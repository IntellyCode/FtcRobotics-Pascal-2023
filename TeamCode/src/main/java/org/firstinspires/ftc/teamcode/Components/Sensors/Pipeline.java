package org.firstinspires.ftc.teamcode.Components.Sensors;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

public class Pipeline extends OpenCvPipeline {

//    OpenCvWebcam openCvWebcam;
//
//    public Pipeline(OpenCvWebcam webcam){
//        openCvWebcam = webcam;
//    }

    TelemetryPacket telemetryPacket;
    @Override
    public Mat processFrame(Mat input){

        return input;
    }
}
