package org.firstinspires.ftc.teamcode.additional.DataPackages;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Common.Vector3d;

@Config
public class BigRobotArmData {
    //Grabber coordinates with respect to the front side of the robot and the ground
    public final Vector2d initialGrabberCoordinates = new Vector2d(10, 10);
    Vector2d grabberCoordinates = initialGrabberCoordinates;
    public Vector2d getGrabberCoordinates() {
        return grabberCoordinates;
    }

    //Lower section
    DcMotorEx lowerArmMotor;
    public DcMotorEx getLowerArmMotor() {
        return lowerArmMotor;
    }
    public final int lowerMotorTicksLowerBound = 0;
    public final int lowerMotorTicksUpperBound = 380;

    //Upper section

        //Constants
    DcMotorEx upperArmMotor;
    public DcMotorEx getUpperArmMotor() {
        return upperArmMotor;
    }
    public final int upperMotorTicksUpperBound = 6000;
    public final int upperMotorTicksLowerBound = 0;
    public final double upperMotorGearRatio = 1.0f/4.5f;
    public final double ticksPerCm = -94.492;



        //Coordinates
    public final double initialArmLength = 42;
    public double armLength = initialArmLength;

    //Lower section
    Servo clawServoRight;
    Servo clawServoLeft;
    Servo sweeperServo;
    public Servo getClawServoRight() {
        return clawServoRight;
    }
    public Servo getClawServoLeft() {
        return clawServoLeft;
    }
    public Servo getSweeperServo() { return sweeperServo; }
    public final double ticksPerDeg = 8.1763f;
    public final double initialAngle = 0;
    public double currentAngle = initialAngle;

    //Plane launcher
    Servo planeLauncher;
    public Servo getPlaneLauncher() {
        return planeLauncher;
    }

    public BigRobotArmData(HardwareMap map) {
        lowerArmMotor = map.get(DcMotorEx.class, "lowerArmMotor");
        upperArmMotor = map.get(DcMotorEx.class, "upperArmMotor");
        clawServoRight = map.get(Servo.class, "clawServoRight");
        clawServoLeft = map.get(Servo.class, "clawServoLeft");
        sweeperServo = map.get(Servo.class, "sweeperServo");
        planeLauncher = map.get(Servo.class, "planeLauncher");

        lowerArmMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        upperArmMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        lowerArmMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        upperArmMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        lowerArmMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        //upperArmMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        lowerArmMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        upperArmMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        clawServoRight.setPosition(1);
        clawServoLeft.setPosition(0);

    }

    public void localize() {
        armLength = armLength +( getUpperArmMotor().getCurrentPosition()/ticksPerCm);
        currentAngle = getLowerArmMotor().getCurrentPosition()/ticksPerDeg;
        double x = armLength*Math.cos(Math.toRadians(currentAngle));
        double y = armLength*Math.sin(Math.toRadians(currentAngle));
        grabberCoordinates = new Vector2d(x, y);
    }
}