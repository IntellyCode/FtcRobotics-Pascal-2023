package org.firstinspires.ftc.teamcode.additional.Initializers;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.additional.drive.SampleMecanumDrive;


//Это тест для траекторий
public class TrajectoryTest extends OpMode {
    SampleMecanumDrive drive;
    Trajectory backAndForth1; //Эта траектория может выбросить PathContinuityException (возможно)
    Trajectory lineTo;
    @Override
    public void init() {
        drive = new SampleMecanumDrive(hardwareMap);

        backAndForth1 = drive.trajectoryBuilder(new Pose2d())
                .forward(60)
                .back(60)
                .build();


        drive.trajectoryBuilder(new Pose2d()).lineTo(new Vector2d(40, 40))
                .build();
    }

    @Override
    public void loop() {
        drive.followTrajectory(lineTo); //Запуск конкретной траектории
    }
}
