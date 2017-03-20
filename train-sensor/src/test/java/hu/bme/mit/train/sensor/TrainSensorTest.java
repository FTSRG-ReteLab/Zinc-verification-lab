package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.user.TrainUserImpl;

import static org.mockito.Mockito.*;

public class TrainSensorTest {
	
	TrainSensor sensor;
	TrainController mockTC;
	TrainUser mockTU;

    @Before
    public void init() {
    	// stub of TrainController
    	mockTC = mock(TrainController.class);
    	// mock of TrainUser
    	mockTU = mock(TrainUser.class);
    	sensor = new TrainSensorImpl(mockTC, mockTU);
    }
    
    @Test
    public void marginRelTest() {
    	when(mockTC.getReferenceSpeed()).thenReturn(200);
    	sensor.overrideSpeedLimit(20);
    	verify(mockTU, times(1)).setAlarmState(true);
    }
    
    @Test
    public void marginAbsUnderZeroTest() {
    	sensor.overrideSpeedLimit(-100);
    	verify(mockTU, times(1)).setAlarmState(true);
    }
    
    @Test
    public void marginAbsOver500Test() {
    	
    	sensor.overrideSpeedLimit(550);
    	verify(mockTU, times(1)).setAlarmState(true);
    }
    
    @Test
    public void combinationTest() {
    	when(mockTC.getReferenceSpeed()).thenReturn(200);
    	sensor.overrideSpeedLimit(550);
    	verify(mockTU, times(1)).setAlarmState(true);
    }
    
}
