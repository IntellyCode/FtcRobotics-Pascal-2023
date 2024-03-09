package org.firstinspires.ftc.teamcode.Actions;

import org.firstinspires.ftc.teamcode.TelemetryHelper;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SequencedAction implements IAction {
    IAction currentAction;
    boolean isStarted;
    boolean isFinished;
    Queue<IAction> actions;

    @Override
    public void start() {
        isStarted = true;
        currentAction = actions.poll();
        currentAction.start();
    }

    @Override
    public void update() {
        if(currentAction != null && currentAction.isOver()) {
            currentAction = actions.poll();
            if(currentAction == null)
                isFinished = true;
            else
                currentAction.start();
        }

        if(currentAction != null && isStarted)
            currentAction.update();

        if(currentAction != null) {
            TelemetryHelper.getTelemetry().addData("Current action: ", currentAction);
            TelemetryHelper.getTelemetry().addData("Current action status: ", currentAction);
        }

    }

    @Override
    public boolean isOver() {
        return isFinished;
    }

    public SequencedAction(IAction... actions) {
        this.actions = new ConcurrentLinkedQueue<>();
        for (IAction action : actions) {
            this.actions.add(action);
        }
    }
}
