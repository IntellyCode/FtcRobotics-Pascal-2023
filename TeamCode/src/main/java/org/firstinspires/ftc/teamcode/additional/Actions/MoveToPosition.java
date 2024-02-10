package org.firstinspires.ftc.teamcode.additional.Actions;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.additional.drive.SampleMecanumDrive;

public class MoveToPosition implements IAction {
    boolean isOver;
    Pose2d pos;
    SampleMecanumDrive drive;
    Trajectory trajectory;
    public MoveToPosition(SampleMecanumDrive drive, Pose2d pos) {
        this.drive = drive;
        this.pos = pos;
    }
    @Override
    public void start() {
        trajectory = drive.trajectoryBuilder(new Pose2d())
                .splineTo(pos.vec(), pos.getHeading()).
                build();
        drive.followTrajectory(trajectory);

    }

    @Override
    public void update() {
        if(!drive.isBusy()) isOver = true;
    }

    @Override
    public boolean isOver() {
        return isOver;
    }
}
