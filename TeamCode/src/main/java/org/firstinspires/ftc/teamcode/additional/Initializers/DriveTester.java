package org.firstinspires.ftc.teamcode.additional.Initializers;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Common.TelemetryHelper;
import org.firstinspires.ftc.teamcode.additional.Actions.IAction;
import org.firstinspires.ftc.teamcode.additional.Actions.MoveToPosition;
import org.firstinspires.ftc.teamcode.additional.Actions.RotateAction;
import org.firstinspires.ftc.teamcode.additional.drive.SampleMecanumDrive;

@Autonomous
public class DriveTester extends OpMode {
    IAction action;
    SampleMecanumDrive drive;

    @Override
    public void init() {
        TelemetryHelper.initTelemetry(telemetry);
        drive = new SampleMecanumDrive(hardwareMap);
    }

    @Override
    public void loop() {
        if(gamepad1.cross) {
            action = new MoveToPosition(drive, new Pose2d(300, 0, 0));
            action.start();
        }
        if(gamepad1.circle) {
            action = new RotateAction(drive, Math.toRadians(360));
            action.start();
        }
        if(action != null) {
            TelemetryHelper.getTelemetry().addData("Is finished", action.isOver());
            action.update();
        }
        drive.update();
        drive.update();
        TelemetryHelper.update();
    }
}
