package org.firstinspires.ftc.teamcode.Actions;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.DataPackages.BigRobotArmData;
import org.firstinspires.ftc.teamcode.Roadrunner.drive.SampleMecanumDrive;

public class PutThePixelAction implements IAction{
    SampleMecanumDrive drive;
    BigRobotArmData armData;
    boolean isFinished;
    boolean left;
    boolean right;
    Pose2d standPosition;
    IAction sequence;
    public PutThePixelAction(SampleMecanumDrive drive, BigRobotArmData armData, Pose2d standPosition, boolean right, boolean left) {
        this.armData = armData;
        this.drive = drive;
        this.standPosition = standPosition;
        this.left = left;
        this.right = right;
    }
    @Override
    public void start() {
        Pose2d targetPosition = new Pose2d(standPosition.getX() - 20, standPosition.getY(), standPosition.getHeading());
        MoveToPositionRectangular approachAction = new MoveToPositionRectangular(drive, targetPosition, Axis.Vertical);
        LiftToAngleBigRobot liftAction = new LiftToAngleBigRobot(armData, 35);
        ExtendTheArmAction extendAction = new ExtendTheArmAction(armData, 110);
        ReleasePixelAction releaseAction = new ReleasePixelAction(armData, right, left);
        sequence = new SequencedAction(approachAction, liftAction, extendAction, releaseAction);
        sequence.start();
    }

    @Override
    public void update() {
        if(sequence != null && sequence.isOver())
            return;
        if(sequence != null)
            sequence.update();
    }

    @Override
    public boolean isOver() {
        return isFinished;
    }
}
