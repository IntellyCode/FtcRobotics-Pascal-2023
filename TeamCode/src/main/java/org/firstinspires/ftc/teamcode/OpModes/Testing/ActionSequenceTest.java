package org.firstinspires.ftc.teamcode.OpModes.Testing;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Camera.Util.RelativePosition;
import org.firstinspires.ftc.teamcode.TelemetryHelper;
import org.firstinspires.ftc.teamcode.DataPackages.BigRobotArmData;
import org.firstinspires.ftc.teamcode.DataPackages.BigRobotDriveData;
import org.firstinspires.ftc.teamcode.Roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Actions.*;

@TeleOp
public class ActionSequenceTest extends OpMode {
    BigRobotArmData armData;
    BigRobotDriveData driveData;
    SampleMecanumDrive drive;

    boolean sequstarted;
    boolean simlutstarted;
    IAction sequentialActionSequence;
    IAction parallelActionSequence;
    IAction scanAndMoveAction;
    IAction moveRect;
    IAction rotate;
    IAction moveLine;
    @Override
    public void init() {
        armData = new BigRobotArmData(hardwareMap);
        drive = new SampleMecanumDrive(hardwareMap);
        TelemetryHelper.initTelemetry(telemetry);
        sequentialActionSequence = new SequencedAction(
                new MoveToPositionRectangular(drive, new Pose2d(100, 100), Axis.Horizontal),
                new LiftToAngleBigRobot(armData, 30),
                new ExtendTheArmAction(armData, 70)
        );
        rotate = new RotateAction(drive, Math.toRadians(90));
//        parallelActionSequence = new SimultaneousAction(
//                new MoveToPositionDiagonal(drive, new Pose2d(100, 0)),
//                new LiftToAngleBigRobot(armData, 30),
//                new ExtendTheArmAction(armData, 70)
//        );
    }

    @Override
    public void loop() {
        if(gamepad1.cross) {
            scanAndMoveAction = new ScanAndMove(drive, RelativePosition.left);
            scanAndMoveAction.start();
        }
        if(gamepad1.circle) {
            scanAndMoveAction = new ScanAndMove(drive, RelativePosition.right);
            scanAndMoveAction.start();
        }
        if(gamepad1.triangle) {
            scanAndMoveAction = new ScanAndMove(drive, RelativePosition.center);
            scanAndMoveAction.start();
        }
        if(gamepad1.square) {
            rotate.start();
        }
        rotate.update();
        if(sequstarted)
            sequentialActionSequence.update();
        if(simlutstarted)
            parallelActionSequence.update();
        if(moveRect != null && !moveRect.isOver())
            moveRect.update();
        if(moveLine != null && !moveLine.isOver())
            moveLine.update();
        if(scanAndMoveAction != null)
            scanAndMoveAction.update();
//        TelemetryHelper.getTelemetry().addData("parallelAction finished: ", parallelActionSequence.isOver());
        TelemetryHelper.update();
    }
}
