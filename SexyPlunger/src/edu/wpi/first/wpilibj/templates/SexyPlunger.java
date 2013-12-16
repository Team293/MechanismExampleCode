/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.SpikeButton;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class SexyPlunger extends IterativeRobot {

    Jaguar motor = new Jaguar(3);
    DigitalInput limit = new DigitalInput(4);
    Joystick joystick = new Joystick(1);
    DriverStationLCD LCD = DriverStationLCD.getInstance();
    SpikeButton fire = new SpikeButton(joystick, 1);
    boolean movedOffLimitSwitch = false;

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
        if (Math.abs(joystick.getY()) > 0.05) {
            motor.set(joystick.getY());
        } else {
            if (fire.getState()) {
                if (!movedOffLimitSwitch) {
                    if (limit.get()) {                        motor.set(-1);
                    } else {
                        movedOffLimitSwitch = true;
                    }
                } else {
                    if (!limit.get()) {
                        motor.set(-1);
                    } else {
                        motor.set(0.0);
                        movedOffLimitSwitch = false;
                        fire.setState(false);
                    }
                }
            }
        }

    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {

    }

}
