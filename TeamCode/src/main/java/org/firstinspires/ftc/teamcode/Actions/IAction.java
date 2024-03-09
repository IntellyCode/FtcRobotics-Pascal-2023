package org.firstinspires.ftc.teamcode.Actions;

public interface IAction {
    //Start a function
    void start();

    //If an action needs to be continuously updated
    void update();

    //Is the function over?
    boolean isOver();
}
