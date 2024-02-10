package org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.additional.DataPackages.BigRobotArmData;
import org.firstinspires.ftc.teamcode.additional.DataPackages.SmallRobotArmData;

public class ManualBigRobotArmModule implements IDecisionModule {
    Gamepad gamepad1;
    BigRobotArmData bigRobotArmData;

    double armY;
    double cleshnjaY;
    public ManualBigRobotArmModule(HardwareMap map, Gamepad gamepad1) {
        bigRobotArmData = new BigRobotArmData(map);
        this.gamepad1 = gamepad1;
    }
    @Override
    public void controlLoop() {
        //Reading data
        double lowerPower = gamepad1.right_trigger - gamepad1.left_trigger;
        double upperPower = 0f;
        if(gamepad1.dpad_up) {
            upperPower = 1f;
        } else upperPower = 0;

        bigRobotArmData.getLowerArmMotor().setPower(lowerPower);
        bigRobotArmData.getUpperArmMotor().setPower(upperPower);

        if(gamepad1.left_bumper) cleshnjaY = -0.5;
        if(gamepad1.right_bumper) cleshnjaY = 0.5;
    }
}
