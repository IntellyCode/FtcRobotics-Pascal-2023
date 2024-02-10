package org.firstinspires.ftc.teamcode.additional.Initializers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.additional.Actions.PickupPixel;
import org.firstinspires.ftc.teamcode.additional.DataPackages.BeltData;
import org.firstinspires.ftc.teamcode.Common.TelemetryHelper;

@Autonomous
public class TestInitializer extends OpMode {
    BeltData beltData;
    PickupPixel pickupPixel;

    @Override
    public void init() {
        TelemetryHelper.initTelemetry(telemetry);
        beltData = new BeltData(hardwareMap);
        pickupPixel = new PickupPixel(beltData);
    }

    @Override
    public void loop() {
        if(gamepad1.x) {
            pickupPixel.start();
        }
//        if(pickupPixel.isStarted())
//            pickupPixel.update();

        TelemetryHelper.update();
    }
}
