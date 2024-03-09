package org.firstinspires.ftc.teamcode.OpModes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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


@TeleOp(name = "InitRed", group = "Testing")
public class InitRed extends OpMode {

    Camera camera;
    Team team = Team.red;
    RelativePosition rlp;
    SampleMecanumDrive drive;
    BigRobotArmData armData;
    BigRobotBeltData beltData;

    IAction action;
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
        if (rlp==null){
            rlp = camera.getPipeline().getRelPosOfProp();
        }
        else {
            double angleRot = getAngle(rlp);

        }
        TelemetryHelper.getDashboardTelemetry().put("Position",rlp);
        TelemetryHelper.update();
    }

    double getAngle(RelativePosition rlp) {
        switch(rlp) {
            case left:
                return -1* FieldConstants.initRotAngle;
            case right:
                return 1*FieldConstants.initRotAngle;
            case center:
                return 0;
        }
        return 0;
    }
}
