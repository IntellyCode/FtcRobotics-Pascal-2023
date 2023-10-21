package org.firstinspires.ftc.teamcode.ArmController;

import com.qualcomm.robotcore.hardware.DcMotor;


/* Wrapper around the DcMotor. Converts tick commands into cm commands
   I will use it for non-drive motor control. Drive motor odometry is handled by the roadrunner.*/
public class MotorLocalizer {
    int ticksPerRev;
    double rotatingRadius;
    double circumference = rotatingRadius * 2 * Math.PI;

    DcMotor motor;
    double position;
    double getPosition() {
        return position;
    }

    public MotorLocalizer(int ticksPerRev, double rotatingRadius, DcMotor motor){
        this.motor = motor;
        this.ticksPerRev = ticksPerRev;
        this.rotatingRadius = rotatingRadius;
    }

    public void update() {
        position = (motor.getCurrentPosition()/ticksPerRev)*circumference;
    }

    public double estimateTheTicksForPosition(double positionCm) {
        //Portion of the circle traversed*circumference
        return (motor.getCurrentPosition()/ticksPerRev)*circumference;
    }
}
