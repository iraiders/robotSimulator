package edu.wpi.first.wpilibj.command;

import main.mainClass;

public class Command {

	int id;

	public Command() {

	}

	public boolean isFinished() {
		return false;
	}

	public void execute() {

	}

	public void cancel() {

	}

	public void start() {
		if (mainClass.thisBoard.robot.enabled) {
			id = mainClass.thisBoard.manager.addThread(this);
			mainClass.thisBoard.manager.start(id);
			
		}
	}

	protected void end() {

	}

	protected void requires(Subsystem stuff) {

	}

}
