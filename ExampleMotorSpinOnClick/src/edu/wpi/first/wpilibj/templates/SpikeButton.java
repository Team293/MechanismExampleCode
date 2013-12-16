/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 *
 * @author Peter
 */
public class SpikeButton extends JoystickButton {

    private boolean lastState = false, currentState = false, state = false;

    SpikeButton(GenericHID joystick, int buttonNumber) {
        super(joystick, buttonNumber);
    }

    public boolean getState() {
        currentState = super.get();
        if (currentState && !lastState) {
            state = !state;
        }
        return state;
    }

}
