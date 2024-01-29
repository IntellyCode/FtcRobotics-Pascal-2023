package org.firstinspires.ftc.teamcode.additional.Initializers;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.additional.drive.SampleMecanumDrive;

@Autonomous
public class TrajectoryFollower extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Trajectory trajectory = drive.trajectoryBuilder(new Pose2d())
                .splineTo(new Vector2d(cmToInch(100), cmToInch(100)), Math.toRadians(90)).
                build();

        waitForStart();

        drive.followTrajectory(trajectory);

        if(isStopRequested()) return;
    }

    static double cmToInch(double cm) {
        return cm * 0.393701;
    }
}
