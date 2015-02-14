package code.org.usfirst.frc.team2713.robot.commands;

import code.org.usfirst.frc.team2713.robot.RobotMap;
import api.Sensors.*;

public class closeOrOpenGrabber extends commandBase{

	int direction;
	Timer time;
	
	public closeOrOpenGrabber(int direction1) { //-1 is close, 1 is open
		time = new Timer();
		time.reset();
		time.start();
		direction = direction1;
	}
	
	public void execute() {
		grab.setLift(direction);
	}
	
	public boolean isFinished() {
		if (grab.armClosed.get() && direction > 0) {
			return true;
		}
		if(time.get() > RobotMap.TIME_TO_CLOSE_OR_OPEN && direction < 0) {
			grab.setLift(0);
			return true;
		}
		return false;
	}
	
}
