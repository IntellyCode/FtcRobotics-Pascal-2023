package org.firstinspires.ftc.teamcode.OpModes.Testing;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Actions.Axis;
import org.firstinspires.ftc.teamcode.Actions.IAction;
import org.firstinspires.ftc.teamcode.Actions.MoveToPositionLine;
import org.firstinspires.ftc.teamcode.Actions.MoveToPositionRectangular;
import org.firstinspires.ftc.teamcode.Actions.PickupThePixelAction;
import org.firstinspires.ftc.teamcode.Actions.PutThePixelAction;
import org.firstinspires.ftc.teamcode.Actions.ReleasePixelAction;
import org.firstinspires.ftc.teamcode.Actions.RotateAction;
import org.firstinspires.ftc.teamcode.Actions.SequencedAction;
import org.firstinspires.ftc.teamcode.Actions.SwitchBeltAction;
import org.firstinspires.ftc.teamcode.DataPackages.BigRobotArmData;
import org.firstinspires.ftc.teamcode.DataPackages.BigRobotBeltData;
import org.firstinspires.ftc.teamcode.DataPackages.FieldConstants;
import org.firstinspires.ftc.teamcode.Roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.TelemetryHelper;

public class CoordinateTest extends OpMode {
    SampleMecanumDrive drive;
    BigRobotArmData armData;
    BigRobotBeltData beltData;
    MoveToPositionRectangular moveToPosition;
    IAction rotate;

    @Override
    public void init() {
        drive.setPoseEstimate(new Pose2d(FieldConstants.redRight, Math.toRadians(90)));
        beltData = new BigRobotBeltData(hardwareMap);
        TelemetryHelper.initTelemetry(telemetry);
        drive = new SampleMecanumDrive(hardwareMap);
        moveToPosition = new MoveToPositionRectangular(drive, new Pose2d(0, 0), Axis.Vertical);
    }

    @Override
    public void loop() {
        telemetry.addData("currPosx", drive.getPoseEstimate().getX());
        telemetry.addData("currPosy", drive.getPoseEstimate().getY());
        telemetry.addData("Current pos x:", drive.getPoseEstimate().getX());
        telemetry.addData("Current pos y:", drive.getPoseEstimate().getY());

        if(gamepad1.cross)
            moveToPosition.start();
        moveToPosition.update();
    }
}