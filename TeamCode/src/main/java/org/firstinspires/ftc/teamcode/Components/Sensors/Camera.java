package org.firstinspires.ftc.teamcode.Components.Sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Camera.Pipeline;
import org.firstinspires.ftc.teamcode.Common.Team;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

public class Camera {

    OpenCvWebcam webcam;

    WebcamName webcamName;
    Pipeline pipeline;

    public Camera(HardwareMap hardwareMap, Team team){
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        this.webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        webcam = OpenCvCameraFactory.getInstance().createWebcam(this.webcamName,cameraMonitorViewId);
        this.pipeline = new Pipeline(team);
        webcam.setPipeline(pipeline);
        webcam.setMillisecondsPermissionTimeout(5000);
    }

    public WebcamName getWebcamName(){
        return this.webcamName;
    }
    public void start(){
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(752,416, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {
            }
        });
    }

    public Pipeline getPipeline(){
        return this.pipeline;
    }

}
