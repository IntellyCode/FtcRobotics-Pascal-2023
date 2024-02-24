package org.firstinspires.ftc.teamcode.additional.Actions;


import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.additional.DataPackages.BigRobotBeltData;

public class PickupPixel implements IAction {
    ElapsedTime elapsedtime;
    boolean isStarted;
    boolean isOver;
    final double pixelPickupConstant = 1000; //In milliseconds

    public PickupPixel(BigRobotBeltData bigRobotBeltData) {
        this.bigRobotBeltData = bigRobotBeltData;
    }
    BigRobotBeltData bigRobotBeltData;
    @Override
    public void start() {
        if(isOver()) return;
        isStarted = true;
        bigRobotBeltData.getBeltMotor1().setPower(1);
        bigRobotBeltData.getBeltMotor2().setPower(1);
        elapsedtime = new ElapsedTime();
    }

    @Override
    public void update() {
        if(isOver) return;
        if(elapsedtime.milliseconds() >= pixelPickupConstant) {
            bigRobotBeltData.getBeltMotor1().setPower(0);
            bigRobotBeltData.getBeltMotor2().setPower(0);
            isOver = true;
        }
    }

    @Override
    public boolean isOver() {
        return isOver;
    }
}
