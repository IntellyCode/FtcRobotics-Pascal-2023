package org.firstinspires.ftc.teamcode.OpModes.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Components.Sensors.Distance;
import org.firstinspires.ftc.teamcode.Components.Sensors.Gyro;

@TeleOp
public class TestingGyro extends OpMode {
    Gyro gyro;
    @Override
    public void init() {
        gyro = new Gyro(hardwareMap);
    }
    //test
    @Override
    public void loop() {
        //double angle = gyro.getHeading();
        //telemetry.addData("Angle",angle);

    }
}
