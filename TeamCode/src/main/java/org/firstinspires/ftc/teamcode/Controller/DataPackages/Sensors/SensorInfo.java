package org.firstinspires.ftc.teamcode.Controller.DataPackages.Sensors;

import org.firstinspires.ftc.teamcode.Components.Sensors.ISensor;
import org.firstinspires.ftc.teamcode.Components.Sensors.SensorException;

public class SensorInfo {

    // A wrapper class which includes the sensor and its value and Object
    ISensor sensor;

    Object value = null;

    //Init the sensors info object
    public SensorInfo(ISensor sensor){
        this.sensor = sensor;
    }


    public ISensor getSensor(){
        return sensor;
    }
    public void setValue(Object value){
        this.value = value;
    }

    //get's the value of the SensorInfo, but ensures that the value has been collected
    public Object getValue() throws SensorException{
        if(value== null){
            throw new SensorException("Value not read yet");
        }
        return value;
    }

}
