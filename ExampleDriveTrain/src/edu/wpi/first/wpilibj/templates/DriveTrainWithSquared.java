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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class DriveTrainWithSquared extends IterativeRobot {

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    Joystick leftJoystick = new Joystick(1);
    Joystick rightJoystick = new Joystick(2);
    Joystick gamepad = new Joystick(3);
    Jaguar leftDrive = new Jaguar(1);
    Jaguar rightDrive = new Jaguar(2);
    RobotDrive drive = new RobotDrive(leftDrive,
            rightDrive);
    SpikeButton squaredDrive = new SpikeButton(leftJoystick, 3);

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
        SmartDashboard.putBoolean("squared drive: ", squaredDrive.getState());
        if (squaredDrive.getState()) {
            //the last parameter is to say "square the joystick values"
            //as usual, look inthe wpilibj source if you want to see how that works
            drive.tankDrive(leftJoystick, rightJoystick, true);
        } else {
            drive.tankDrive(leftJoystick.getY(), rightJoystick.getY());
        }

        if (Math.abs(leftJoystick.getY()) < 0.1 && Math.abs(rightJoystick.getY()) < 0.1) {
            drive.tankDrive(gamepad.getRawAxis(2), gamepad.getRawAxis(4));
        }
        SmartDashboard.putNumber("leftJoy: ", leftJoystick.getY());
        SmartDashboard.putNumber("rightJoy: ", rightJoystick.getY());

        SmartDashboard.putNumber("gamepad left Y: ", gamepad.getRawAxis(2));
        SmartDashboard.putNumber("gamepad right y: ", gamepad.getRawAxis(4));

    }
}
