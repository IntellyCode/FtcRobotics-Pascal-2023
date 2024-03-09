package org.firstinspires.ftc.teamcode.additional.Actions;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.Camera.Util.ColorDetection;
import org.firstinspires.ftc.teamcode.Camera.Util.RelativePosition;
import org.firstinspires.ftc.teamcode.additional.drive.SampleMecanumDrive;

public class ScanAndMove implements IAction
{
    boolean isFinished;
    SampleMecanumDrive drive;
    RelativePosition position;
    IAction movementRoutine;

    public ScanAndMove(SampleMecanumDrive drive, RelativePosition position) {
        this.drive = drive;
        this.position = position;
    }

    @Override
    public void start() {
        switch (position){
            case center:
                movementRoutine = new MoveToPositionLine(drive, drive.getPoseEstimate(), new Pose2d(100, 0));
                break;
            case left:
                movementRoutine = new MoveToPositionRectangular(drive, new Pose2d(100, 100), Axis.Horizontal);
                break;
            case right:
                movementRoutine = new MoveToPositionRectangular(drive, new Pose2d(100, -100), Axis.Horizontal);
                break;
        }
        movementRoutine.start();
    }

    @Override
    public void update() {
        if(movementRoutine.isOver()) isFinished = true;
        if(isFinished) return;
        movementRoutine.update();
    }

    @Override
    public boolean isOver() {
        return isFinished;
    }
}
