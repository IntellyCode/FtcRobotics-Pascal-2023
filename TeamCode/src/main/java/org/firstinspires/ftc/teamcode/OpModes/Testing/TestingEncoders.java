package org.firstinspires.ftc.teamcode.OpModes.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.TelemetryHelper;

@Autonomous
public class TestingEncoders extends OpMode {
    float ticksPerRev = 320;
    float wheelRadius = 0.05f;
    DcMotor motor1;
    @Override
    public void init() {
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
    }

    @Override
    public void loop() {
        TelemetryHelper.getTelemetry().addData("ticks: ", motor1.getCurrentPosition());
        TelemetryHelper.getTelemetry().addData("cmTraveled: ", sampleKinematicEquation(motor1.getCurrentPosition()));
    }

    //Just a sample for calculating a very very rough position of the imaginary robot.
    double sampleKinematicEquation(int ticks) {
         float fractionOfWheel = 320/ticks;
         double distance = fractionOfWheel*(2*Math.PI*wheelRadius);
         return distance;
    }
}
