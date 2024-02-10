package org.firstinspires.ftc.teamcode.ArmController;

import org.firstinspires.ftc.teamcode.Common.TelemetryHelper;

//This one uses outer-open inner-closed loop for estimating actual arm position;
public class ArmLiftAction {
    MotorLocalizer localizer;
    double targetPosition;


    public ArmLiftAction(double targetPosition, MotorLocalizer localizer) {
        this.targetPosition = targetPosition;
        this.localizer = localizer;
    }

    public void act() {
        int targetTicks = (int) localizer.estimateTheTicksForPosition(targetPosition);

        /*This is the closed-loop part. The pid controller or analogous controller is already implemented in the motor.
        It uses data from the encoder to get as close to the required position as possible.
        I hope it is precise enough. If not, we have to consider alternative methods of estimating the arm's position that do
        not depend on the encoders*/
        localizer.motor.setTargetPosition(targetTicks);

        TelemetryHelper.getTelemetry().addData("current arm position in cm: ", localizer.position);
    }
}
