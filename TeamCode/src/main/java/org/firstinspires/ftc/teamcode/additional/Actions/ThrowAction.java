package org.firstinspires.ftc.teamcode.additional.Actions;

import org.firstinspires.ftc.teamcode.additional.DataPackages.BigRobotPlaneLauncherData;

public class ThrowAction implements IAction {
    boolean isFinished;
    BigRobotPlaneLauncherData data;
    @Override
    public void start() {
        //This releases a spring or whatever. Don't know how it will work exactly yet
        data.getSpringServo().setPosition(1);

    }

    @Override
    public void update() {
        if(data.getSpringServo().getPosition() == 1) {
            data.planeThrown = true;
            isFinished = true;
        }
    }

    @Override
    public boolean isOver() {
        return isFinished;
    }

}
