package org.firstinspires.ftc.teamcode.OpModes;

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
import org.firstinspires.ftc.teamcode.Camera.Data.TeamPosition;
import org.firstinspires.ftc.teamcode.Camera.Util.RelativePosition;
import org.firstinspires.ftc.teamcode.DataPackages.BigRobotArmData;
import org.firstinspires.ftc.teamcode.DataPackages.BigRobotBeltData;
import org.firstinspires.ftc.teamcode.DataPackages.FieldConstants;
import org.firstinspires.ftc.teamcode.Roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.TelemetryHelper;

public class AutonomousRoutine {
    Camera camera;
    Team team;
    RelativePosition rlp;
    SampleMecanumDrive drive;
    BigRobotArmData armData;
    BigRobotBeltData beltData;
    IAction finalSequence;

    int coordinateModifier;

    IAction farSequence;

    Pose2d initPos;
    public void init(HardwareMap hardwareMap, Telemetry telemetry, Team team, TeamPosition teamPos){
        this.team = team;
        if (this.team == Team.blue){
            coordinateModifier = 1;
        } else{
            coordinateModifier = -1;
        }

        armData = new BigRobotArmData(hardwareMap);
        beltData = new BigRobotBeltData(hardwareMap);
        drive = new SampleMecanumDrive(hardwareMap);
        if (teamPos == TeamPosition.far){
            farSequence = new MoveToPositionLine(drive,drive.getPoseEstimate(),new Pose2d(0.2*FieldConstants.cmPerBlock,2*FieldConstants.cmPerBlock*coordinateModifier));
            initPos = new Pose2d(0.2*FieldConstants.cmPerBlock,2*FieldConstants.cmPerBlock*coordinateModifier);
        } else{
            farSequence = new WaitAction(0);
            initPos = drive.getPoseEstimate();
        }
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
                    new SimultaneousAction(
                            new WaitAction(100),
                            new PickupThePixelAction(armData,true,false)
                    ),
                    new RotateAction(drive, Math.toRadians(angleRot)),
                    new LiftToAngleBigRobot(armData,15),
                    new ExtendTheArmAction(armData, 100),
                    new LiftToAngleBigRobot(armData,0),
                    new SimultaneousAction(
                            new WaitAction(100),
                            new ReleasePixelAction(armData, true, false)
                    ),

                    new LiftToAngleBigRobot(armData,15),
                    new ExtendTheArmAction(armData, armData.initialArmLength),
                    new LiftToAngleBigRobot(armData,0),
                    new RotateAction(drive, Math.toRadians(-angleRot)),
                    new SimultaneousAction(
                            new WaitAction(50),
                            new PickupThePixelAction(armData,false,true)
                    ),
                    farSequence,
                    new MoveToPositionLine(drive,initPos,new Pose2d(1* FieldConstants.cmPerBlock,initPos.getY())),
                    new MoveToPositionLine(drive,new Pose2d(1*FieldConstants.cmPerBlock,initPos.getY()),new Pose2d(1*FieldConstants.cmPerBlock,1.4*FieldConstants.cmPerBlock*coordinateModifier)),
                    new RotateAction(drive,Math.toRadians(90)),
                    new LiftToAngleBigRobot(armData,30),
                    new ExtendTheArmAction(armData, 100),
                    new ReleasePixelAction(armData, false, true),
                    new SimultaneousAction(
                            new ExtendTheArmAction(armData, armData.initialArmLength),
                            new LiftToAngleBigRobot(armData,15,0.1)
                    ),
                    new LiftToAngleBigRobot(armData,0),
                    new MoveToPositionLine(drive,new Pose2d(1*FieldConstants.cmPerBlock,1.4*FieldConstants.cmPerBlock*coordinateModifier),new Pose2d(0,1.4*FieldConstants.cmPerBlock*coordinateModifier)),
                    new MoveToPositionLine(drive,new Pose2d(0,1.4*FieldConstants.cmPerBlock*coordinateModifier),new Pose2d(0,2*FieldConstants.cmPerBlock*coordinateModifier))

            );
            finalSequence.start();
        }else {
            armData.localize();
            finalSequence.update();
        }
        TelemetryHelper.getDashboardTelemetry().put("Position",rlp);
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
