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
public class SmallRobotArmData {
    //Upper section
    DcMotorEx rightArmUpperMotor;
    public DcMotorEx getRightArmMotor() {
        return rightArmUpperMotor;
    }

    DcMotorEx leftArmUpperMotor;
    public DcMotorEx getLeftArmMotor() {
        return leftArmUpperMotor;
    }
    public final double degreesPerCmUpper = 0;

    //Lower section
    Servo lowerServo;
    public Servo getLowerServo() {
        return lowerServo;
    }
    public final double degreesPerCmLower = 0;

    Servo grabberServo;
    public Servo getGrabberServo() {
        return grabberServo;
    }

    public SmallRobotArmData(HardwareMap map) {
        rightArmUpperMotor = map.get(DcMotorEx.class, "rightArmMotor");
        leftArmUpperMotor = map.get(DcMotorEx.class, "leftArmMotor");
        lowerServo = map.get(Servo.class, "lowerServo");
        grabberServo = map.get(Servo.class, "grabberServo");;

        rightArmUpperMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftArmUpperMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        rightArmUpperMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        leftArmUpperMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);


        //rightArmUpperMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        //leftArmUpperMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        rightArmUpperMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftArmUpperMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }
}
