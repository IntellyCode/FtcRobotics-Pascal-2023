package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TelemetryHelper {
    static Telemetry telemetry;
    static FtcDashboard dashboard;
    public static Telemetry getTelemetry() {
        return telemetry;
    }

    public static FtcDashboard getDashboard(){
        return dashboard;
    }

    public static void initTelemetry(Telemetry telemetry) {

        if(TelemetryHelper.telemetry == null) {
            TelemetryHelper.telemetry = telemetry;
            dashboard = FtcDashboard.getInstance();
        }
    }
}
