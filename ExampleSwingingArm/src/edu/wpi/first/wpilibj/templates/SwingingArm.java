/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
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
public class SwingingArm extends IterativeRobot {
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */

	Jaguar jag = new Jaguar(1);
	Encoder enc = new Encoder(1, 2);
	Joystick joystick = new Joystick(1);
	JoystickButton leftEnd = new JoystickButton(joystick, 3);
	JoystickButton rightEnd = new JoystickButton(joystick, 4);
	DigitalInput leftLimit = new DigitalInput(1);
	DigitalInput rightLimit = new DigitalInput(2);
	int state;
	int prevState;

	public void robotInit() {
		// you always have to start and reset encoders
		enc.start();
		enc.reset();
		// just for safety
		jag.set(0);
		// init your state variables
		state = 0;

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
		if (leftEnd.get() || state == 1) {
			state = 1;
			if (!leftLimit.get()) {
				jag.set(0.2);
			} else {
				jag.set(0); // make sure to stop it!!!
				state = 0; // return to manual control
			}
		} else if (rightEnd.get() || state == 2) {
			state = 2;
			if (!rightLimit.get() || state == 3) {
				jag.set(-0.2);
			} else {
				jag.set(0); // make sure to stop it!!!
				state = 0; // return to manual control
			}
		} else {
			double y = 0.0;
			if (!leftLimit.get() && (y = joystick.getY()) > 0) {
				jag.set(y);
			} else if (!rightLimit.get() && (y = joystick.getY()) < 0) {
				jag.set(y);
			} else {
				jag.set(0);// make sure to stop it!!!
			}
		}
	}

}
