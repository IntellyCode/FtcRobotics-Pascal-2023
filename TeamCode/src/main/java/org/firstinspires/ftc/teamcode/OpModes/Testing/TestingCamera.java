package org.firstinspires.ftc.teamcode.OpModes.Testing;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Components.Sensors.Camera;
import org.firstinspires.ftc.teamcode.TelemetryHelper;



@TeleOp(name = "Testing Camera", group = "Testing")
public class TestingCamera extends OpMode {
    Camera camera;
    @Override
    public void init(){
        TelemetryHelper.initTelemetry(telemetry);
        camera = new Camera(hardwareMap);
        camera.start();
    }

    @Override
    public void loop() {
        TelemetryHelper.update();
        }




}
