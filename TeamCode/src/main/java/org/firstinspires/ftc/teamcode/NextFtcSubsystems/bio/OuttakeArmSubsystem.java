package org.firstinspires.ftc.teamcode.NextFtcSubsystems.bio;


import static org.firstinspires.ftc.teamcode.configs.OuttakeArmPos.BUCKDROP;
import static org.firstinspires.ftc.teamcode.configs.OuttakeArmPos.BUCKDROPHIGH;
import static org.firstinspires.ftc.teamcode.configs.OuttakeArmPos.DEPOPICK;
import static org.firstinspires.ftc.teamcode.configs.OuttakeArmPos.DEPOPICKTELE;
import static org.firstinspires.ftc.teamcode.configs.OuttakeArmPos.RESET;
import static org.firstinspires.ftc.teamcode.configs.OuttakeArmPos.SPECDROPBACK;
import static org.firstinspires.ftc.teamcode.configs.OuttakeArmPos.SPECDROPFRONT;
import static org.firstinspires.ftc.teamcode.configs.OuttakeArmPos.SPECPICK;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.EXTENDO_LEFT_IN_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.EXTENDO_LEFT_MAX_OUT_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.EXTENDO_RIGHT_IN_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.EXTENDO_RIGHT_MAX_OUT_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.LEFT_CLAW_ROTATE;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.RIGHT_CLAW_ROTATE;
import static org.firstinspires.ftc.teamcode.configs.RobotState.armPos;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.MultipleServosToSeperatePositions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.configs.OuttakeArmPos;

import java.util.HashMap;

public class OuttakeArmSubsystem extends Subsystem {

    private Servo leftArmServo;
    private Servo rightArmServo;

    public static final OuttakeArmSubsystem INSTANCE = new OuttakeArmSubsystem();
    private OuttakeArmSubsystem() { }

    @Override
    public void initialize() {
        leftArmServo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, LEFT_CLAW_ROTATE);
        rightArmServo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, RIGHT_CLAW_ROTATE);
        rightArmServo.setDirection(Servo.Direction.REVERSE);
    }

    public Command specPickUp() {
        HashMap<Servo, Double> servos = new HashMap<>();
        servos.put(leftArmServo, SPECPICK.getPosition());
        servos.put(rightArmServo, SPECPICK.getPosition());
        return new MultipleServosToSeperatePositions(servos,this);
    }

    public Command specDrop() {
        HashMap<Servo, Double> servos = new HashMap<>();
        servos.put(leftArmServo, SPECDROPFRONT.getPosition());
        servos.put(rightArmServo, SPECDROPFRONT.getPosition()           );
        return new MultipleServosToSeperatePositions(servos,this); // IMPLEMENTED SUBSYSTEM
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
    public void specimanDropBack(){
            leftArmServo.setPosition(SPECDROPBACK.getPosition());
            rightArmServo.setPosition(SPECDROPBACK.getPosition());
            armPos = SPECDROPBACK;
    }
    public void specimanDropFront(){
        leftArmServo.setPosition(SPECDROPFRONT.getPosition());
        rightArmServo.setPosition(SPECDROPFRONT.getPosition());
        armPos = SPECDROPFRONT;
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

    public void pickUpPositionTele(){
        leftArmServo.setPosition(DEPOPICKTELE.getPosition());
        rightArmServo.setPosition(DEPOPICKTELE.getPosition());
        armPos = DEPOPICK;
    }



}
