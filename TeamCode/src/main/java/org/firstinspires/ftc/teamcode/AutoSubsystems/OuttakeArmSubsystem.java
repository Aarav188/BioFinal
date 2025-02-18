package org.firstinspires.ftc.teamcode.AutoSubsystems;

import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_BUCKET_DROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_BUCKET_DROP_HIGH;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_RESERVOIR_PICKUP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_RESERVOIR_PICKUP_TELEOP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_REST;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_SPECIMAN;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_SPECIMAN_DROP_BACK;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_SPECIMAN_DROP_FRONT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.LEFT_CLAW_ROTATE;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.RIGHT_CLAW_ROTATE;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class OuttakeArmSubsystem{
    public Servo leftArmServo, rightArmServo;
    public OuttakeArmPos state;

    public enum OuttakeArmPos {

        RESET,
        TRANSFER,
        SPECPICK,
        BUCKDROP,
        SPECDROPBACK,
        SPECDROPFRONT
    }

    public RunAction specPickUp, bucketDrop, frontSpecDrop, backSpecDrop, reset, transfer;

    public OuttakeArmSubsystem(HardwareMap hardwareMap, OuttakeArmPos state){
        leftArmServo = hardwareMap.get(Servo.class, LEFT_CLAW_ROTATE);
        rightArmServo = hardwareMap.get(Servo.class, RIGHT_CLAW_ROTATE);
        rightArmServo.setDirection(Servo.Direction.REVERSE);
        this.state = state;

        reset = new RunAction(this::reset);
        transfer = new RunAction(this::transfer);
        bucketDrop = new RunAction(this::bucketDrop);
        frontSpecDrop = new RunAction(this::frontSpecDrop);
        backSpecDrop = new RunAction(this::backSpecDrop);
        specPickUp = new RunAction(this::specPickUp);
    }

    public void reset(){
        setState(OuttakeArmPos.RESET);
    }
    public void transfer(){
        setState(OuttakeArmPos.TRANSFER);
    }
    public void specPickUp(){
        setState(OuttakeArmPos.SPECPICK);
    }
    public void bucketDrop(){
        setState(OuttakeArmPos.BUCKDROP);
    }

    public void frontSpecDrop(){
        setState(OuttakeArmPos.SPECDROPFRONT);
    }
    public void backSpecDrop(){
        setState(OuttakeArmPos.SPECDROPBACK);
    }
    public void setState(OuttakeArmPos state){
        if(state == OuttakeArmPos.SPECPICK){
            leftArmServo.setPosition(ARM_POS_SPECIMAN);
            rightArmServo.setPosition(ARM_POS_SPECIMAN);
        }
        else if(state == OuttakeArmPos.RESET){
            leftArmServo.setPosition(ARM_POS_REST);
            rightArmServo.setPosition(ARM_POS_REST);
        }
        else if(state == OuttakeArmPos.BUCKDROP){
            leftArmServo.setPosition(ARM_POS_BUCKET_DROP);
            rightArmServo.setPosition(ARM_POS_BUCKET_DROP);
        }
        else if(state == OuttakeArmPos.SPECDROPBACK){
            leftArmServo.setPosition(ARM_POS_SPECIMAN_DROP_BACK);
            rightArmServo.setPosition(ARM_POS_SPECIMAN_DROP_BACK);
        }
        else if(state == OuttakeArmPos.SPECDROPFRONT){
            leftArmServo.setPosition(ARM_POS_SPECIMAN_DROP_FRONT);
            rightArmServo.setPosition(ARM_POS_SPECIMAN_DROP_FRONT);
        }
        else if(state == OuttakeArmPos.TRANSFER){
            leftArmServo.setPosition(ARM_POS_RESERVOIR_PICKUP);
            rightArmServo.setPosition(ARM_POS_RESERVOIR_PICKUP);
        }
    }
    public void init(){
        reset();
    }
    public void start(){
        transfer();
    }
}