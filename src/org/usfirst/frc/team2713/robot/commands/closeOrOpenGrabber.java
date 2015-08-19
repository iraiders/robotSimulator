package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.RobotMap;


import edu.wpi.first.wpilibj.Timer;

public class closeOrOpenGrabber extends commandBase{

	double direction;
	Timer time;
	
	public closeOrOpenGrabber(double direction1) { //-1 is close, 1 is open
		time = new Timer();
		time.reset();
		time.start();
		direction = direction1;
	}
	
	public void execute() {
		grab.setLift(direction);
	}
	
	public boolean isFinished() {
		if ((!grab.armClosed.get()) && !grab.armClosed2.get() && direction < 0) {
			System.out.println("Run");
			return true;
		}
		if(time.get() > RobotMap.TIME_TO_CLOSE_OR_OPEN && direction > 0) {
			grab.setLift(0);
			return true;
		}
		return false;
	}
	
}