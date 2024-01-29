package org.firstinspires.ftc.teamcode.additional.Controllers;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.TelemetryHelper;
import org.firstinspires.ftc.teamcode.Vector3d;

public class PIDController {
    ElapsedTime elapsedTime;
    double accumulatedError;
    double previousError;
    double kp;
    double ki;
    double kd;
    public PIDController(Vector3d pidCoefficients) {
        this.kp = pidCoefficients.x;
        this.ki = pidCoefficients.y;
        this.kd = pidCoefficients.z;
    }
    public PIDController(double kp, double ki, double kd) {
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
    }

    public void start() {
        elapsedTime = new ElapsedTime();
    }

    public double update(double reference, double input) {
        double error = reference-input;
        TelemetryHelper.getDashboardTelemetry().put("Error: ", error);
        double deltaTime = elapsedTime.seconds();
        accumulatedError += integral(error, deltaTime);
        double derivative = derivative(error, previousError, deltaTime);

        previousError = error;
        elapsedTime.reset();

        return (kp*error) + (ki*accumulatedError) + (kd*derivative);
    }

    double integral(double error, double elapsedTime) {
        return error*elapsedTime;
    }

    double derivative(double error, double previousError, double elapsedTime) {
        return (previousError - error)/elapsedTime;
    }
}
