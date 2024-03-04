package org.firstinspires.ftc.teamcode.OpModes.Testing;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.TelemetryHelper;
import org.firstinspires.ftc.teamcode.additional.DataPackages.BigRobotArmData;
import org.firstinspires.ftc.teamcode.additional.DataPackages.BigRobotDriveData;
import org.firstinspires.ftc.teamcode.additional.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.additional.Actions.*;

import java.util.ArrayList;

@TeleOp
public class ActionSequenceTest extends OpMode {
    BigRobotArmData armData;
    BigRobotDriveData driveData;
    SampleMecanumDrive drive;

    boolean sequstarted;
    boolean simlutstarted;
    IAction sequentialActionSequence;
    IAction parallelActionSequence;
    @Override
    public void init() {
        armData = new BigRobotArmData(hardwareMap);
        drive = new SampleMecanumDrive(hardwareMap);
        TelemetryHelper.initTelemetry(telemetry);
        sequentialActionSequence = new SequencedAction(
                new MoveToPosition(drive, new Pose2d(100, 0)),
                new LiftToAngleBigRobot(armData, 30),
                new ExtendTheArmAction(armData, 70)
        );
        parallelActionSequence = new SimultaneousAction(
                new MoveToPosition(drive, new Pose2d(100, 0)),
                new LiftToAngleBigRobot(armData, 30),
                new ExtendTheArmAction(armData, 70)
        );
    }

    @Override
    public void loop() {
        if(gamepad1.cross) {
            sequentialActionSequence.start();
            TelemetryHelper.getTelemetry().addData("Telemetry x", true);
            sequstarted = true;
        }
        if(gamepad1.circle) {
            parallelActionSequence.start();
            simlutstarted = true;
        }
        if(sequstarted)
            sequentialActionSequence.update();
        if(simlutstarted)
            parallelActionSequence.update();
        TelemetryHelper.getTelemetry().addData("parallelAction finished: ", parallelActionSequence.isOver());
    }
}
