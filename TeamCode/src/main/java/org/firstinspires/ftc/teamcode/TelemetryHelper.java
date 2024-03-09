package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TelemetryHelper {
    static FtcDashboard dashboard;
    static TelemetryPacket dashBoardTelemetry;
    static Telemetry telemetry;

    public static TelemetryPacket getDashboardTelemetry() {
        return dashBoardTelemetry;
    }
    public static Telemetry getTelemetry() { return telemetry; }

    public static void initTelemetry(Telemetry initTelemetry) {
        dashboard = FtcDashboard.getInstance();
        dashBoardTelemetry = new TelemetryPacket();
        telemetry = initTelemetry;
    }

    public static void update() {
        telemetry.update();
        dashboard.sendTelemetryPacket(dashBoardTelemetry);
        dashBoardTelemetry.clearLines();
    }

    public static FtcDashboard getDashboard(){
        return dashboard;
    }
}
