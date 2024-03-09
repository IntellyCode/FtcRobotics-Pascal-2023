package org.firstinspires.ftc.teamcode.DecisionModules;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.TelemetryHelper;
import org.firstinspires.ftc.teamcode.Actions.LiftToAngleBigRobot;
import org.firstinspires.ftc.teamcode.DataPackages.BigRobotArmData;

public class BigRobotManualArmModule implements IDecisionModule {

    final int setAngle = 15;
    LiftToAngleBigRobot liftAction;
    double targetAngle;
    boolean cleshnjaClosed;
    Gamepad gamepad;
    BigRobotArmData bigRobotArmData;

    public BigRobotManualArmModule(HardwareMap map, Gamepad gamepad) {
        bigRobotArmData = new BigRobotArmData(map);
        bigRobotArmData.getSweeperServo().setPosition(0);
        this.gamepad = gamepad;
    }

    @Override
    public void controlLoop() {
        TelemetryHelper.getTelemetry().addData("R Servo pos", bigRobotArmData.getClawServoRight().getPosition());
        TelemetryHelper.getTelemetry().addData("L Servo pos", bigRobotArmData.getClawServoLeft().getPosition());
        //Haptic feedback

        //Upper arm control
        double upperPower = 0f;
        if (gamepad.dpad_up && spaceAheadFree(bigRobotArmData.getUpperArmMotor(),targetAngle)) {
            upperPower = 1f;
        }else if (gamepad.dpad_up && !spaceAheadFree(bigRobotArmData.getUpperArmMotor(),targetAngle)){
            targetAngle = setAngle;
        } else if (gamepad.dpad_down) upperPower = -1f;

        upperPower = limitExceeded(bigRobotArmData.getUpperArmMotor(), bigRobotArmData.lowerMotorTicksLowerBound, bigRobotArmData.upperMotorTicksUpperBound, upperPower);
        bigRobotArmData.getUpperArmMotor().setPower(upperPower);
        TelemetryHelper.getTelemetry().addData("Upper m ticks:", bigRobotArmData.getUpperArmMotor().getCurrentPosition());

        if (gamepad.dpad_down || gamepad.dpad_up ){
            targetAngle = adjustAngle(bigRobotArmData.getUpperArmMotor(),targetAngle, gamepad.dpad_down);
        }
        //Lower motor control
        targetAngle += (gamepad.right_trigger - gamepad.left_trigger) * 3;
        if (targetAngle > 45)
            targetAngle = 45;
        if (targetAngle < 0)
            targetAngle = 0;

        double power = interpolate(bigRobotArmData.minPower, bigRobotArmData.maxPower, bigRobotArmData.getUpperArmMotor().getCurrentPosition(), bigRobotArmData.lowerMotorTicksLowerBound, bigRobotArmData.upperMotorTicksUpperBound);
        liftAction = new LiftToAngleBigRobot(bigRobotArmData, targetAngle, power);
        liftAction.start();
        TelemetryHelper.getTelemetry().addData("Target angle", targetAngle);
        TelemetryHelper.getTelemetry().addData("current angle", bigRobotArmData.currentAngle);
        TelemetryHelper.getTelemetry().addData("rightTrigger", gamepad.right_trigger);
        TelemetryHelper.getTelemetry().addData("leftTrigger", gamepad.left_trigger);

        //Cleshnja control
        if(gamepad.square) cleshnjaClosed = false;
        if(gamepad.circle) cleshnjaClosed = true;

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

    private double interpolate( double minP, double maxP, double curTick, double minTick, double maxTick) {
        TelemetryHelper.getTelemetry().addData("MinP: ", minP);
        TelemetryHelper.getTelemetry().addData("MaxP: ", maxP);
        TelemetryHelper.getTelemetry().addData("CurTick: ", curTick);
        TelemetryHelper.getTelemetry().addData("minTick: ",minTick);
        TelemetryHelper.getTelemetry().addData("maxTick: ", maxTick);
        if (curTick <= minTick) {
            TelemetryHelper.getTelemetry().addData("Returned Power: ", maxP);
            return maxP; // If curTick is less than or equal to minTick, return minP
        } else if (curTick >= maxTick) {
            TelemetryHelper.getTelemetry().addData("Returned Power: ", minP);
            return minP; // If curTick is greater than or equal to maxTick, return maxP
        } else {
            // Calculate the percentage of curTick between minTick and maxTick
            double percentage = (maxTick - curTick) / (maxTick - minTick);
            TelemetryHelper.getTelemetry().addData("Percentage: ", percentage);
            // Calculate the interpolated value of P within the range of minP and maxP
            TelemetryHelper.getTelemetry().addData("Returned Power: ", minP + (maxP - minP) * percentage);
            return minP + (maxP - minP) * percentage;
        }
    }

    private double limitExceeded(DcMotorEx motor, int min, int max, double dir) {
        int currentPosition = motor.getCurrentPosition();
        if ((dir > 0 && currentPosition > max) || (dir < 0 && currentPosition < min)) {
            return 0;
        }
        return dir;
    }

    private double adjustAngle(DcMotorEx motor,double angle, boolean special){

        if (motor.getCurrentPosition() < bigRobotArmData.minTickForRaise && motor.getCurrentPosition() > bigRobotArmData.lowerMotorTicksLowerBound){
            if (angle < setAngle){
                return setAngle;
            }
        }
        if (motor.getCurrentPosition() <= bigRobotArmData.lowerMotorTicksLowerBound &&special){
            return 0;
        }
        return angle;
    }

    private boolean spaceAheadFree(DcMotorEx motor, double currentAngle){
        return motor.getCurrentPosition() >= bigRobotArmData.minTickForRaise  || !(currentAngle < setAngle);
    }
}


