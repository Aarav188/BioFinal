package org.firstinspires.ftc.teamcode.bio;



import static org.firstinspires.ftc.teamcode.configs.OuttakeArmPos.BUCKDROP;
import static org.firstinspires.ftc.teamcode.configs.OuttakeArmPos.BUCKDROPHIGH;
import static org.firstinspires.ftc.teamcode.configs.OuttakeArmPos.DEPOPICK;
import static org.firstinspires.ftc.teamcode.configs.OuttakeArmPos.RESET;
import static org.firstinspires.ftc.teamcode.configs.OuttakeArmPos.SPECDROP;
import static org.firstinspires.ftc.teamcode.configs.OuttakeArmPos.SPECPICK;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_BUCKET_DROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_BUCKET_DROP_HIGH;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_RESERVOIR_PICKUP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_REST;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_SPECIMAN;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_SPECIMAN_DROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.LEFT_CLAW_ROTATE;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.RIGHT_CLAW_ROTATE;
import static org.firstinspires.ftc.teamcode.configs.RobotState.armPos;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.HashMap;
import java.util.Map;

public class OuttakeArmSubsystem extends TacoSubsystem {

    private final Servo leftArmServo;
    private final Servo rightArmServo;

    public OuttakeArmSubsystem(HardwareMap hardwareMap, Telemetry dashboardTelemetry) {
        super(hardwareMap, dashboardTelemetry);
        leftArmServo = hardwareMap.get(Servo.class, LEFT_CLAW_ROTATE);
        rightArmServo = hardwareMap.get(Servo.class, RIGHT_CLAW_ROTATE);
        rightArmServo.setDirection(Servo.Direction.REVERSE);
    }

    public void specimanPickUp(){
            leftArmServo.setPosition(SPECPICK.getPosition());
            rightArmServo.setPosition(SPECPICK.getPosition());
            armPos = SPECPICK;

    }
    public void bucketDrop(){
        leftArmServo.setPosition(BUCKDROP.getPosition());
        rightArmServo.setPosition(BUCKDROP.getPosition());
        armPos = BUCKDROP;
    }
    public void bucketDropHigh(){
        leftArmServo.setPosition(BUCKDROPHIGH.getPosition());
        rightArmServo.setPosition(BUCKDROPHIGH.getPosition());
        armPos = BUCKDROPHIGH;
    }
    public void specimanDrop(){
            leftArmServo.setPosition(SPECDROP.getPosition());
            rightArmServo.setPosition(SPECPICK.getPosition());
            armPos = SPECDROP;
    }
    public void resetArm(){
        leftArmServo.setPosition(RESET.getPosition());
        rightArmServo.setPosition(RESET.getPosition());
        armPos = RESET;
    }
    public void pickUpPosition(){
            leftArmServo.setPosition(DEPOPICK.getPosition());
            rightArmServo.setPosition(DEPOPICK.getPosition());
            armPos = DEPOPICK;
    }



}
