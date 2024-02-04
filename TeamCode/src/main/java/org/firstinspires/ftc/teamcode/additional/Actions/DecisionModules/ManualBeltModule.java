package org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.additional.DataPackages.BeltData;

public class ManualBeltModule implements IDecisionModule {
    Gamepad gamepad1;
    BeltData beltData;
    public ManualBeltModule(HardwareMap hardwareMap, Gamepad gamepad1) {
        this.gamepad1 = gamepad1;
        this.beltData = new BeltData(hardwareMap);
    }
    @Override
    public void controlLoop() {
        if(gamepad1.right_bumper) {
            beltData.getBeltMotor1().setPower(0.5);
            beltData.getBeltMotor2().setPower(0.5);
        }
        if(gamepad1.left_bumper) {
            beltData.getBeltMotor1().setPower(-0.5);
            beltData.getBeltMotor2().setPower(-0.5);
        }
    }
}
