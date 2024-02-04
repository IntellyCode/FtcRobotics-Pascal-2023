package org.firstinspires.ftc.teamcode.additional.Actions;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.additional.DataPackages.ArmData;
import org.firstinspires.ftc.teamcode.additional.Controllers.PIDController;
import org.firstinspires.ftc.teamcode.TelemetryHelper;

public class LiftToAngle implements IAction {
    ArmData armData;
    DcMotorEx leftMotor;
    DcMotorEx rightMotor;
    PIDController controller;
    boolean isStarted;
    boolean isFinished;
    double targetAngle;
    public LiftToAngle(ArmData armData, double targetAngle) {
        this.armData = armData;
        this.targetAngle = targetAngle;
        leftMotor = armData.getLeftArmMotor();
        rightMotor = armData.getRightArmMotor();
        controller = new PIDController(armData.getPIDCoefficients());
    }

    @Override
    public void start() {
        controller.start();
    }
    @Override
    public void update() {
        TelemetryHelper.getDashboardTelemetry().put("Target angle: ", targetAngle);

        double currentAngle = calculateCurrentAngle(armData.getLeftArmMotor().getCurrentPosition());
        armData.currentAngle = currentAngle;
        TelemetryHelper.getDashboardTelemetry().put("Current angle: ", currentAngle);

        double output = controller.update(targetAngle, currentAngle);
        TelemetryHelper.getDashboardTelemetry().put("Output: ", output);
        leftMotor.setPower(output);
        rightMotor.setPower(output);
    }
    @Override
    public boolean isOver() {
        return isFinished;
    }

    @Override
    public boolean isStarted() {
        return isStarted;
    }

//    int calculateTargetTicks(double targetAngle) {
//        return (int) (2*Math.PI*targetAngle)/armData.ticksPerRev;
//    }

    double calculateCurrentAngle(int ticks) {
        return (armData.ticksPerRev*ticks)/(2*Math.PI);
    }

    //double feedforwardOutput() {
       // return ArmData.kA * ArmData.kG * Math.sin(armData.currentAngle);
    //}
}
