package org.firstinspires.ftc.teamcode.Actions;

import org.firstinspires.ftc.teamcode.DataPackages.BigRobotBeltData;

public class SwitchBeltAction implements IAction {
    boolean isFinished;
    boolean on;
    BigRobotBeltData beltData;

    public SwitchBeltAction(BigRobotBeltData beltData, boolean on) {
        this.beltData = beltData;
        this.on = on;
    }
    @Override
    public void start() {
        isFinished = true;
        if(on) {
            beltData.getBeltMotor1().setPower(-1);
            beltData.getBeltMotor2().setPower(-1);
        } else
        {
            beltData.getBeltMotor1().setPower(0);
            beltData.getBeltMotor2().setPower(0);
        }
    }

    @Override
    public void update() {
        if(isFinished)
            return;
    }

    @Override
    public boolean isOver() {
        return isFinished;
    }
}
