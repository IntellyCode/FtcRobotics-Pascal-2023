package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TelemetryHelper {
    static Telemetry telemetry;
    static Telemetry dashboardTelemetry;
    public static Telemetry getTelemetry() {
        return telemetry;
    }

    public static Telemetry getDashboardTelemetry(){
        return dashboardTelemetry;
    }

    public static void initTelemetry(Telemetry telemetry) {
        FtcDashboard dashboard = FtcDashboard.getInstance();

        if(TelemetryHelper.telemetry == null) {
            TelemetryHelper.dashboardTelemetry = dashboard.getTelemetry();
            TelemetryHelper.telemetry = telemetry;
        }
    }
}
