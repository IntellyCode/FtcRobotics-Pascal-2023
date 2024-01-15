package org.firstinspires.ftc.teamcode.OpModes.Testing;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Components.Sensors.ObjectDetection;


@TeleOp(name = "Testing Camera", group = "Testing")
public class TestingCamera extends OpMode {
    ObjectDetection object = new ObjectDetection();
    @Override
    public void init() {
        object.initTfod(hardwareMap);
    }

    @Override
    public void loop() {
                object.telemetryTfod();
                // Push telemetry to the Driver Station.
                telemetry.update();
                // Save CPU resources; can resume streaming when needed.
                if (gamepad1.dpad_down) {
                    object.visionPortal.stopStreaming();
                } else if (gamepad1.dpad_up) {
                    object.visionPortal.resumeStreaming();
        }
        object.visionPortal.close();

    }
//    Camera camera;
//    @Override
//    public void init() {
//        TelemetryHelper.initTelemetry(telemetry);
//        camera = new Camera(hardwareMap);
//        camera.start();
//    }
//
//    @Override
//    public void loop() {
//        TelemetryHelper.getDashboardTelemetry().addData("Telemtry helper",true);
//    }

}
