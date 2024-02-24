package org.firstinspires.ftc.teamcode.additional.Actions;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.additional.drive.SampleMecanumDrive;

public class RotateAction implements IAction{
    boolean isOver;
    double angle;
    SampleMecanumDrive drive;
    public RotateAction(SampleMecanumDrive drive, double angle) {
        this.drive = drive;
        this.angle = angle;
    }
    @Override
    public void start() {
        drive.turnAsync(angle);
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
