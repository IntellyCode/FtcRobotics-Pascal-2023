package org.firstinspires.ftc.teamcode.OpModes.Testing;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Components.Sensors.Camera;
import org.firstinspires.ftc.teamcode.Components.Sensors.Pipeline;
import org.firstinspires.ftc.teamcode.TelemetryHelper;

import java.nio.channels.Pipe;

@TeleOp(name = "Testing Camera", group = "Testing")
public class TestingCamera extends OpMode {
    Camera camera;
    @Override
    public void init() {
        TelemetryHelper.initTelemetry(telemetry);
        camera = new Camera(hardwareMap);
        camera.start();
    }

    @Override
    public void loop() {
        telemetry.addData("Testing Camera",true);
        telemetry.update();
    }
}
