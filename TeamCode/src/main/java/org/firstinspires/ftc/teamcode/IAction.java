package org.firstinspires.ftc.teamcode;

/*
This interface represents a robot action, either singular or simultaneous.
Actions may take time to complete, so they also have a function that check if they're over
*/
public interface IAction {
    //Start a function
    void start();

    //If an action needs to be continuously updated
    void update();

    //Is the function over?
    boolean isOver();
}
