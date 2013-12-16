/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Turret extends IterativeRobot {

    NetworkTable cameraTable = NetworkTable.getTable("SmartDashboard");
    Servo pan = new Servo(6);
    Servo tilt = new Servo(5);
    Joystick joystick = new Joystick(1);
    JoystickButton auto = new JoystickButton(joystick, 1);
    JoystickButton move = new JoystickButton(joystick, 2);
    double cogX = 0.0, cogY = 0.0;
    DriverStationLCD LCD = DriverStationLCD.getInstance();
    int pauseCount = 1, pause = 10;
    double deltaPan = 0.0, deltaTilt = 0.0, newPan = 0.0, newTilt = 0.0;

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

        cogX = cameraTable.getNumber("COG_X", -1);
        cogY = cameraTable.getNumber("COG_Y", -1);
        LCD.println(DriverStationLCD.Line.kUser1, 1, "COG_X " + cogX + " COG_Y " + cogY);
        LCD.println(DriverStationLCD.Line.kUser2, 1, "pan " + pan.get());
        LCD.println(DriverStationLCD.Line.kUser3, 1, "tilt " + tilt.get());

        SmartDashboard.putNumber("deltaPan", deltaPan);
        SmartDashboard.putNumber("deltaTilt", deltaTilt);

        if (auto.get()) {
            if (pauseCount % pause == 0) {

                deltaPan = pan.get() - 0.5;
                deltaTilt = tilt.get() - 0.5;

                newPan = deltaPan + cogX / (640.0 * 3.0) + 1.0 / 3.0;
                newTilt = deltaTilt + -cogY / (3 * 480.0) + 2.0 / 3.0;
                pan.set(newPan);
                tilt.set(newTilt);
            }
            pauseCount++;
        } else {
            if (move.get()) {
                pan.set(joystick.getX() / 2.0 + 0.5);
                tilt.set((joystick.getY() + 0.5));
            }
        }

        LCD.updateLCD();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    }
}
