package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Camera.Camera;
import org.firstinspires.ftc.teamcode.Common.Team;
import org.firstinspires.ftc.teamcode.Common.TelemetryHelper;



@TeleOp(name = "Testing Camera", group = "Testing")
public class TestingCamera extends OpMode {
    Camera camera;
    @Override
    public void init(){
        TelemetryHelper.initTelemetry(telemetry);
        camera = new Camera(hardwareMap, Team.red);
        camera.start();
    }

    @Override
    public void loop() {
        TelemetryHelper.update();
        }




}
