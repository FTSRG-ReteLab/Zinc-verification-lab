package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit = 5;

	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		this.user = user;
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		
		this.speedLimit = speedLimit;
		
		boolean marginAbs = speedLimit < 0 || speedLimit > 500;
		boolean marginRel = controller.getReferenceSpeed()/speedLimit > 2;
		if(marginAbs || marginRel) {
			user.setAlarmState(true);
		} else {
			user.setAlarmState(false);
		}
			
		controller.setSpeedLimit(speedLimit);
	}

}
