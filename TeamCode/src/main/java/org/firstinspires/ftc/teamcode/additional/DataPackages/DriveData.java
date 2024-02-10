package org.firstinspires.ftc.teamcode.additional.DataPackages;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Common.Vector3d;

@Config
public class DriveData {
    public static Vector3d pidCoefficients = new Vector3d(1f , 0.2f, 0.1f);
    public static double kV;
    public static double kA;
    public Vector3d getPIDCoefficients() {
        return pidCoefficients;
    }
    public DcMotorEx upperLeft;
    public DcMotorEx getUpperLeft() {
        return upperLeft;
    }
    public DcMotorEx upperRight;
    public DcMotorEx getUpperRight() {
        return upperRight;
    }
    public DcMotorEx lowerLeft;
    public DcMotorEx getLowerLeft() {
        return lowerLeft;
    }
    public DcMotorEx lowerRight;
    public DcMotorEx getLowerRight() {
        return lowerRight;
    }
    public DriveData(HardwareMap map) {
        upperLeft = map.get(DcMotorEx.class, "upperRight");
        upperRight = map.get(DcMotorEx.class, "upperLeft/perpendicularEncoder");
        lowerLeft = map.get(DcMotorEx.class, "lowerRight");
        lowerRight = map.get(DcMotorEx.class, "lowerLeft/parallelEncoder");

        upperLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        upperRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        lowerLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        lowerRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        lowerRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        lowerRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        lowerRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        lowerRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        upperLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        upperRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        lowerLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        lowerRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);


        upperLeft.setDirection(DcMotorEx.Direction.REVERSE);
        lowerLeft.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    void update() {

    }
}
