package org.firstinspires.ftc.teamcode.additional.Actions;


import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.additional.DataPackages.BeltData;

public class PickupPixel implements IAction {
    ElapsedTime elapsedtime;
    boolean isStarted;
    boolean isOver;
    final double pixelPickupConstant = 1000; //In milliseconds

    public PickupPixel(BeltData beltData) {
        this.beltData = beltData;
    }
    BeltData beltData;
    @Override
    public void start() {
        if(isOver()) return;
        isStarted = true;
        beltData.getBeltMotor1().setPower(1);
        beltData.getBeltMotor2().setPower(1);
        elapsedtime = new ElapsedTime();
    }

    @Override
    public void update() {
        if(isOver) return;
        if(elapsedtime.milliseconds() >= pixelPickupConstant) {
            beltData.getBeltMotor1().setPower(0);
            beltData.getBeltMotor2().setPower(0);
            isOver = true;
        }
    }

    @Override
    public boolean isOver() {
        return isOver;
    }

    @Override
    public boolean isStarted() {
        return isStarted;
    }
}
