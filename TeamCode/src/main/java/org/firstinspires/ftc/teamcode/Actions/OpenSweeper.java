package org.firstinspires.ftc.teamcode.Actions;

import org.firstinspires.ftc.teamcode.DataPackages.BigRobotArmData;

public class OpenSweeper implements IAction {

    BigRobotArmData bigRobotArmData;
    boolean isFinished;

    boolean lower;
    public OpenSweeper(BigRobotArmData bigRobotArmData,boolean lower){
        this.bigRobotArmData = bigRobotArmData;
        this.lower = lower;
    }
    @Override
    public void start() {
        if(lower){
            bigRobotArmData.getSweeperServo().setPosition(0);
        } else{
            bigRobotArmData.getSweeperServo().setPosition(1);
        }
        isFinished = true;
    }

    @Override
    public void update() {

    }

    @Override
    public boolean isOver() {
        return isFinished;
    }
}
