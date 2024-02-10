package org.firstinspires.ftc.teamcode.Camera.Util;

import org.firstinspires.ftc.teamcode.TelemetryHelper;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ColorDetection {

    ColorRange colorRange;

    ColorRange offsetRange;
    public ColorDetection(ColorRange colorRange) {
        this.colorRange = colorRange;
    }

    public ColorDetection(ColorRange colorRange,ColorRange offsetRange) {
        this.colorRange = colorRange;
        this.offsetRange = offsetRange;
    }

    public Mat extractColors( Mat src) {
        Mat hsv = new Mat();
        Imgproc.cvtColor(src, hsv, Imgproc.COLOR_RGB2HSV);
        /*
        Competition values RED
        Scalar lowHSV = new Scalar(0, 60, 100);
        Scalar highHSV = new Scalar(15, 255, 255);
         */

        Mat mask = new Mat();
        Core.inRange(hsv, colorRange.getLow(), colorRange.getHigh(), mask);
        if (offsetRange!=null){
            Mat mask2 = new Mat();
            Core.inRange(hsv, offsetRange.getLow(), offsetRange.getHigh(), mask2);
            Core.add(mask, mask2, mask);
        }

        // Apply a morphological closing operation to fill small gaps in the detected contour
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
        Imgproc.morphologyEx(mask, mask, Imgproc.MORPH_CLOSE, kernel);

        Mat result = new Mat();
        src.copyTo(result, mask);
        hsv.release();
        mask.release();
        kernel.release();
        return result;

    }


    public Mat markOuterContour(Mat processedImage) {
        // Convert the image to grayscale
        Mat grayImage = new Mat();
        Imgproc.cvtColor(processedImage, grayImage, Imgproc.COLOR_BGR2GRAY);

        // Find contours of the grayscale image
        final List<MatOfPoint> allContours = new ArrayList<>();
        Imgproc.findContours(
                grayImage,
                allContours,
                new Mat(),
                Imgproc.RETR_EXTERNAL,
                Imgproc.CHAIN_APPROX_NONE
        );

        // Filter out noise and display contour
        final List<MatOfPoint> filteredContours = allContours.stream()
                .filter(contour -> {
                    final double value = Imgproc.contourArea(contour);
                    return value > 2000;
                }).collect(Collectors.toList());

        // Get the largest contour
        Optional<MatOfPoint> largestContour = filteredContours.stream()
                .max(Comparator.comparingDouble(Imgproc::contourArea));

        if (!largestContour.isPresent()) {
            return processedImage;
        }
        MatOfPoint contour = largestContour.get();
        // draw Contours

        final Rect rect = Imgproc.boundingRect(contour);
        final Point center = new Point(rect.x + rect.width / 2, rect.y + rect.height / 2);
        Imgproc.drawContours(
                processedImage,
                Collections.singletonList(contour),
                -1, // Negative value indicates that we want to draw all of contours
                new Scalar(255, 0, 0),
                4
        );
        // Draw green dot at the center of the contour
        Imgproc.circle(
                processedImage,
                center,
                2,
                new Scalar(0, 255, 0),
                -1
        );
        RelativePosition relative_position = this.getRelativePosition(center,processedImage);
        //TelemetryHelper.getDashboardTelemetry().addLine(relative_position.toString());
        TelemetryHelper.getTelemetry().addData("Position",relative_position.toString());
        grayImage.release();
        return processedImage;
    }

    public RelativePosition getRelativePosition (Point center, Mat image){

        Point imageCenter = new Point(image.cols() / 2, image.rows() / 2);

        if (imageCenter.x + 100 < center.x) {
            return RelativePosition.right;
        } else if (imageCenter.x - 100 > center.x) {
            return RelativePosition.left;
        } else {
            return RelativePosition.center;
        }
    }

}
