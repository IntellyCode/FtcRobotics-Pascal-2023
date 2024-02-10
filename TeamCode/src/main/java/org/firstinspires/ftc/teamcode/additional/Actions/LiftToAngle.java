package org.firstinspires.ftc.teamcode.additional.Actions;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.additional.DataPackages.SmallRobotArmData;
import org.firstinspires.ftc.teamcode.additional.Controllers.PIDController;
import org.firstinspires.ftc.teamcode.TelemetryHelper;

public class LiftToAngle implements IAction {
    SmallRobotArmData smallRobotArmData;
    DcMotorEx leftMotor;
    DcMotorEx rightMotor;
    PIDController controller;
    boolean isFinished;
    double targetAngle;
    public LiftToAngle(SmallRobotArmData smallRobotArmData, double targetAngle) {
        this.smallRobotArmData = smallRobotArmData;
        this.targetAngle = targetAngle;
        leftMotor = smallRobotArmData.getLeftArmMotor();
        rightMotor = smallRobotArmData.getRightArmMotor();
        controller = new PIDController(smallRobotArmData.getPIDCoefficients());
    }

    @Override
    public void start() {
        controller.start();
    }
    @Override
    public void update() {
        TelemetryHelper.getDashboardTelemetry().put("Target angle: ", targetAngle);

        double currentAngle = calculateCurrentAngle(smallRobotArmData.getLeftArmMotor().getCurrentPosition());
        //smallRobotArmData.currentAngle = currentAngle;
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
    double calculateCurrentAngle(int ticks) {
        //return (smallRobotArmData.ticksPerRev*ticks)/(2*Math.PI);
        return 0.0f;
    }

    //double feedforwardOutput() {
       // return ArmData.kA * ArmData.kG * Math.sin(armData.currentAngle);
    //}
}
