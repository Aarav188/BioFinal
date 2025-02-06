package org.firstinspires.ftc.teamcode.AutoSubsystems;


import static org.firstinspires.ftc.teamcode.configs.RobotConfig.CLAW;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.CLAW_CLOSE;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.CLAW_OPEN;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.OUTTAKE_STOPPER;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.OUTTAKE_STOPPER_LOCK;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.OUTTAKE_STOPPER_UNLOCK;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.WRIST;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.WRIST_POS_BUCKET_DROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.WRIST_POS_DEPO_RESET;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.WRIST_POS_DEPO_RESET_TELEOP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.WRIST_POS_REST;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.WRIST_POS_SPECIMAN_BACK;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.WRIST_POS_SPECIMAN_FRONT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.WRIST_POS_SPECIMAN_PICK;
import static org.firstinspires.ftc.teamcode.configs.RobotState.outtakeClawOpen;
import static org.firstinspires.ftc.teamcode.configs.RobotState.wristPos;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.BucketAuto;
import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.configs.OuttakeWristPos;

public class OuttakeClawSubsystem{
    private final Servo claw;
    private final Servo wrist;
    private final Servo stopper;
    public enum ClawGrabState{
        CLOSED, OPEN
    }
    public enum SampleGrabState{
        LOCK, UNLOCK
    }
    public enum WristState{
        PICKUP, RESET, TRANSFER, SPECDROPFRONT, SPECDROPBACK, BUCKETDROP
    }
    public WristState wristState;
    public SampleGrabState sampleGrabState;
    public ClawGrabState clawGrabState;

    public RunAction wristPickup, wristReset, wristTransfer, specFront, specBack, bucketdrop;
    public OuttakeClawSubsystem(HardwareMap hardwareMap, WristState wristState, SampleGrabState sampleGrabState, ClawGrabState clawGrabState) {
        claw = hardwareMap.get(Servo.class, CLAW);
        wrist = hardwareMap.get(Servo.class, WRIST);
        stopper = hardwareMap.get(Servo.class, OUTTAKE_STOPPER);

        wristPickup = new RunAction(this::pickup);
        wristReset = new RunAction(this::reset);
        wristTransfer = new RunAction(this::transfer);
        specFront = new RunAction(this::specFront);
        specBack = new RunAction(this::specBack);
        bucketdrop = new RunAction(this::bucketDrop);
    }


    //wristState//
    public void setWristState(WristState wristState) {
        if (wristState == WristState.TRANSFER) {
            wrist.setPosition(WRIST_POS_REST);
            this.wristState = WristState.TRANSFER;
        } else if (wristState == WristState.RESET) {
            wrist.setPosition(WRIST_POS_DEPO_RESET);
            this.wristState = WristState.RESET;
        } else if (wristState == WristState.PICKUP) {
            wrist.setPosition(WRIST_POS_SPECIMAN_PICK);
            this.wristState = WristState.PICKUP;
        } else if (wristState == WristState.BUCKETDROP) {
            wrist.setPosition(WRIST_POS_BUCKET_DROP);
            this.wristState = WristState.BUCKETDROP;
        } else if (wristState == WristState.SPECDROPBACK) {
            wrist.setPosition(WRIST_POS_SPECIMAN_BACK);
            this.wristState = WristState.SPECDROPBACK;
        } else if (wristState == WristState.SPECDROPFRONT) {
            wrist.setPosition(WRIST_POS_SPECIMAN_FRONT);
            this.wristState = WristState.SPECDROPFRONT;
        }
    }

    public void setClawGrabState(ClawGrabState clawGrabState) {
        if (clawGrabState == ClawGrabState.CLOSED) {
            claw.setPosition(CLAW_CLOSE);
        } else if (clawGrabState == ClawGrabState.OPEN) {
            claw.setPosition(CLAW_OPEN);
        }
    }

    public void setSampleGrabState(SampleGrabState sampleGrabState) {
        if (sampleGrabState == SampleGrabState.LOCK) {
            stopper.setPosition(OUTTAKE_STOPPER_LOCK);
        } else if (sampleGrabState == SampleGrabState.UNLOCK) {
            stopper.setPosition(OUTTAKE_STOPPER_UNLOCK);
        }
    }

    public void lockSample(){
        setSampleGrabState(SampleGrabState.LOCK);
    }
    public void unlockSample(){
        setSampleGrabState(SampleGrabState.UNLOCK);
    }
    public void closeClaw() {
        setClawGrabState(ClawGrabState.CLOSED);
    }
    public void openClaw(){
        setClawGrabState(ClawGrabState.OPEN);
    }
    public void transfer(){
        setWristState(WristState.TRANSFER);
    }

    public void reset(){
        setWristState(WristState.RESET);
    }

    public void pickup(){
        setWristState(WristState.PICKUP);
    }
    public void specBack(){
        setWristState(WristState.SPECDROPBACK);
    }
    public void bucketDrop(){
        setWristState(WristState.BUCKETDROP);
    }

    public void specFront(){
        setWristState(WristState.SPECDROPFRONT);
    }


}
