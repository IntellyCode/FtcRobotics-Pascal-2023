package org.firstinspires.ftc.teamcode.OpModes.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Common.Team;
import org.firstinspires.ftc.teamcode.Common.TelemetryHelper;
import org.firstinspires.ftc.teamcode.Components.Sensors.BigRobotSensorData;
import org.firstinspires.ftc.teamcode.Components.Sensors.Camera;

@TeleOp(name = "Distance Testing", group = "Testing")
public class DistanceTesting extends OpMode {
    BigRobotSensorData bigRobotSensorData;
    @Override
    public void init(){
        TelemetryHelper.initTelemetry(telemetry);
        bigRobotSensorData = new BigRobotSensorData(hardwareMap);

    }

    @Override
    public void loop() {
        TelemetryHelper.getDashboardTelemetry().put("Distance",bigRobotSensorData.getDistance());
        TelemetryHelper.update();
    }




}