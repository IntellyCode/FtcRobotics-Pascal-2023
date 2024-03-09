package org.firstinspires.ftc.teamcode.Camera;

import android.graphics.Bitmap;


import org.firstinspires.ftc.teamcode.Camera.Util.ColorDetection;
import org.firstinspires.ftc.teamcode.Camera.Util.ColorRange;
import org.firstinspires.ftc.teamcode.Camera.Util.RelativePosition;
import org.firstinspires.ftc.teamcode.Camera.Data.Team;
import org.firstinspires.ftc.teamcode.TelemetryHelper;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

public class Pipeline extends OpenCvPipeline {
    //Color Constants
    public static final ColorRange red = new ColorRange(new Scalar(0, 20, 40),new Scalar(15, 255, 255));
    public static final ColorRange red_2 = new ColorRange(new Scalar(160, 20, 40),new Scalar(180, 255, 255));
    public static final ColorRange blue = new ColorRange(new Scalar(100, 50, 20),new Scalar(130, 255, 200));

    //Color Detection Object
    ColorDetection rcd;
    OpenCvWebcam webcam;

    public Pipeline(Team team){
        switch (team){
            case red:this.rcd = new ColorDetection(red,red_2);break;
            case blue:this.rcd = new ColorDetection(blue);break;
        }
    }
    @Override
    public Mat processFrame(Mat input){
        Mat mat = processImage(input);
        sendImage(mat);
        mat.release();
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

    public Mat processImage(Mat input){
        return rcd.markOuterContour(rcd.extractColors(input));
    }

    public RelativePosition getRelPosOfProp(){
        return rcd.getRelativePosition();
    }


}
