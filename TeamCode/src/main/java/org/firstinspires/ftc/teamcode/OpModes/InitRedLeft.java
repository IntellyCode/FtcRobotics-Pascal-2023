package org.firstinspires.ftc.teamcode.OpModes;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Camera.Data.Team;
import org.firstinspires.ftc.teamcode.Camera.Data.TeamPosition;
import org.firstinspires.ftc.teamcode.DataPackages.FieldConstants;


@TeleOp(name = "InitRedLeft", group = "Official")
public class InitRedLeft extends OpMode {

    AutonomousRoutine routine;
    Pose2d initialPose;
    @Override
    public void init() {
        initialPose = new Pose2d(FieldConstants.redRight.getX(),FieldConstants.redRight.getY(),Math.toRadians(90));
        routine = new AutonomousRoutine();
        routine.init(hardwareMap, telemetry, Team.red, TeamPosition.far);
    }

    @Override
    public void loop() {
        routine.loop();
    }
}