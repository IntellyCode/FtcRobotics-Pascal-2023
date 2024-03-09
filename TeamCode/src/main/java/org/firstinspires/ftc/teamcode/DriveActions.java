package org.firstinspires.ftc.teamcode;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Roadrunner.drive.SampleMecanumDrive;

public class DriveActions {
    SampleMecanumDrive drive;
    Trajectory currTrajectory;
   Pose2d currentPosition;

    DriveActions(SampleMecanumDrive drive, Pose2d startingPosition) {
        this.drive = drive;
        this.currentPosition = startingPosition;
    }

    void MoveToCoordinates(Vector2d coordinates, double heading) {
        if(drive.isBusy()) return;
        if(currTrajectory != null) currentPosition = currTrajectory.end();
        currTrajectory = drive.trajectoryBuilder(currentPosition).splineTo(coordinates, heading).build();
        drive.followTrajectory(currTrajectory);
    }

    boolean isFinished() {
        return drive.isBusy();
    }
}