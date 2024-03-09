package org.firstinspires.ftc.teamcode.DecisionModules;

/*This is an interface encapsulating the decision-making process. Our manual and autonomous controls are
going to implement it*/
public interface IDecisionModule {
    //This is going to be continuously invoked from the opmode
    void controlLoop();
}