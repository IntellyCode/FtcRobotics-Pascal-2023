package org.firstinspires.ftc.teamcode.Controller.DataPackages.Sensors;

import org.firstinspires.ftc.teamcode.Components.Sensors.ISensor;
import org.firstinspires.ftc.teamcode.Components.Sensors.SensorException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SensorData {
    //Data package for sensors

    List<SensorInfo> sensorList;

    boolean wasModified;

    public SensorData(ISensor... sensors) throws SensorException {
        wasModified = false;
        Set<ISensor> sensorSet = new HashSet<>();
        sensorList = new ArrayList<>();

        for (ISensor sensor : sensors) {
            //Ensures two identical sensors are not passed
            if (sensorSet.contains(sensor)) {
                throw new SensorException("Two identical sensors passed");
            }

            sensorSet.add(sensor);

            // Create a SensorInfo and add it to the sensorList
            SensorInfo sensorInfo = new SensorInfo(sensor);
            sensorList.add(sensorInfo);
        }
    }

    //Returns the sensors information for each sensors and its corresponding value,
    // value = null if data package has not yet been modified
    public List<SensorInfo> getSensorsInfo(){
        return sensorList;
    }

    public void setWasModified(boolean val){
        wasModified = val;
    }

    public boolean getWasModified() {
        return wasModified;
    }
}
