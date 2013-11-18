package org.usfirst.frc0;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class PID extends PIDSubsystem {

	private static final double Kp = 0.0;
	private static final double Ki = 0.0;
	private static final double Kd = 0.0;
	public double setpoint = 0.0;

	Jaguar jag;
	Encoder enc;

	public PID(Jaguar jag, Encoder enc) {
		super("newPIDSubsystem", Kp, Ki, Kd);
		this.jag = jag;
		this.enc = enc;
		enc.start();
		enc.reset();

		this.setSetpointRange(0.0, 1.0);
		this.setSetpoint(setpoint);
		this.enable();
		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.
	}

	protected double returnPIDInput() {
		return enc.getRaw();
	}

	protected void usePIDOutput(double output) {
		jag.set(output);
	}

	public double getOutput() {
		return this.getOutput();
	}

	protected void initDefaultCommand() {

	}

}
