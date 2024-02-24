package org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Common.TelemetryHelper;
import org.firstinspires.ftc.teamcode.additional.Actions.LiftToAngleBigRobot;
import org.firstinspires.ftc.teamcode.additional.DataPackages.BigRobotArmData;

public class BigRobotManualArmModule implements IDecisionModule {
    LiftToAngleBigRobot liftAction;
    double targetAngle;
    boolean cleshnjaClosed;
    Gamepad gamepad1;
    BigRobotArmData bigRobotArmData;

    public BigRobotManualArmModule(HardwareMap map, Gamepad gamepad1) {
        bigRobotArmData = new BigRobotArmData(map);
        bigRobotArmData.getSweeperServo().setPosition(0);
        this.gamepad1 = gamepad1;
    }

    @Override
    public void controlLoop() {
        TelemetryHelper.getTelemetry().addData("R Servo pos", bigRobotArmData.getClawServoRight().getPosition());
        TelemetryHelper.getTelemetry().addData("L Servo pos", bigRobotArmData.getClawServoLeft().getPosition());
        //Haptic feedback

        //Lower motor control
        targetAngle += (gamepad1.right_trigger - gamepad1.left_trigger)*3;
        if(targetAngle > 45)
            targetAngle = 45;
        if(targetAngle < 0)
            targetAngle = 0;

        liftAction = new LiftToAngleBigRobot(bigRobotArmData, targetAngle);
        liftAction.start();
        TelemetryHelper.getTelemetry().addData("Target angle", targetAngle);
        TelemetryHelper.getTelemetry().addData("current angle", bigRobotArmData.currentAngle);
        TelemetryHelper.getTelemetry().addData("rightTrigger", gamepad1.right_trigger);
        TelemetryHelper.getTelemetry().addData("leftTrigger", gamepad1.left_trigger);

        //Upper arm control
        double upperPower = 0f;
        if(gamepad1.dpad_up) {
            upperPower = 1f;
        } else if(gamepad1.dpad_down) upperPower = -1f;

        if((bigRobotArmData.getUpperArmMotor().getCurrentPosition() > bigRobotArmData.upperMotorTicksUpperBound ) && upperPower > 0)
            upperPower = 0;
        else if((bigRobotArmData.getUpperArmMotor().getCurrentPosition() < bigRobotArmData.lowerMotorTicksLowerBound) && upperPower < 0 )
            upperPower = 0;

        bigRobotArmData.getUpperArmMotor().setPower(upperPower);
        TelemetryHelper.getTelemetry().addData("Upper m ticks:", bigRobotArmData.getUpperArmMotor().getCurrentPosition());

        if(gamepad1.options) {

        }
        //Cleshnja control
        if(gamepad1.square) cleshnjaClosed = false;
        if(gamepad1.circle) cleshnjaClosed = true;

        if(cleshnjaClosed) {
            bigRobotArmData.getClawServoLeft().setPosition(1);
            bigRobotArmData.getClawServoRight().setPosition(0);
        }
        else {
            bigRobotArmData.getClawServoLeft().setPosition(0);
            bigRobotArmData.getClawServoRight().setPosition(1);
        }
        if(liftAction != null)
            liftAction.update();
        bigRobotArmData.localize();
    }
}


