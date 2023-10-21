package org.firstinspires.ftc.teamcode.Components;

import org.firstinspires.ftc.teamcode.Components.Sensors.SensorException;
import org.firstinspires.ftc.teamcode.Controller.DataPackages.Sensors.SensorData;
import org.firstinspires.ftc.teamcode.Controller.DataPackages.Sensors.SensorInfo;

import java.util.List;

public class SensorComponent extends ComponentAbstract{

    //The data package present in the SensorComponent
    SensorData sensorData;
    public SensorComponent(String componentName) {
        super(componentName);
    }

    //Set the data package from the controller after a new ControllerData package is read
    public void setSensorData(SensorData sensorData){
        this.sensorData = sensorData;
    }

    //After updating return the sensorData package with the new values
    public SensorData getSensorData() throws SensorException{
        if(!sensorData.getWasModified()){
            throw new SensorException("Sensor data was not yet read");
        }
        return sensorData;
    }

    //Looped function which for
    //each sensor of the data package updates the value related to the sensor
    //and informs the package of being modified
    @Override
    public void performAction() {
       List<SensorInfo> sensorInfos = sensorData.getSensorsInfo();

       for (SensorInfo sensorInfo : sensorInfos){
           sensorInfo.setValue(sensorInfo.getSensor().getData());
       }
       sensorData.setWasModified(true);
    }
}
