package org.firstinspires.ftc.teamcode.DecisionModules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Common.TelemetryHelper;
import org.firstinspires.ftc.teamcode.DataPackages.SmallRobotArmData;

public class SmallRobotManualArmModule implements IDecisionModule {
    Gamepad gamepad;
    SmallRobotArmData smallRobotArmData;

    double upperArmY;
    double lowerArmY;
    double cleshnjaY;
    public SmallRobotManualArmModule(HardwareMap map, Gamepad gamepad) {
        smallRobotArmData = new SmallRobotArmData(map);
        this.gamepad = gamepad;
    }
    @Override
    public void controlLoop() {
        //Upper arm
        upperArmY = gamepad.right_trigger - gamepad.left_trigger;

        smallRobotArmData.getLowerServo().setPosition(1);
        smallRobotArmData.getGrabberServo().setPosition(0);
        smallRobotArmData.getRightArmMotor().setPower(upperArmY);
        smallRobotArmData.getLeftArmMotor().setPower(upperArmY);

        //Lower arm
        if(gamepad.dpad_up && lowerArmY < 1)
            lowerArmY += 0.01;
        if(gamepad.dpad_down && lowerArmY > 0)
            lowerArmY -= 0.01;
        smallRobotArmData.getLowerServo().setPosition(lowerArmY);

        //Cleshnja
        if(gamepad.left_bumper && cleshnjaY > 0) cleshnjaY -= 0.01*0.5;
        if(gamepad.right_bumper && cleshnjaY < 1) cleshnjaY += 0.01*0.5;
        smallRobotArmData.getGrabberServo().setPosition(cleshnjaY);
        TelemetryHelper.getTelemetry().addData("CleshnjaY:", cleshnjaY);
        TelemetryHelper.getTelemetry().addData("Servo pos", smallRobotArmData.getGrabberServo().getPosition());

    }
}
