package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Common.TelemetryHelper;
import org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules.SmallRobotManualArmModule;
import org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules.SmallRobotManualDriveModule;

@TeleOp
public class ManualSmallRobotInitializer extends OpMode {
    SmallRobotManualDriveModule driveController;
    SmallRobotManualArmModule armController;
    @Override
    public void init() {
        TelemetryHelper.initTelemetry(telemetry);
        driveController = new SmallRobotManualDriveModule(hardwareMap, gamepad1);
        armController = new SmallRobotManualArmModule(hardwareMap, gamepad1);
    }

    @Override
    public void loop() {
        TelemetryHelper.update();
        driveController.controlLoop();
        armController.controlLoop();
    }
}
