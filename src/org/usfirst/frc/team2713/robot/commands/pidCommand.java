package org.usfirst.frc.team2713.robot.commands;

public class pidCommand extends commandBase {

	double startingPoint;

	public pidCommand(double startingPoint) {
		lift.pidStarted = true;
		lift.stopPID = false;
		this.startingPoint = startingPoint;
	}

	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	public void execute() {
		if (!lift.stopPID) {
			if (lift.thisEncoder.getDistance() < startingPoint) {
				lift.lift(.07);
			}
			if (lift.thisEncoder.getDistance() > startingPoint) {
				lift.lift(-.02);
				System.out.println("Adjusting Down");
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	public boolean isFinished() {
		if (lift.stopPID) {
			lift.lift(0);
			lift.pidStarted = false;
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		lift.pidStarted = false;
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		lift.pidStarted = false;
	}

}