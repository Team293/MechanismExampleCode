/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class BalanceBoard extends IterativeRobot {

	private Encoder enc = new Encoder(1, 1);
	private Jaguar jag = new Jaguar(1);
	private Joystick joystick = new Joystick(1);
	// you have to hold this
	private JoystickButton autoBalance = new JoystickButton(joystick, 1);
	private JoystickButton resetGyro = new JoystickButton(joystick, 3);
	private Gyro gyro = new Gyro(1);
	DriverStationLCD LCD = DriverStationLCD.getInstance();
	private static final double kp = 0.0, ki = 0.0, kd = 0.0;
	private static double lastAngle = 0.0, output = 0.0, integral = 0.0,
			lastT = 0.0;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		// you always need this for encoders
		enc.start();
		enc.reset();
		// not sure if you need this
		gyro.reset();
		// for safety
		jag.set(0.0);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		/**
		 * this is the angle from the gyro, which will be the input for our
		 * controller. It returns in degrees and shouldn't be more than +=40
		 */
		double angle = gyro.getAngle();
		double t = Timer.getFPGATimestamp();
		double deltaT = t - lastT;
		double error = angle;
		/**
		 * the following is a basic PID (proportional, integral, differentiable)
		 * control algorithm the idea is the keep the board balanced
		 * its ok that you dont understand it, or be super amazing and google it 
		 */
		integral = integral + deltaT * (lastAngle + angle) / 2.0;
		double derivitive = (angle - lastAngle) / deltaT;
		if (autoBalance.get()) {
			output = kp * error + ki * integral + kd * derivitive;
			jag.set(output);
		} else {
			jag.set(joystick.getY() / 2.0);
		}
		LCD.println(DriverStationLCD.Line.kUser2, 1, "angle: " + angle);
		LCD.updateLCD();
		lastT = t;
		lastAngle = angle;
		if (resetGyro.get()){
			gyro.reset();
		}
	}
	
	
}
