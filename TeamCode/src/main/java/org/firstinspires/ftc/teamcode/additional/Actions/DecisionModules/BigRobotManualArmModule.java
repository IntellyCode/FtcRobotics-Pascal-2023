package org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Common.TelemetryHelper;
import org.firstinspires.ftc.teamcode.additional.DataPackages.BigRobotArmData;

public class BigRobotManualArmModule implements IDecisionModule {
    boolean cleshnjaClosed;
    Gamepad gamepad1;
    BigRobotArmData bigRobotArmData;

    public BigRobotManualArmModule(HardwareMap map, Gamepad gamepad1) {
        bigRobotArmData = new BigRobotArmData(map);
        bigRobotArmData.getSweeperServo().setPosition(0);
        this.gamepad1 = gamepad1;
    }

    @Override
    public void controlLoop() {
        //Haptic feedback

        //Lower motor control
        double lowerPower = 0.5*(gamepad1.right_trigger - gamepad1.left_trigger);
        TelemetryHelper.getTelemetry().addData("lower power:", lowerPower);
        TelemetryHelper.getTelemetry().addData("currposleft", bigRobotArmData.getLowerArmMotor().getCurrentPosition());
        TelemetryHelper.getTelemetry().addData("currposright:", bigRobotArmData.getLowerArmMotor().getCurrentPosition());
        if((bigRobotArmData.getLowerArmMotor().getCurrentPosition() > bigRobotArmData.lowerMotorTicksUpperBound) && lowerPower > 0)
            lowerPower = 0;
        if((bigRobotArmData.getLowerArmMotor().getCurrentPosition() < bigRobotArmData.lowerMotorTicksLowerBound) && lowerPower < 0)
            lowerPower = 0;
        TelemetryHelper.getTelemetry().addData("lower after:", lowerPower);
        bigRobotArmData.getLowerArmMotor().setPower(lowerPower);
        TelemetryHelper.getTelemetry().addData("Lower m ticks:", bigRobotArmData.getLowerArmMotor().getCurrentPosition());

        //Upper arm control
        double upperPower = 0f;
        if(gamepad1.dpad_up) {
            upperPower = -1f;
        } else if(gamepad1.dpad_down) upperPower = 1f;
        else upperPower = 0f;
        bigRobotArmData.getUpperArmMotor().setPower(upperPower);
        TelemetryHelper.getTelemetry().addData("Upper m ticks:", bigRobotArmData.getUpperArmMotor().getCurrentPosition());


        //Cleshnja control
        if(gamepad1.square) cleshnjaClosed = false;
        if(gamepad1.circle) cleshnjaClosed = true;

        if(cleshnjaClosed) {
            bigRobotArmData.getClawServoLeft().setPosition(0);
            bigRobotArmData.getClawServoRight().setPosition(1);
        }
        else {
            bigRobotArmData.getClawServoLeft().setPosition(1);
            bigRobotArmData.getClawServoRight().setPosition(0);
        }
        bigRobotArmData.localize();
    }
}


