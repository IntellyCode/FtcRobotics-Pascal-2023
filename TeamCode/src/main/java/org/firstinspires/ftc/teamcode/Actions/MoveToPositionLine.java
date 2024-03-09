package org.firstinspires.ftc.teamcode.Actions;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.TelemetryHelper;
import org.firstinspires.ftc.teamcode.Roadrunner.drive.SampleMecanumDrive;

public class MoveToPositionLine implements IAction {
    boolean isOver;
    Pose2d targetPos;
    Pose2d currentPos;
    SampleMecanumDrive drive;
    Trajectory trajectory;
    public MoveToPositionLine(SampleMecanumDrive drive, Pose2d currentPos, Pose2d targetPos) {
        this.drive = drive;
        this.targetPos = targetPos;
        this.currentPos = currentPos;
    }
    @Override
    public void start() {
        trajectory = drive.trajectoryBuilder(currentPos)
                .lineTo(targetPos.vec()).
                build();
        drive.followTrajectoryAsync(trajectory);
    }

    @Override
    public void update() {
        if(!drive.isBusy()) isOver = true;
        drive.update();
        TelemetryHelper.getDashboardTelemetry().put("Target x:", targetPos.getX());
        TelemetryHelper.getDashboardTelemetry().put("Target y:", targetPos.getY());
    }

    @Override
    public boolean isOver() {
        return isOver;
    }
}
