package org.firstinspires.ftc.teamcode.additional.DataPackages;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Vector3d;

@Config
public class BigRobotArmData {
    //Pid coefficients
    public static Vector3d pidCoefficients = new Vector3d(1f , 0.2f, 0.1f);
    public Vector3d getPIDCoefficients() {
        return pidCoefficients;
    }

    //Upper section
    DcMotorEx lowerArmMotor;
    DcMotorEx upperArmMotor;
    public final int upperMotorTicksPerRev = 288;
    public final double upperMotorGearRation = 1.0f/4.5f;

    public DcMotorEx getLowerArmMotor() {
        return lowerArmMotor;
    }

    public DcMotorEx getUpperArmMotor() {
        return upperArmMotor;
    }

    //Lower section
    Servo clawServo;
    public final int clawServoTicksPerRev = 000;
    public final float clawServoGearRatio = 000f;


    public BigRobotArmData(HardwareMap map) {
        lowerArmMotor = map.get(DcMotorEx.class, "lowerArmMotor");
        upperArmMotor = map.get(DcMotorEx.class, "upperArmMotor");
        clawServo = map.get(Servo.class, "clawServo");

        lowerArmMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        upperArmMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        lowerArmMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        upperArmMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        lowerArmMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        upperArmMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        lowerArmMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        upperArmMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }
}