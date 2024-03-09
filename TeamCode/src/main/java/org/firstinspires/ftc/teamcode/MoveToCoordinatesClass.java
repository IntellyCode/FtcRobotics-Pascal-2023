package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Roadrunner.drive.SampleMecanumDrive;

public class MoveToCoordinatesClass {
    SampleMecanumDrive drive;
    Trajectory currTrajectory;

    public MoveToCoordinatesClass(SampleMecanumDrive drive) {
        this.drive = drive;
    }
    void moveToCoordinates(Vector2d coordinates) {
        if(isBusy()) return;
        currTrajectory = drive.trajectoryBuilder(new Pose2d())
                .splineTo(coordinates, Math.toRadians(90)).
                build();
        currTrajectory.start();
        drive.followTrajectory(currTrajectory);
    }

    boolean isBusy() {
        return drive.isBusy();
    }
}
