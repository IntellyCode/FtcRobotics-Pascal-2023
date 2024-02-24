package org.firstinspires.ftc.teamcode.additional.Initializers;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules.BigRobotManualDrive;
import org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules.BigRobotManualBeltModule;
import org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules.BigRobotManualArmModule;
import org.firstinspires.ftc.teamcode.Common.TelemetryHelper;

@TeleOp
public class ManualBigRobotInitializer extends OpMode {
    BigRobotManualDrive driveController;
    BigRobotManualArmModule armController;
    BigRobotManualBeltModule beltController;

    @Override
    public void init() {
        TelemetryHelper.initTelemetry(telemetry);
        driveController = new BigRobotManualDrive(hardwareMap, gamepad1);
        armController = new BigRobotManualArmModule(hardwareMap, gamepad1);
        beltController = new BigRobotManualBeltModule(hardwareMap, gamepad1);
    }

    @Override
    public void loop() {
        driveController.controlLoop();
        armController.controlLoop();
        beltController.controlLoop();
        TelemetryHelper.update();
    }
}
