package org.firstinspires.ftc.teamcode.OpModes.Testing;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.TelemetryHelper;
import org.firstinspires.ftc.teamcode.Actions.Axis;
import org.firstinspires.ftc.teamcode.Actions.IAction;
import org.firstinspires.ftc.teamcode.Actions.MoveToPositionLine;
import org.firstinspires.ftc.teamcode.Actions.MoveToPositionRectangular;
import org.firstinspires.ftc.teamcode.Actions.PutThePixelAction;
import org.firstinspires.ftc.teamcode.Actions.PickupThePixelAction;
import org.firstinspires.ftc.teamcode.Actions.ReleasePixelAction;
import org.firstinspires.ftc.teamcode.Actions.RotateAction;
import org.firstinspires.ftc.teamcode.Actions.SequencedAction;
import org.firstinspires.ftc.teamcode.Actions.SwitchBeltAction;
import org.firstinspires.ftc.teamcode.DataPackages.BigRobotArmData;
import org.firstinspires.ftc.teamcode.DataPackages.BigRobotBeltData;
import org.firstinspires.ftc.teamcode.DataPackages.FieldConstants;
import org.firstinspires.ftc.teamcode.Roadrunner.drive.SampleMecanumDrive;

@Autonomous
public class AutonomousTesting extends OpMode {
    SampleMecanumDrive drive;
    BigRobotArmData armData;
    BigRobotBeltData beltData;
    MoveToPositionRectangular moveToPosition;
    PutThePixelAction putThePixelAction;
    PickupThePixelAction pickupThePixelAction;
    ReleasePixelAction releasePixelAction;
    SequencedAction pixelAction;
    @Override
    public void init() {
        beltData = new BigRobotBeltData(hardwareMap);
        TelemetryHelper.initTelemetry(telemetry);
        drive = new SampleMecanumDrive(hardwareMap);
        //drive.setPoseEstimate(new Pose2d(FieldConstants.redStation, 0));
        armData = new BigRobotArmData(hardwareMap);
        double x = FieldConstants.blueBoard.getX();
        double y = FieldConstants.blueBoard.getY();
        moveToPosition = new MoveToPositionRectangular(drive, new Pose2d(x, y), Axis.Vertical);
        putThePixelAction = new PutThePixelAction(drive, armData, new Pose2d(50, 10, 0), true, true);
        pickupThePixelAction = new PickupThePixelAction(armData, true, true);
        releasePixelAction = new ReleasePixelAction(armData, true, true);

        //The action
        IAction activateBelts = new SwitchBeltAction(beltData, true);
        IAction rotateAction = new RotateAction(drive, (180));
        IAction moveToPixel = new MoveToPositionLine(drive, drive.getPoseEstimate(), new Pose2d(50, 0));
        IAction pickupThePixel = new PickupThePixelAction(armData, true, true);
        IAction placeThePixel = new PutThePixelAction(drive, armData, new Pose2d(50, 0), true, true);

        pixelAction = new SequencedAction(activateBelts, moveToPixel, rotateAction, pickupThePixel, placeThePixel);

    }

    @Override
    public void loop() {
        TelemetryHelper.update();
        if(gamepad1.cross)
           moveToPosition.start();
        if(!moveToPosition.isOver())
            moveToPosition.update();
        if(gamepad1.circle) {
            pickupThePixelAction.start();
        }
        if(gamepad1.triangle) {
            releasePixelAction.start();
        }
        if(gamepad1.square) {
            putThePixelAction.start();
        }

        releasePixelAction.update();
        pickupThePixelAction.update();
        putThePixelAction.update();
        pixelAction.update();
        telemetry.addData("currPosx", drive.getPoseEstimate().getX());
        telemetry.addData("currPosy", drive.getPoseEstimate().getY());
        telemetry.addData("Current pos x:", drive.getPoseEstimate().getX());
        telemetry.addData("Current pos y:", drive.getPoseEstimate().getY());
        telemetry.addData("Right claw pos", armData.getClawServoRight().getPosition());
        telemetry.addData("Left claw pos", armData.getClawServoLeft().getPosition());
    }
}
