package org.firstinspires.ftc.teamcode.OpModes.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Components.Sensors.Distance;


@TeleOp
public class TestingDistance extends OpMode {
    Distance dist;
    @Override
    public void init() {
        dist = new Distance(hardwareMap);
    }

    @Override
    public void loop() {
        double distance = dist.getDistance();
        telemetry.addData("Distance", distance);
    }
}
