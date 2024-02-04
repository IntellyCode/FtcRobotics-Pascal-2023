package org.firstinspires.ftc.teamcode.Camera;

import android.graphics.Bitmap;
import android.graphics.Color;

import org.firstinspires.ftc.teamcode.Camera.Util.ColorDetection;
import org.firstinspires.ftc.teamcode.Camera.Util.ColorRange;
import org.firstinspires.ftc.teamcode.TelemetryHelper;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

public class Pipeline extends OpenCvPipeline {

    public static final ColorRange red = new ColorRange(new Scalar(0, 60, 100),new Scalar(15, 255, 255));
    public static final ColorRange blue = new ColorRange(new Scalar(100, 50, 20),new Scalar(130, 255, 200));
    ColorDetection rcd = new ColorDetection(blue);
    OpenCvWebcam webcam;
    public Pipeline(){

    }
    @Override
    public Mat processFrame(Mat input){
        Mat red_extracted = rcd.extractColors(input);
        Mat with_contour = rcd.markOuterContour(red_extracted);
        sendImage(with_contour);
        red_extracted.release();
        with_contour.release();
        return input;
    }
    public void sendImage(Mat input){
        Bitmap bitmap = Bitmap.createBitmap(input.cols(), input.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(input,bitmap);
        TelemetryHelper.getDashboard().sendImage(bitmap);
    }

    @Override
    public void onViewportTapped() {

        this.webcam.pauseViewport();
    }
}
