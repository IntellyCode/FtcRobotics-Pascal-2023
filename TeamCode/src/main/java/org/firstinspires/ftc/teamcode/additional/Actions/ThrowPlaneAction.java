package org.firstinspires.ftc.teamcode.additional.Actions;

import org.firstinspires.ftc.teamcode.additional.DataPackages.BigRobotArmData;

public class ThrowPlaneAction implements IAction {
    BigRobotArmData data;
    boolean isFinished;

    public ThrowPlaneAction(BigRobotArmData data) {
        this.data = data;
    }
    @Override
    public void start() {
        data.getPlaneLauncher().setPosition(0);
    }

    @Override
    public void update() {
        if(data.getPlaneLauncher().getPosition() == 0) {
            isFinished = true;
            data.getPlaneLauncher().setPosition(1);
        }
    }

    @Override
    public boolean isOver() {
        return isFinished;
    }
}
