/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc293;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Spike extends IterativeRobot {
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */

	Joystick leftJoystick = new Joystick(1);
	Joystick rightJoystick = new Joystick(2);
	Jaguar leftDrive = new Jaguar(1);
	Jaguar rightDrive = new Jaguar(1);
	Encoder leftDriveEnc = new Encoder(1, 2);
	Encoder rightDriveEnc = new Encoder(1, 2);
	DriverStationLCD out = DriverStationLCD.getInstance();
	RobotDrive drive = new RobotDrive(leftDrive.getChannel(),
			rightDrive.getChannel());

	public void robotInit() {
		// you always have to start and reset encoders
		leftDriveEnc.start();
		rightDriveEnc.start();
		leftDriveEnc.reset();
		rightDriveEnc.reset();
		// just for safety
		leftDrive.set(0);
		rightDrive.set(0);

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
		drive.tankDrive(leftJoystick.getY(), rightJoystick.getY());

		out.println(DriverStationLCD.Line.kUser2, 1, "left drive: "
				+ leftDriveEnc.getRaw() + " " + leftDriveEnc.getDistance()
				+ " " + leftDriveEnc.getRate());
		out.println(DriverStationLCD.Line.kUser3, 1, "right drive: "
				+ rightDriveEnc.getRaw() + " " + rightDriveEnc.getDistance()
				+ " " + rightDriveEnc.getRate());
		out.updateLCD(); // you need this in order for the display to update
	}
}
