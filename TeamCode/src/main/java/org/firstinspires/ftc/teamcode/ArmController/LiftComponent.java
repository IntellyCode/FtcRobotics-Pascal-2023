package org.firstinspires.ftc.teamcode.ArmController;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LiftComponent {

    public final double ARM_WHEEL_DIAMETER = 5.75;
    public final int TICKS_PER_REVOLUTION = 1120;
    public final int ARM_TICKS_PER_CM = (int)(TICKS_PER_REVOLUTION/(ARM_WHEEL_DIAMETER * Math.PI));

    //Сюда вставить ограничение на подъём руки.
    public final double MAXIMUM_ARM_POSITION = 90;
    public MotorLocalizer motorLocalizer;

    //public int armMotorPosition;
    public LiftComponent(HardwareMap hardwareMap) {
        //Arm init stuff
        DcMotor motor = hardwareMap.get(DcMotor.class, "liftMotor");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        motor.setPower(1);
        MotorLocalizer localizer = new MotorLocalizer(TICKS_PER_REVOLUTION, ARM_WHEEL_DIAMETER, motor);
    }
}