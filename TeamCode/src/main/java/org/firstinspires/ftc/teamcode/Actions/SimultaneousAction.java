package org.firstinspires.ftc.teamcode.Actions;

import org.firstinspires.ftc.teamcode.TelemetryHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimultaneousAction implements IAction{
    List<IAction> ongoingActions;
    public List<IAction> getOngoingActions() {
        return ongoingActions;
    }

    List<IAction> finishedActions;
    public List<IAction> getFinishedActions() {
        return finishedActions;
    }

    public SimultaneousAction(IAction ... actions) {
        ongoingActions = new LinkedList<>();
        for(IAction action : actions)
            ongoingActions.add(action);
        finishedActions = new ArrayList<>();

    }
    boolean isFinished = false;
    public boolean isOver() {
        return isFinished;
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

        for (int i = 0; i < ongoingActions.size(); ++i) {
            if(ongoingActions.get(i).isOver()) {
                finishedActions.add(ongoingActions.get(i));
                ongoingActions.remove(i);
            }
            else {
                TelemetryHelper.getTelemetry().addData("Curr size", ongoingActions.size());
                ongoingActions.get(i).update();
            }
        }

        if(ongoingActions.size() == 0)
            isFinished = true;
        TelemetryHelper.getTelemetry().addData("Finished :", isFinished);
        TelemetryHelper.getTelemetry().addData("Ongoing actions:", ongoingActions.size());
    }
}
