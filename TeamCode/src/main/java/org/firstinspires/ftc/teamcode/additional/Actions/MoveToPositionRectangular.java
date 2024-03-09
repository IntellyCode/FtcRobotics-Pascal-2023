package org.firstinspires.ftc.teamcode.additional.Actions;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.additional.drive.SampleMecanumDrive;

public class MoveToPositionRectangular implements IAction {
    Axis priorityAxis;
    boolean isOver;
    Pose2d pos;
    SampleMecanumDrive drive;
    IAction complexTraj;
    public MoveToPositionRectangular(SampleMecanumDrive drive, Pose2d pos, Axis priorityAxis) {
        this.priorityAxis = priorityAxis;
        if(priorityAxis == Axis.Horizontal)
        {
            complexTraj = new SequencedAction(
                    new MoveToPositionLine(drive, drive.getPoseEstimate(), new Pose2d(drive.getPoseEstimate().getX(), pos.getY())),
                    new MoveToPositionLine(drive, new Pose2d(drive.getPoseEstimate().getX(), pos.getY()), new Pose2d(pos.getX(), pos.getY()))
                    );
        }
        if(priorityAxis == Axis.Vertical)
        {
            complexTraj = new SequencedAction(
                    new MoveToPositionLine(drive, drive.getPoseEstimate(), new Pose2d(pos.getX(), drive.getPoseEstimate().getY())),
                    new MoveToPositionLine(drive, new Pose2d(pos.getX(), drive.getPoseEstimate().getY()), new Pose2d(pos.getX(), pos.getY()))
                    );
        }

        this.drive = drive;
        this.pos = pos;
    }
    @Override
    public void start() {
        complexTraj.start();
    }

    @Override
    public void update() {
        if(complexTraj.isOver()) isOver = true;
        if(isOver) return;
        complexTraj.update();
    }

    @Override
    public boolean isOver() {
        return isOver;
    }
}

