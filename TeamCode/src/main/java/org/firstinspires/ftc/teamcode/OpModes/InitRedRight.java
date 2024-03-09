package org.firstinspires.ftc.teamcode.OpModes;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Camera.Data.Team;
import org.firstinspires.ftc.teamcode.DataPackages.FieldConstants;
import org.firstinspires.ftc.teamcode.OpModes.Routines.RoutineRed;


@TeleOp(name = "InitRedRight", group = "Official")
public class InitRedRight extends OpMode {

    RoutineRed routine;
    Pose2d initialPose;
    @Override
    public void init() {
        initialPose = new Pose2d(FieldConstants.redRight.getX(),FieldConstants.redRight.getY(),Math.toRadians(90));
        routine = new RoutineRed();
        routine.init(hardwareMap, telemetry, Team.red);
    }

    @Override
    public void loop() {
        routine.loop();
    }
}