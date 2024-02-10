package org.firstinspires.ftc.teamcode.additional.Initializers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.additional.Actions.LiftToAngle;
import org.firstinspires.ftc.teamcode.additional.DataPackages.SmallRobotArmData;
import org.firstinspires.ftc.teamcode.Common.TelemetryHelper;

@Autonomous
public class ArmMotorTuner extends OpMode {
    SmallRobotArmData smallRobotArmData;
    LiftToAngle liftToAngle;
    double target;
    @Override
    public void init() {
        target = Math.PI/4;
        this.smallRobotArmData = new SmallRobotArmData(hardwareMap);
        TelemetryHelper.initTelemetry(telemetry);
    }

    @Override
    public void loop() {
        if(gamepad1.left_bumper) {
            liftToAngle = new LiftToAngle(smallRobotArmData, Math.PI / 4);
            liftToAngle.start();
        }
        if(gamepad1.right_bumper) {
            liftToAngle = new LiftToAngle(smallRobotArmData, 0);
            liftToAngle.start();
        }

        if(liftToAngle != null)
            liftToAngle.update();

        TelemetryHelper.update();
    }
}
