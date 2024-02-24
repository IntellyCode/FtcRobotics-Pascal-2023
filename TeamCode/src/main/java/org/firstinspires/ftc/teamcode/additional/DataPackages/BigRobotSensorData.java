package org.firstinspires.ftc.teamcode.additional.DataPackages;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Camera.Util.RelativePosition;
import org.firstinspires.ftc.teamcode.Common.Team;
import org.firstinspires.ftc.teamcode.Camera.Camera;

public class BigRobotSensorData {

    Team team = Team.red;
    Camera camera;
    DistanceSensor distanceSensor;

    public BigRobotSensorData(HardwareMap map){
        distanceSensor = map.get(DistanceSensor.class, "distanceSensor");
        camera =new Camera(map,team);
    }

    public void cameraStart(){
        camera.start();
    }
    public double getDistance(){
        return distanceSensor.getDistance(DistanceUnit.MM);
    }

    public RelativePosition getRelPosOfProp(){
        return camera.getPipeline().getRelPosOfProp();
    }
}
