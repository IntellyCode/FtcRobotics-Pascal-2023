package org.firstinspires.ftc.teamcode.Components;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.TelemetryHelper;



public abstract class ComponentAbstract {
    // Common variables for both Motion and Sensor components
    protected String componentName;
    protected boolean isActive = false;

    // Constructor for initializing the component
    public ComponentAbstract(String componentName) {
        this.componentName = componentName;
        activate();
    }

    // Common method to activate the component
    public void activate() {
        isActive = true;
        TelemetryHelper.getTelemetry().addLine(componentName + " is now active:");
    }

    // Common method to deactivate the component
    public void deactivate() {
        isActive = false;
        TelemetryHelper.getTelemetry().addLine(componentName + " is now inactive:");
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

