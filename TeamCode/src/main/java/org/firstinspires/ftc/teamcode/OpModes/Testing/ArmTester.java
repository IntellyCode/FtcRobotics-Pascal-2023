package org.firstinspires.ftc.teamcode.OpModes.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TelemetryHelper;
import org.firstinspires.ftc.teamcode.Actions.ExtendTheArmAction;
import org.firstinspires.ftc.teamcode.Actions.IAction;
import org.firstinspires.ftc.teamcode.Actions.LiftToAngleBigRobot;
import org.firstinspires.ftc.teamcode.Actions.SequencedAction;
import org.firstinspires.ftc.teamcode.Actions.ThrowPlaneAction;
import org.firstinspires.ftc.teamcode.DataPackages.BigRobotArmData;

@TeleOp
public class ArmTester extends OpMode {
    BigRobotArmData data;
    IAction currAction;
    @Override
    public void init() {
        data = new BigRobotArmData(hardwareMap);
        TelemetryHelper.initTelemetry(telemetry);

    }

    @Override
    public void loop() {

        if(gamepad1.cross) {
            IAction lift = new LiftToAngleBigRobot(data, 30);
            IAction extend = new ExtendTheArmAction(data, 70);
            currAction = new SequencedAction(lift, extend);
            currAction.start();
        }
//        if(gamepad1.cross) {
//            currAction = new ExtendTheArmAction(data, 70);
//            currAction.start();
//        }
        else if(gamepad1.circle) {
            currAction = new ExtendTheArmAction(data, 42);
            currAction.start();
        }
        else if(gamepad1.triangle) {
            currAction = new LiftToAngleBigRobot(data, 45);
            currAction.start();
        }
        else if(gamepad1.square) {
            currAction = new ThrowPlaneAction(data);
            currAction.start();
        }

        TelemetryHelper.update();
        if(currAction != null)
        {
            TelemetryHelper.getTelemetry().addData("Is finished: ", currAction.isOver());
            currAction.update();
        }
        TelemetryHelper.getTelemetry().addData("Lower motor ticks:", data.getLowerArmMotor().getCurrentPosition());
        TelemetryHelper.getTelemetry().addData("Upper motor ticks:", data.getUpperArmMotor().getCurrentPosition());
        TelemetryHelper.getTelemetry().addData("Current angle", data.currentAngle);
        TelemetryHelper.getTelemetry().addData("Current arm length", data.armLength);
        TelemetryHelper.getTelemetry().addData("Grabber coordinates", data.getGrabberCoordinates().getX());
        TelemetryHelper.getTelemetry().addData("Current arm length", data.getGrabberCoordinates().getY());
        data.localize();
    }
}
