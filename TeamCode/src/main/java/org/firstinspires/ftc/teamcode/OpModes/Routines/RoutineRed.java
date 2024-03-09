package org.firstinspires.ftc.teamcode.OpModes.Routines;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Actions.ExtendTheArmAction;
import org.firstinspires.ftc.teamcode.Actions.IAction;
import org.firstinspires.ftc.teamcode.Actions.LiftToAngleBigRobot;
import org.firstinspires.ftc.teamcode.Actions.MoveToPositionLine;
import org.firstinspires.ftc.teamcode.Actions.OpenSweeper;
import org.firstinspires.ftc.teamcode.Actions.PickupThePixelAction;
import org.firstinspires.ftc.teamcode.Actions.ReleasePixelAction;
import org.firstinspires.ftc.teamcode.Actions.RotateAction;
import org.firstinspires.ftc.teamcode.Actions.SequencedAction;
import org.firstinspires.ftc.teamcode.Actions.SimultaneousAction;
import org.firstinspires.ftc.teamcode.Actions.SwitchBeltAction;
import org.firstinspires.ftc.teamcode.Actions.WaitAction;
import org.firstinspires.ftc.teamcode.Camera.Camera;
import org.firstinspires.ftc.teamcode.Camera.Data.Team;
import org.firstinspires.ftc.teamcode.Camera.Util.RelativePosition;
import org.firstinspires.ftc.teamcode.DataPackages.BigRobotArmData;
import org.firstinspires.ftc.teamcode.DataPackages.BigRobotBeltData;
import org.firstinspires.ftc.teamcode.DataPackages.FieldConstants;
import org.firstinspires.ftc.teamcode.Roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.TelemetryHelper;


public class RoutineRed {
    Camera camera;
    Team team;
    RelativePosition rlp;
    SampleMecanumDrive drive;
    BigRobotArmData armData;
    BigRobotBeltData beltData;
    IAction finalSequence;

    public void init(HardwareMap hardwareMap, Telemetry telemetry, Team team){
        this.team = team;
        armData = new BigRobotArmData(hardwareMap);
        beltData = new BigRobotBeltData(hardwareMap);
        drive = new SampleMecanumDrive(hardwareMap);
        camera =new Camera(hardwareMap,team);
        TelemetryHelper.initTelemetry(telemetry);
        camera.start();
    }

    public void loop() {
        if (rlp==null) {
            rlp = camera.getPipeline().getRelPosOfProp();
            double angleRot = getAngle(rlp);
            TelemetryHelper.getDashboardTelemetry().put("Angle", angleRot);
           finalSequence = new SequencedAction(
                    new OpenSweeper(armData,false),
                    new SwitchBeltAction(beltData,true),
                    new WaitAction(200),
                    new SimultaneousAction(
                            new WaitAction(100),
                            new PickupThePixelAction(armData,true,false)
                    ),
                    new RotateAction(drive, Math.toRadians(angleRot)),
                    new LiftToAngleBigRobot(armData,15),
                    new ExtendTheArmAction(armData, 100),
                    new LiftToAngleBigRobot(armData,0),
                    new ReleasePixelAction(armData, true, false),
                    new LiftToAngleBigRobot(armData,15),
                    new ExtendTheArmAction(armData, armData.initialArmLength),
                    new LiftToAngleBigRobot(armData,0),
                    new RotateAction(drive, Math.toRadians(-angleRot)),
                    new SimultaneousAction(
                            new WaitAction(100),
                            new PickupThePixelAction(armData,false,true)
                    ),
                   new MoveToPositionLine(drive,drive.getPoseEstimate(),new Pose2d(2* FieldConstants.cmPerBlock,drive.getPoseEstimate().getY())),
                   new MoveToPositionLine(drive,new Pose2d(2*FieldConstants.cmPerBlock,drive.getPoseEstimate().getY()),new Pose2d(2*FieldConstants.cmPerBlock,-1*FieldConstants.cmPerBlock)),
                   new RotateAction(drive,Math.toRadians(-90)),
                    new LiftToAngleBigRobot(armData,45),
                    new ExtendTheArmAction(armData, 100),
                    new ReleasePixelAction(armData, false, true),
                    new SimultaneousAction(
                            new ExtendTheArmAction(armData, armData.initialArmLength),
                            new LiftToAngleBigRobot(armData,15,0.1)
                    ),
                    new LiftToAngleBigRobot(armData,0),
                   new MoveToPositionLine(drive,new Pose2d(2*FieldConstants.cmPerBlock,-1*FieldConstants.cmPerBlock),new Pose2d(1*FieldConstants.cmPerBlock,-1*FieldConstants.cmPerBlock)),
                   new MoveToPositionLine(drive,new Pose2d(1*FieldConstants.cmPerBlock,-1*FieldConstants.cmPerBlock),new Pose2d(1*FieldConstants.cmPerBlock,-2*FieldConstants.cmPerBlock))

            );
            finalSequence.start();
        }else {
            armData.localize();
            finalSequence.update();
        }
        TelemetryHelper.getDashboardTelemetry().put("Position",rlp);
        TelemetryHelper.getTelemetry().addData("x:", drive.getPoseEstimate().getX());
        TelemetryHelper.getTelemetry().addData("y:", drive.getPoseEstimate().getY());
        TelemetryHelper.update();
    }

    double getAngle(RelativePosition rlp) {
        switch(rlp) {
            case left:
                return 1* FieldConstants.initRotAngle;
            case right:
                return -1*FieldConstants.initRotAngle;
            case center:
                return 0;
        }
        return 0;
    }

}
