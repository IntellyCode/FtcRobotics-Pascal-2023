package org.firstinspires.ftc.teamcode.additional.Initializers;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules.ManualBeltModule;
import org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules.ManualDriveModule;
import org.firstinspires.ftc.teamcode.TelemetryHelper;

@TeleOp

public class ManualBigRobotInitializer extends OpMode {
    ManualDriveModule driveController;
    ManualBeltModule beltController;
    //ManualArmModule armController;
    @Override
    public void init() {
        TelemetryHelper.initTelemetry(telemetry);
        driveController = new ManualDriveModule(hardwareMap, gamepad1);
        beltController = new ManualBeltModule(hardwareMap, gamepad1);
        //armController = new ManualArmModule(hardwareMap, gamepad1);
    }

    @Override
    public void loop() {
        driveController.controlLoop();
        beltController.controlLoop();
        TelemetryHelper.update();
        //armController.controlLoop();
    }
}
