package org.firstinspires.ftc.teamcode.DataPackages;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class BigRobotDriveData {
    DcMotorEx upperLeft;
    public DcMotorEx getUpperLeft() {
        return upperLeft;
    }
    DcMotorEx upperRight;
    public DcMotorEx getUpperRight() {
        return upperRight;
    }
    DcMotorEx lowerLeft;
    public DcMotorEx getLowerLeft() {
        return lowerLeft;
    }
    DcMotorEx lowerRight;
    public DcMotorEx getLowerRight() {
        return lowerRight;
    }
    //public DistanceSensor distanceSensor;
    public BigRobotDriveData(HardwareMap map) {
        upperRight = map.get(DcMotorEx.class, "upperRight/parallelEncoder");
        upperLeft = map.get(DcMotorEx.class, "upperLeft/perpendicularEncoder");
        lowerLeft = map.get(DcMotorEx.class, "lowerLeft");
        lowerRight = map.get(DcMotorEx.class, "lowerRight");


        upperRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        upperLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lowerLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lowerRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        upperLeft.setDirection(DcMotorEx.Direction.REVERSE);
        lowerLeft.setDirection(DcMotorSimple.Direction.REVERSE);

    }
}
