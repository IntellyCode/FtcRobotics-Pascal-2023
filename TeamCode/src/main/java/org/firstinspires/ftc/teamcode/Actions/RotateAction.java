package org.firstinspires.ftc.teamcode.Actions;

import org.firstinspires.ftc.teamcode.Roadrunner.drive.SampleMecanumDrive;

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
