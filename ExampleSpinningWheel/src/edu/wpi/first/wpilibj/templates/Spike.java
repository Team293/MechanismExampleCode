/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Spike extends IterativeRobot {

	Encoder enc = new Encoder(1, 2);
	Jaguar jag = new Jaguar(1);
	Joystick joystick = new Joystick(1);
	// this button must be held to be in effect
	JoystickButton emergencyStopButton = new JoystickButton(joystick, 3);
	PID pid = new PID(jag, enc);
	DriverStationLCD LCD = DriverStationLCD.getInstance();

	double setpoint = 2000;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		// setup encoders
		enc.start();
		enc.reset();
		// just for safety
		jag.set(0.0);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		// spin at set point
		if (!emergencyStopButton.get()) {
			pid.setpoint=0.5;
		}
		LCD.println(DriverStationLCD.Line.kUser3, 1, ""+pid.getOutput());
		LCD.updateLCD();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		// spin with joystick control
		if (!emergencyStopButton.get()) {
			jag.set(joystick.getY());
		}
		LCD.updateLCD();
	}
}
