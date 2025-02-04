package org.firstinspires.ftc.teamcode.AutoSubsystems;

import static org.firstinspires.ftc.teamcode.configs.RobotConfig.LEFT_CLAW_ROTATE;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.RIGHT_CLAW_ROTATE;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.commands.intake.Outtake;
import org.firstinspires.ftc.teamcode.configs.OuttakeArmPos;

public class OuttakeArmSubsystem{
    public Servo leftArmServo, rightArmServo;
    public OuttakeArmPos state;

    public RunAction specPickUp, bucketDrop, frontSpecDrop, backSpecDrop, reset, transfer;

    public OuttakeArmSubsystem(HardwareMap hardwareMap, OuttakeArmPos state){
        leftArmServo = hardwareMap.get(Servo.class, LEFT_CLAW_ROTATE);
        rightArmServo = hardwareMap.get(Servo.class, RIGHT_CLAW_ROTATE);
        this.state = state;

        reset = new RunAction(this::reset);
        transfer = new RunAction(this::transfer);
        specPickUp = new RunAction(this::specPickUp);
        bucketDrop = new RunAction(this::bucketDrop);
        frontSpecDrop = new RunAction(this::frontSpecDrop);
        backSpecDrop = new RunAction(this::backSpecDrop);
    }

    public void reset(){
        setState(OuttakeArmPos.RESET);
    }
    public void transfer(){
        setState(OuttakeArmPos.DEPOPICK);
    }
    public void specPickUp(){
        setState(OuttakeArmPos.SPECPICK);
    }
    public void bucketDrop(){
        setState(OuttakeArmPos.BUCKDROPHIGH);
    }

    public void frontSpecDrop(){
        setState(OuttakeArmPos.SPECDROPFRONT);
    }
    public void backSpecDrop(){
        setState(OuttakeArmPos.SPECDROPBACK);
    }
    public void setState(OuttakeArmPos state){
        if(state == OuttakeArmPos.DEPOPICK){
            leftArmServo.setPosition(OuttakeArmPos.DEPOPICK.getPosition());
            rightArmServo.setPosition(OuttakeArmPos.DEPOPICK.getPosition());
        }
        else if(state == OuttakeArmPos.RESET){
            leftArmServo.setPosition(OuttakeArmPos.RESET.getPosition());
            rightArmServo.setPosition(OuttakeArmPos.RESET.getPosition());
        }
        else if(state == OuttakeArmPos.BUCKDROPHIGH){
            leftArmServo.setPosition(OuttakeArmPos.BUCKDROPHIGH.getPosition());
            rightArmServo.setPosition(OuttakeArmPos.BUCKDROPHIGH.getPosition());
        }
        else if(state == OuttakeArmPos.SPECDROPBACK){
            leftArmServo.setPosition(OuttakeArmPos.SPECDROPBACK.getPosition());
            rightArmServo.setPosition(OuttakeArmPos.SPECDROPBACK.getPosition());
        }
        else if(state == OuttakeArmPos.SPECDROPFRONT){
            leftArmServo.setPosition(OuttakeArmPos.SPECDROPFRONT.getPosition());
            rightArmServo.setPosition(OuttakeArmPos.SPECDROPFRONT.getPosition());
        }
        else if(state == OuttakeArmPos.SPECPICK){
            leftArmServo.setPosition(OuttakeArmPos.SPECPICK.getPosition());
            rightArmServo.setPosition(OuttakeArmPos.SPECPICK.getPosition());
        }
    }
    public void init(){
        reset();
    }
    public void start(){
        transfer();
    }
}