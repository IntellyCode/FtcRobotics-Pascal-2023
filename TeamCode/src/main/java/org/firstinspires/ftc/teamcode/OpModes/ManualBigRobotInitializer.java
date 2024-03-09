package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.DecisionModules.BigRobotManualDrive;
import org.firstinspires.ftc.teamcode.DecisionModules.BigRobotManualBeltModule;
import org.firstinspires.ftc.teamcode.DecisionModules.BigRobotManualArmModule;
import org.firstinspires.ftc.teamcode.TelemetryHelper;

@TeleOp
public class ManualBigRobotInitializer extends OpMode {
    BigRobotManualDrive driveController;
    BigRobotManualArmModule armController;
    BigRobotManualBeltModule beltController;

    @Override
    public void init() {
        TelemetryHelper.initTelemetry(telemetry);
        driveController = new BigRobotManualDrive(hardwareMap, gamepad1);
        armController = new BigRobotManualArmModule(hardwareMap, gamepad2);
        beltController = new BigRobotManualBeltModule(hardwareMap, gamepad2);
    }

    @Override
    public void loop() {
        driveController.controlLoop();
        armController.controlLoop();
        beltController.controlLoop();
        TelemetryHelper.update();
    }
}
