package org.firstinspires.ftc.teamcode.additional.DataPackages;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class BigRobotPlaneLauncherData {
    Servo springServo;
    public Servo getSpringServo() {
        return springServo;
    }

    public boolean planeThrown;
    public BigRobotPlaneLauncherData(HardwareMap map) {
        //Initialize sensors
        springServo = map.get(Servo.class, "springServo");

    }

    void update() {

    }


}
