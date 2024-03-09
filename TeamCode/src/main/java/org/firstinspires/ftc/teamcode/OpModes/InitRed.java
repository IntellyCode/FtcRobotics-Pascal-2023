package org.firstinspires.ftc.teamcode.OpModes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Camera.Camera;
import org.firstinspires.ftc.teamcode.Camera.Util.RelativePosition;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.Team;
import org.firstinspires.ftc.teamcode.Common.TelemetryHelper;
import org.firstinspires.ftc.teamcode.additional.Actions.IAction;
import org.firstinspires.ftc.teamcode.additional.Actions.MoveToPositionSpline;
import org.firstinspires.ftc.teamcode.additional.drive.SampleMecanumDrive;


@TeleOp(name = "InitRed", group = "Testing")
public class InitRed extends OpMode {

    Camera camera;
    Team team = Team.red;

    RelativePosition rlp;
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
            action = new MoveToPositionSpline(drive, drive.getPoseEstimate(), Coordinates.getParkingPosition(this.rlp));
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
