package org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.additional.DataPackages.BigRobotBeltData;

public class BigRobotManualBeltModule implements IDecisionModule {
    double currentPower;
    Gamepad gamepad;
    BigRobotBeltData bigRobotBeltData;
    public BigRobotManualBeltModule(HardwareMap hardwareMap, Gamepad gamepad) {
        this.gamepad = gamepad;
        this.bigRobotBeltData = new BigRobotBeltData(hardwareMap);
    }
    @Override
    public void controlLoop() {
        if(gamepad.right_bumper) {
            currentPower = -0.5f;
        }
        else if (gamepad.left_bumper)
            currentPower = 0.5f;
        else if(gamepad.share)
            currentPower = 0;
        bigRobotBeltData.getBeltMotor1().setPower(currentPower);
        bigRobotBeltData.getBeltMotor2().setPower(currentPower);
    }
}
