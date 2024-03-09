package org.firstinspires.ftc.teamcode.OpModes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Actions.ExtendTheArmAction;
import org.firstinspires.ftc.teamcode.Actions.LiftToAngleBigRobot;
import org.firstinspires.ftc.teamcode.Actions.OpenSweeper;
import org.firstinspires.ftc.teamcode.Actions.PickupThePixelAction;
import org.firstinspires.ftc.teamcode.Actions.ReleasePixelAction;
import org.firstinspires.ftc.teamcode.Actions.RotateAction;
import org.firstinspires.ftc.teamcode.Actions.SequencedAction;
import org.firstinspires.ftc.teamcode.Actions.SimultaneousAction;
import org.firstinspires.ftc.teamcode.Actions.SwitchBeltAction;
import org.firstinspires.ftc.teamcode.Actions.WaitAction;
import org.firstinspires.ftc.teamcode.Camera.Camera;
import org.firstinspires.ftc.teamcode.Camera.Util.RelativePosition;
import org.firstinspires.ftc.teamcode.Camera.Data.Coordinates;
import org.firstinspires.ftc.teamcode.Camera.Data.Team;
import org.firstinspires.ftc.teamcode.DataPackages.BigRobotArmData;
import org.firstinspires.ftc.teamcode.DataPackages.BigRobotBeltData;
import org.firstinspires.ftc.teamcode.DataPackages.FieldConstants;
import org.firstinspires.ftc.teamcode.TelemetryHelper;
import org.firstinspires.ftc.teamcode.Actions.IAction;
import org.firstinspires.ftc.teamcode.Actions.MoveToPositionSpline;
import org.firstinspires.ftc.teamcode.Roadrunner.drive.SampleMecanumDrive;

import java.util.ArrayList;
import java.util.List;


@TeleOp(name = "InitRed", group = "Testing")
public class InitRed extends OpMode {

    Camera camera;
    Team team = Team.red;
    RelativePosition rlp;
    SampleMecanumDrive drive;
    BigRobotArmData armData;
    BigRobotBeltData beltData;
    IAction finalSequence;
    @Override
    public void init(){
        armData = new BigRobotArmData(hardwareMap);
        beltData = new BigRobotBeltData(hardwareMap);
        drive = new SampleMecanumDrive(hardwareMap);
        camera =new Camera(hardwareMap,team);
        TelemetryHelper.initTelemetry(telemetry);
        camera.start();
    }

    @Override
    public void loop() {
        if (rlp==null) {
            rlp = camera.getPipeline().getRelPosOfProp();
            double angleRot = getAngle(rlp);
            TelemetryHelper.getDashboardTelemetry().put("Angle", angleRot);
            finalSequence = new SequencedAction(
                    new OpenSweeper(armData,true),
                    new SwitchBeltAction(beltData,true),
                    new SimultaneousAction(
                            new WaitAction(300),
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
                             new WaitAction(80),
                             new PickupThePixelAction(armData,false,true)
                     ),
                    new LiftToAngleBigRobot(armData,45),
                    new ExtendTheArmAction(armData, 100),
                    new ReleasePixelAction(armData, false, true),
                    new SimultaneousAction(
                         new ExtendTheArmAction(armData, armData.initialArmLength),
                         new LiftToAngleBigRobot(armData,15,0.2)
                    ),
                    new LiftToAngleBigRobot(armData,0)

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
