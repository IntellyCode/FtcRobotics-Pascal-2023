package org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.additional.DataPackages.ArmData;

public class ManualArmModule implements IDecisionModule {
    Gamepad gamepad1;
    ArmData armData;

    double armY;
    double cleshnjaY;
    public ManualArmModule(HardwareMap map, Gamepad gamepad1) {
        armData = new ArmData(map);
        this.gamepad1 = gamepad1;
    }
    @Override
    public void controlLoop() {
        //Reading data
        armY = gamepad1.right_trigger - gamepad1.left_trigger;

        armData.getRightArmMotor().setPower(armY);
        armData.getLeftArmMotor().setPower(armY);

        if(gamepad1.left_bumper) cleshnjaY = -0.5;
        if(gamepad1.right_bumper) cleshnjaY = 0.5;
    }
}
