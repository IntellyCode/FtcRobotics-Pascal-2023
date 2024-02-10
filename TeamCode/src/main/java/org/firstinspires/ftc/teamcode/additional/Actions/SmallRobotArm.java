package org.firstinspires.ftc.teamcode.additional.Actions;

import org.firstinspires.ftc.teamcode.additional.DataPackages.SmallRobotArmData;

public class SmallRobotArm {
    SmallRobotArmData armData;

    public SmallRobotArm(SmallRobotArmData armData) {
        this.armData = armData;
    }

    public void liftTheUpperJointByAngle(double deltaAngleRad) {
        //Calculate ticks required
        int ticks = (int) (deltaAngleRad * (armData.upperMotorTicksPerRev/(2*Math.PI)));

        //Run motors to new required position
        armData.getRightArmMotor().setTargetPosition(armData.getRightArmMotor().getCurrentPosition() + ticks);
        armData.getLeftArmMotor().setTargetPosition(armData.getLeftArmMotor().getCurrentPosition() + ticks);
    }

    public void localize()
    {

    }
}
