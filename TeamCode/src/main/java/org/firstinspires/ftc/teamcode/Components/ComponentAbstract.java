package org.firstinspires.ftc.teamcode.Components;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class ComponentAbstract {
    // Common variables for both Motion and Sensor components
    protected String componentName;
    protected boolean isActive = false;

    Telemetry telemetry;
    // Constructor for initializing the component
    public ComponentAbstract(String componentName, Telemetry telemetry) {
        this.componentName = componentName;
        this.telemetry = telemetry;
        activate();
    }

    // Common method to activate the component
    public void activate() {
        isActive = true;
        telemetry.addLine(componentName + " is now active:");
    }

    // Common method to deactivate the component
    public void deactivate() {
        isActive = false;
        telemetry.addLine(componentName + " is now inactive:");
    }

    // Common method to check if the component is active
    public boolean isActive() {
        return isActive;
    }

    /*
        Abstract method to be implemented by specific Motion and Sensor components.
        It takes the data package, read it and based on it decides what sensor or motor to activate
     */
    public abstract void performAction();
}

