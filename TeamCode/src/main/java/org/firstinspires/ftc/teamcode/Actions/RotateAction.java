package org.firstinspires.ftc.teamcode.Actions;

import org.firstinspires.ftc.teamcode.Roadrunner.drive.SampleMecanumDrive;

public class RotateAction implements IAction{
    boolean isOver;
    double angleRad;
    SampleMecanumDrive drive;
    public RotateAction(SampleMecanumDrive drive, double angleRad) {
        this.drive = drive;
        this.angleRad = angleRad;
    }
    @Override
    public void start() {
        drive.turnAsync(angleRad);

    }

    @Override
    public void update() {
        if(!drive.isBusy()) isOver = true;
        drive.update();
    }

    @Override
    public boolean isOver() {
        return isOver;
    }
}
