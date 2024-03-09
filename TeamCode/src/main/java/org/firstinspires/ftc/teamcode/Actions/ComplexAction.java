package org.firstinspires.ftc.teamcode.Actions;

import org.firstinspires.ftc.teamcode.Actions.IAction;

import java.util.List;

/*
This implementation of IAction allows to combine multiple actions happening at the same time
When every action finished - so does the ComplexAction.
*/
public class ComplexAction implements IAction {
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

    boolean over;
    public boolean isOver() {
        return over;
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
        if(over) return;

        for (IAction action : ongoingActions) {
            if(action.isOver()) {
                finishedActions.add(action);
                ongoingActions.remove(action);
            }
            else
                action.update();
        }

        if(ongoingActions.size() == 0)
            over = true;
    }
}
