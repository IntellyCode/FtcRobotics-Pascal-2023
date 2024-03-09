package org.firstinspires.ftc.teamcode.DataPackages;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

//Coordinate system with
public class FieldConstants {
    public static final double cmPerBlock = 60;
    public static final Vector2d redBoard = new Vector2d(2.8*cmPerBlock, 1.5*cmPerBlock);
    public static final Vector2d blueBoard = new Vector2d(2.8*cmPerBlock, -1.5*cmPerBlock);
    public static final Vector2d bluePetlja = new Vector2d(-1*cmPerBlock, -2*cmPerBlock);
    public static final Vector2d redPetlja = new Vector2d(-1*cmPerBlock, 2*cmPerBlock);
    public static final double boardRelPos1 = 15; //cm
    public static final double boardRelPos2 = 30;
    public static final double boardRelPos3 = 45;
    public static final double initRotAngle = 20; //deg

    public static final Vector2d blueRight = new Vector2d(-1.5*cmPerBlock,-2.5*cmPerBlock);
    public static final Vector2d blueLeft = new Vector2d(0.5*cmPerBlock,-2.5*cmPerBlock);
    public static final Vector2d redLeft = new Vector2d(-1.5*cmPerBlock,2.5*cmPerBlock);
    public static final Vector2d redRight = new Vector2d(0.5*cmPerBlock,2.5*cmPerBlock);
}
