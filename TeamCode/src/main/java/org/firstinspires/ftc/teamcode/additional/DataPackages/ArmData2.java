package org.firstinspires.ftc.teamcode.additional.DataPackages;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Vector2d;
import org.firstinspires.ftc.teamcode.Vector3d;

@Config
public class ArmData2 {
    public static Vector3d pidCoefficients = new Vector3d(1f , 0.2f, 0.1f);

    public Vector3d getPIDCoefficients() {
        return pidCoefficients;
    }
    public final int ticksPerRev = 288;
    public final double gearRatio = 1/(1.5*4.5);
    public static final Vector2d initialCoordinates = new Vector2d(0, 0);
    DcMotorEx rightArmMotor;
    public DcMotorEx getRightArmMotor() {
        return rightArmMotor;
    }
    DcMotorEx leftArmMotor;
    public DcMotorEx getLeftArmMotor() {
        return leftArmMotor;
    }
    public ArmData2(HardwareMap map) {
        rightArmMotor = map.get(DcMotorEx.class, "rightArmMotor");
        leftArmMotor = map.get(DcMotorEx.class, "leftArmMotor");

        rightArmMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftArmMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        rightArmMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        leftArmMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        rightArmMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftArmMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        rightArmMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftArmMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }
}
