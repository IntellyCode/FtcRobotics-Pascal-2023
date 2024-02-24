package org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.additional.DataPackages.BigRobotBeltData;

public class BigRobotManualBeltModule implements IDecisionModule {
    double currentPower;
    Gamepad gamepad1;
    BigRobotBeltData bigRobotBeltData;
    public BigRobotManualBeltModule(HardwareMap hardwareMap, Gamepad gamepad1) {
        this.gamepad1 = gamepad1;
        this.bigRobotBeltData = new BigRobotBeltData(hardwareMap);
    }
    @Override
    public void controlLoop() {
        if(gamepad1.right_bumper) {
            currentPower = -0.5f;
        }
        else if (gamepad1.left_bumper)
            currentPower = 0.5f;
        else if(gamepad1.share)
            currentPower = 0;
        bigRobotBeltData.getBeltMotor1().setPower(currentPower);
        bigRobotBeltData.getBeltMotor2().setPower(currentPower);
    }
}
