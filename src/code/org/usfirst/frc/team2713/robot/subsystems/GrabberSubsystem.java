package code.org.usfirst.frc.team2713.robot.subsystems;

public class GrabberSubsystem extends Subsystem {

	public UniversalController grab;
	moveGrabber graberCommand;
	public DigitalInput armClosed;

	public GrabberSubsystem() {
		grab = new UniversalController(RobotMap.GRAB_MOTOR);
		if (graberCommand == null) {
			graberCommand = new moveGrabber();
		}
		if (armClosed == null) {
			armClosed = new DigitalInput(RobotMap.ARM_LIMIT_SWITCH_NUM);
		}
	}
	
	public void intiCommand() {
		graberCommand.start();
	}

	protected void initDefaultCommand() {

	}

	public void setLift(double polarity) {
		grab.getProperController().set(polarity);
	}

	public double getAmps() {
		return ((CANJaguar) grab.getProperController()).getBusVoltage();
	}

	public double getRaw() {
		return ((Talon) grab.getProperController()).getRaw();
	}

}