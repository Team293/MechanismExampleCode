/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.buttons.SpikeButton;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class SpinMove extends IterativeRobot {

    Jaguar leftMotor = new Jaguar(1);
    Jaguar rightMotor = new Jaguar(2);
    Joystick leftJoystick = new Joystick(1);
    Joystick rightJoystick = new Joystick(2);
    SpikeButton spinRight = new SpikeButton(rightJoystick, 1);
    SpikeButton spinLeft = new SpikeButton(leftJoystick, 1);
    RobotDrive drive = new RobotDrive(leftMotor, rightMotor);

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {

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
        if (spinRight.getState()) {
            //spin move right
        } else if (spinLeft.getState()) {
            //spin move left
        } else {
            drive.tankDrive(leftJoystick, rightJoystick);
        }
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {

    }
}
