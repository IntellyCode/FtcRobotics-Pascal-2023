package org.firstinspires.ftc.teamcode.additional.Actions;

import java.util.List;

public class ComplexAction implements IAction{
    List<IAction> ongoingActions;
    public List<IAction> getOngoingActions() {
        return ongoingActions;
    }

    List<IAction> finishedActions;
    public List<IAction> getFinishedActions() {
        return finishedActions;
    }

    public ComplexAction(List<IAction> actions) {
        ongoingActions = actions;
    }

    boolean isStarted;

    boolean isFinished;
    public boolean isOver() {
        return isFinished;
    }

    @Override
    public boolean isStarted() {
        return isStarted;
    }

    //Start every action
    @Override
    public void start() {
        for (IAction action : ongoingActions)
            action.start();
    }

    //Updates every action and checks is finished.
    @Override
    public void update() {
        if(isFinished) return;

        for (IAction action : ongoingActions) {
            if(action.isOver()) {
                finishedActions.add(action);
                ongoingActions.remove(action);
            }
            else
                action.update();
        }

        if(ongoingActions.size() == 0)
            isFinished = true;
    }
}
