package org.firstinspires.ftc.teamcode.Components.Sensors;

import android.graphics.Bitmap;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import org.firstinspires.ftc.teamcode.TelemetryHelper;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

public class Pipeline extends OpenCvPipeline {

    OpenCvWebcam webcam;
    public Pipeline(OpenCvWebcam webcam){
        this.webcam = webcam;
    }
    @Override
    public Mat processFrame(Mat input){
        TelemetryHelper.getDashboard().getTelemetry().addData("Camera Pipeline",true);
        TelemetryHelper.getDashboard().getTelemetry().update();
        Bitmap bitmap = Bitmap.createBitmap(input.cols(), input.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(input,bitmap);
        TelemetryHelper.getDashboard().sendImage(bitmap);
        return input;
    }
    @Override
    public void onViewportTapped() {
        this.webcam.pauseViewport();
    }
}
