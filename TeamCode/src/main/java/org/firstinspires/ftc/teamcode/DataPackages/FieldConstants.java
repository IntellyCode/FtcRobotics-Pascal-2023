package org.firstinspires.ftc.teamcode.DataPackages;


import com.acmerobotics.roadrunner.geometry.Vector2d;

//Coordinate system with
public class FieldConstants {
    public static final double cmPerBlock = 60;
    public static final Vector2d redStation = new Vector2d(-3*cmPerBlock, -3*cmPerBlock);
    public static final Vector2d blueStation = new Vector2d(-3*cmPerBlock, 3*cmPerBlock);
    public static final Vector2d redBoard = new Vector2d(3*cmPerBlock, 2*cmPerBlock);
    public static final Vector2d blueBoard = new Vector2d(3*cmPerBlock, -2*cmPerBlock);
    public static final Vector2d bluePetlja = new Vector2d(-1*cmPerBlock, -2*cmPerBlock);
    public static final Vector2d redPetlja = new Vector2d(-1*cmPerBlock, 2*cmPerBlock);
    public static final double boardRelPos1 = 15; //cm
    public static final double boardRelPos2 = 30;
    public static final double boardRelPos3 = 45;
    public static final double initRotAngle = 15; //deg
}
