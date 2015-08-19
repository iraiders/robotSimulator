package org.usfirst.frc.team2713.robot.commands;

import edu.wpi.first.wpilibj.Timer;

public class goForward extends commandBase {

	double distance; // 8' at 70%
	double time;
	Timer timer;
	double polarity = 0.7;

	public goForward(double distance1) {
		timer = new Timer();
		distance = distance1;
		time = distance / 8;
		if (distance < 0) {
			polarity = -.7;
			time = time * -1;
		}
		System.out.println(time);
		timer.reset();
		timer.start();
		// drive.thisEncoder.reset();
	}

	protected void initialize() {
		timer.reset();
		timer.start();
	}

	public void execute() {
		if ((timer.get() < time)) {
			drive.move(polarity);
		} else {
			drive.move(0);
		}
	}

	public boolean isFinished() {

		// if (drive.thisEncoder.getDistance() > distance * 12) {
		// drive.move(0);
		// return true;
		// } else {
		// return false;
		// }
		if (timer.get() > time) {
			drive.move(0);
			return true;
		}
		return false;

	}

}