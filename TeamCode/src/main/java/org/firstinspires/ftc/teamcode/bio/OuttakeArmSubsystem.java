package org.firstinspires.ftc.teamcode.bio;



import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_BUCKET_DROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_BUCKET_DROP_HIGH;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_RESERVOIR_PICKUP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_REST;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_SPECIMAN;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_SPECIMAN_DROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.LEFT_CLAW_ROTATE;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.RIGHT_CLAW_ROTATE;

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
            leftArmServo.setPosition(ARM_POS_SPECIMAN);
            rightArmServo.setPosition(ARM_POS_SPECIMAN);


    }
    public void bucketDrop(){
        leftArmServo.setPosition(ARM_POS_BUCKET_DROP);
        rightArmServo.setPosition(ARM_POS_BUCKET_DROP);
    }
    public void bucketDropHigh(){
        leftArmServo.setPosition(ARM_POS_BUCKET_DROP_HIGH);
        rightArmServo.setPosition(ARM_POS_BUCKET_DROP_HIGH);
    }
    public void specimanDrop(){
            leftArmServo.setPosition(ARM_POS_SPECIMAN_DROP);
            rightArmServo.setPosition(ARM_POS_SPECIMAN_DROP);
    }
    public void resetArm(){
        leftArmServo.setPosition(ARM_POS_REST);
        rightArmServo.setPosition(ARM_POS_REST);
    }
    public void pickUpPosition(){
            leftArmServo.setPosition(ARM_POS_RESERVOIR_PICKUP);
            rightArmServo.setPosition(ARM_POS_RESERVOIR_PICKUP);
    }



}
