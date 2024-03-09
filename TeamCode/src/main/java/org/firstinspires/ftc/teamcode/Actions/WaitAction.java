package org.firstinspires.ftc.teamcode.Actions;

public class WaitAction implements IAction{

    int counter = 0;
    int loopIterations;
    boolean isFinished;
    public WaitAction(int loopIterations){
        this.loopIterations = loopIterations;
    }
    @Override
    public void start() {

    }

    @Override
    public void update() {
        counter+=1;
        if (counter > this.loopIterations){
            isFinished = true;
        }
    }


    @Override
    public boolean isOver() {
        return isFinished;
    }
}
