package org.firstinspires.ftc.teamcode.additional.Initializers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.additional.Actions.LiftToAngle;
import org.firstinspires.ftc.teamcode.additional.DataPackages.ArmData;
import org.firstinspires.ftc.teamcode.TelemetryHelper;

@Autonomous
public class ArmMotorTuner extends OpMode {
    ArmData armData;
    LiftToAngle liftToAngle;
    double target;
    @Override
    public void init() {
        target = Math.PI/4;
        this.armData = new ArmData(hardwareMap);
        TelemetryHelper.initTelemetry(telemetry);
    }

    @Override
    public void loop() {
        if(gamepad1.left_bumper) {
            liftToAngle = new LiftToAngle(armData, Math.PI / 4);
            liftToAngle.start();
        }
        if(gamepad1.right_bumper) {
            liftToAngle = new LiftToAngle(armData, 0);
            liftToAngle.start();
        }

        if(liftToAngle != null)
            liftToAngle.update();

        TelemetryHelper.update();
    }
}
