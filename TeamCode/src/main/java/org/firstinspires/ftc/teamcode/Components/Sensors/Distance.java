package org.firstinspires.ftc.teamcode.Components.Sensors;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Distance{
    DistanceSensor distanceSensor;

    public Distance(HardwareMap hardwareMap){
        distanceSensor = hardwareMap.get(DistanceSensor.class, "dist");
    }

    public double getDistance(){
        return distanceSensor.getDistance(DistanceUnit.CM);
    }
}
