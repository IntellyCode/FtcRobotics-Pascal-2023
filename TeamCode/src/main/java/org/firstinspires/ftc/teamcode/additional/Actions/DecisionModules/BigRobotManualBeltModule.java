package org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.additional.DataPackages.BigRobotBeltData;

public class BigRobotManualBeltModule implements IDecisionModule {
    Gamepad gamepad1;
    BigRobotBeltData bigRobotBeltData;
    public BigRobotManualBeltModule(HardwareMap hardwareMap, Gamepad gamepad1) {
        this.gamepad1 = gamepad1;
        this.bigRobotBeltData = new BigRobotBeltData(hardwareMap);
    }
    @Override
    public void controlLoop() {
        bigRobotBeltData.getBeltMotor1().setPower(0);
        bigRobotBeltData.getBeltMotor2().setPower(0);
        if(gamepad1.left_bumper) {
            bigRobotBeltData.getBeltMotor1().setPower(0.5);
            bigRobotBeltData.getBeltMotor2().setPower(0.5);
        }
        if(gamepad1.right_bumper) {
            bigRobotBeltData.getBeltMotor1().setPower(-0.5);
            bigRobotBeltData.getBeltMotor2().setPower(-0.5);
        }
    }
}
