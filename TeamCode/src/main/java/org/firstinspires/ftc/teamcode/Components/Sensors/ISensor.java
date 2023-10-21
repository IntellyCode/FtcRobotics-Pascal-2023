package org.firstinspires.ftc.teamcode.Components.Sensors;

/*If you expose the concrete type of the sensor in the getType() function,
then why hide it [type] behind an interface in the first place? This seems pointless and very type-unsafe.*/


public interface ISensor {

    //Interface for every sensor (including camera)

    //Method to get data of sensor

    /*Wrapping and unwrapping data into object every time may be performance-heavy, why not use generics?*/
    Object getData();

    //Method to get type of sensor
    SensorTypes getType();
}