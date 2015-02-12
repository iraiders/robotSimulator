package code.org.usfirst.frc.team2713.robot.subsystems;

public class LiftSubsystem extends Subsystem {

	public int currentLevel = 0;
	UniversalController arm;
    public Encoder thisEncoder;
    public int distanceTraveled = 0;
    public int lastPossition = 0;
    public double heightOfArm = 72.8;
    public final double[] totesLocation;
    public final double toteHeight = 12.1;

	public LiftSubsystem() {
		arm = new UniversalController(RobotMap.ARM_MOTOR);
        thisEncoder = new Encoder(RobotMap.LIFT_ENCODER_A_CHANNEL, RobotMap.LIFT_ENCODER_B_CHANNEL);
        thisEncoder.setDistancePerPulse(11);
        totesLocation = new double[6];
        for(int i = 0; i < 6; i++) {
        	totesLocation[i] = toteHeight * i; 
        }
	}

	public void lift(int polarity) {
		arm.getProperController().set(polarity);
	}

	public void initDefaultCommand() {
			
	}

}