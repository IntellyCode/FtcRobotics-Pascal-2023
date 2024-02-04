package org.firstinspires.ftc.teamcode.additional.DataPackages;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class BeltData {
    public float ticksCap;
    DcMotorEx rightBelt;
    public DcMotorEx getBeltMotor1() {
        return rightBelt;
    }
    DcMotorEx leftBelt;
    public DcMotorEx getBeltMotor2() {
        return leftBelt;
    }
    public BeltData(HardwareMap map) {
        rightBelt = map.get(DcMotorEx.class, "rightBelt");
        rightBelt.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBelt = map.get(DcMotorEx.class, "leftBelt");


    }
}
