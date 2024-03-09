package org.firstinspires.ftc.teamcode.Camera.Data;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.Camera.Util.RelativePosition;

public class Coordinates {
    public static Pose2d getParkingPosition(RelativePosition rlp){
        Pose2d pos;

        switch(rlp){
            case left:pos = new Pose2d(100, 100, 0);break;
            case right:pos = new Pose2d(100, -100, 0);break;
            default: pos = new Pose2d(100, 0, 0);break;
        }

        return pos;
    }
}
