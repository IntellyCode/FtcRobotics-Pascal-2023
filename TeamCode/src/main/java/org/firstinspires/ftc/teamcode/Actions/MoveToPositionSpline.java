package org.firstinspires.ftc.teamcode.Actions;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.TelemetryHelper;
import org.firstinspires.ftc.teamcode.Roadrunner.drive.SampleMecanumDrive;

public class MoveToPositionSpline implements IAction {
    boolean isOver;
    Pose2d startingPosition;
    Pose2d target;
    SampleMecanumDrive drive;
    Trajectory trajectory;
    public MoveToPositionSpline(SampleMecanumDrive drive, Pose2d startingPosition, Pose2d target) {
        this.drive = drive;
        this.startingPosition = startingPosition;
        this.target = target;
    }
    @Override
    public void start() {
        trajectory = drive.trajectoryBuilder(startingPosition)
                .splineTo(target.vec(), target.getHeading()).
                build();
        drive.followTrajectoryAsync(trajectory);

    }

    @Override
    public void update() {
        if(!drive.isBusy()) isOver = true;
        drive.update();
        TelemetryHelper.getTelemetry().addData("Target x:", target.getX());
        TelemetryHelper.getTelemetry().addData("Target y:", target.getX());
    }

    @Override
    public boolean isOver() {
        return isOver;
    }
}
