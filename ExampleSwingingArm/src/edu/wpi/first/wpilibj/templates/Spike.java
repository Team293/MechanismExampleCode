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
import edu.wpi.first.wpilibj.Victor;
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

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    Victor motor = new Victor(1);
    Encoder enc = new Encoder(1, 2);
    Joystick joystick = new Joystick(1);
    JoystickButton controlByPosition = new JoystickButton(joystick, 1);
    DigitalInput leftLimit = new DigitalInput(1);
    DigitalInput rightLimit = new DigitalInput(2);
    double kP = 0.1, tolerence = 0.1;

    public void robotInit() {
        // you always have to start and reset encoders
        enc.start();
        enc.reset();
        // just for safety
        motor.set(0);

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
        if (controlByPosition.get()) {
            setToEncoderValue(joystick.getY());
        } else {
            motor.set(joystick.getY());
        }
    }

    public void setToEncoderValue(double pos) {
        double error = pos - enc.getRaw();
        if (error > tolerence) {
            if (error * kP < 1) {
                motor.set(error * kP);
            } else {
                motor.set(1);
            }
        } else if (error < -tolerence) {
            if (error * kP > -1) {
                motor.set(error * kP);
            } else {
                motor.set(-1);
            }
        }
    }
}
