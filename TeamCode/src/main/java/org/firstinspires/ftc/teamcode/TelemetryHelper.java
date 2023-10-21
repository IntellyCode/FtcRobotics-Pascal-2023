package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TelemetryHelper {
    static Telemetry telemetry;
    public static Telemetry getTelemetry() {
        return telemetry;
    }

    public static void initTelemetry(Telemetry telemetry) {
        if(TelemetryHelper.telemetry == null)
            TelemetryHelper.telemetry = telemetry;
    }
}
