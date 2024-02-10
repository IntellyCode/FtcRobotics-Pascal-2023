package org.firstinspires.ftc.teamcode.Camera.Util;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

public class ColorRange {
    Scalar low;
    Scalar high;
    public ColorRange(Scalar low,Scalar high){
        this.low = low;
        this.high = high;
    }

    public Scalar getLow() {
        return low;
    }
    public Scalar getHigh(){
        return high;
    }
}
