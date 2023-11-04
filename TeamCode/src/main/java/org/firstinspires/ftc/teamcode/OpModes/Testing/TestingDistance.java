package org.firstinspires.ftc.teamcode.OpModes.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Components.Sensors.Distance;
import org.firstinspires.ftc.teamcode.TelemetryHelper;


@TeleOp
public class TestingDistance extends OpMode {
    Distance dist;
    @Override
    public void init() {
        TelemetryHelper.initTelemetry(telemetry);

        dist = new Distance(hardwareMap);
    }

    @Override
    public void loop() {
        double distance = dist.getData();
        telemetry.addData("Distance", distance);
    }
}
