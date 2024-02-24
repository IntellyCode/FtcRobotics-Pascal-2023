package org.firstinspires.ftc.teamcode.additional.DataPackages;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SmallRobotDriveData {
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
    public SmallRobotDriveData(HardwareMap map) {
        upperRight = map.get(DcMotorEx.class, "upperRight");
        upperLeft = map.get(DcMotorEx.class, "upperLeft");
        lowerLeft = map.get(DcMotorEx.class, "lowerLeft");
        lowerRight = map.get(DcMotorEx.class, "lowerRight");


        upperLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        upperRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        lowerLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        lowerRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        upperLeft.setDirection(DcMotorEx.Direction.REVERSE);
        lowerLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}