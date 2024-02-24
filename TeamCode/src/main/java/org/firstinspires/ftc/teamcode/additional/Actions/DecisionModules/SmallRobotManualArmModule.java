package org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.additional.DataPackages.SmallRobotArmData;

public class SmallRobotManualArmModule implements IDecisionModule {
    Gamepad gamepad1;
    SmallRobotArmData smallRobotArmData;

    double armY;
    double cleshnjaY;
    public SmallRobotManualArmModule(HardwareMap map, Gamepad gamepad1) {
        smallRobotArmData = new SmallRobotArmData(map);
        this.gamepad1 = gamepad1;
    }
    @Override
    public void controlLoop() {
        //Reading data
        armY = gamepad1.right_trigger - gamepad1.left_trigger;

        smallRobotArmData.getRightArmMotor().setPower(armY);
        smallRobotArmData.getLeftArmMotor().setPower(armY);

        if(gamepad1.left_bumper) cleshnjaY = -0.5;
        if(gamepad1.right_bumper) cleshnjaY = 0.5;
    }
}
