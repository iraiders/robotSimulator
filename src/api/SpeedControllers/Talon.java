package api.SpeedControllers;


public class Talon extends SpeedController {
		
	public Talon(int portNum1) {
		super(portNum1);
	}
	
	public double getRAW() {
		return 0;
	}
	
}
