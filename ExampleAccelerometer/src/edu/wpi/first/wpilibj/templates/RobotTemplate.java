/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Accelerometer;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    Accelerometer accZ = new Accelerometer(2);
    Accelerometer accY = new Accelerometer(3);
    Accelerometer accX = new Accelerometer(4);
    DriverStationLCD LCD = DriverStationLCD.getInstance();
    JoystickButton reset = new JoystickButton(new Joystick(1),1);

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
        double z=accZ.getAcceleration();
        double y=accY.getAcceleration();
        double x=accX.getAcceleration();
        SmartDashboard.putNumber("z",z);
        SmartDashboard.putNumber("y",y);
        SmartDashboard.putNumber("x",x);
        
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    }
}