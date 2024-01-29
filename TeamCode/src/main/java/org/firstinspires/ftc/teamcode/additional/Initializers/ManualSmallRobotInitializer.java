package org.firstinspires.ftc.teamcode.additional.Initializers;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules.ManualArmModule;
import org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules.ManualDriveModule;

@TeleOp
public class ManualSmallRobotInitializer extends OpMode {
    ManualDriveModule driveController;
    ManualArmModule armController;
    @Override
    public void init() {
        driveController = new ManualDriveModule(hardwareMap, gamepad1);
        armController = new ManualArmModule(hardwareMap, gamepad1);
    }

    @Override
    public void loop() {
        driveController.controlLoop();
        armController.controlLoop();
    }
}
