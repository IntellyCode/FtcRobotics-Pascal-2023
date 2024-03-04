package org.firstinspires.ftc.teamcode;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Camera.Camera;
import org.firstinspires.ftc.teamcode.Camera.Util.RelativePosition;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.Team;
import org.firstinspires.ftc.teamcode.Common.TelemetryHelper;
import org.firstinspires.ftc.teamcode.additional.Actions.IAction;
import org.firstinspires.ftc.teamcode.additional.Actions.MoveToPosition;
import org.firstinspires.ftc.teamcode.additional.drive.SampleMecanumDrive;


@TeleOp(name = "InitRed", group = "Testing")
public class InitRed extends OpMode {

    Camera camera;
    RelativePosition rlp;
    Team team = Team.red;
    IAction action;
    SampleMecanumDrive drive;
    @Override
    public void init(){
        camera =new Camera(hardwareMap,team);
        TelemetryHelper.initTelemetry(telemetry);
        drive = new SampleMecanumDrive(hardwareMap);
        camera.start();

    }

    @Override
    public void loop() {

        if (rlp==null){
            rlp = camera.getPipeline().getRelPosOfProp();
        }
        if(gamepad1.cross) {
            action = new MoveToPosition(drive, Coordinates.getParkingPosition(this.rlp));
            action.start();
        }

        if(action != null) {
            TelemetryHelper.getTelemetry().addData("Is finished", action.isOver());
            action.update();
        }
        TelemetryHelper.getDashboardTelemetry().put("Position",rlp);
        TelemetryHelper.update();
    }




}
