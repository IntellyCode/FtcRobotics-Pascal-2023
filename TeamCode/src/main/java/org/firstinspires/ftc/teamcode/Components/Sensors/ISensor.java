package org.firstinspires.ftc.teamcode.Components.Sensors;

public interface ISensor {

    //Interface for every sensor (including camera)

    //Method to get data of sensor
    Object getData();

    //Method to get type of sensor
    SensorTypes getType();
}
