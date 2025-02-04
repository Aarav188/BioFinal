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
import org.firstinspires.ftc.teamcode.configs.OuttakeWristPos;

public class OuttakeClawSubsystem extends TacoSubsystem {
    private final Servo claw;
    private final Servo wrist;
    private final Servo stopper;

    public OuttakeClawSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        super(hardwareMap, telemetry);
        claw = hardwareMap.get(Servo.class, CLAW);
        wrist = hardwareMap.get(Servo.class, WRIST);
        stopper = hardwareMap.get(Servo.class, OUTTAKE_STOPPER);
    }


    public void lockSample(){
        stopper.setPosition(OUTTAKE_STOPPER_LOCK);
    }
    public void unlockSample(){
        stopper.setPosition(OUTTAKE_STOPPER_UNLOCK);
    }
    public void closeClaw() {
        claw.setPosition(CLAW_CLOSE);
        outtakeClawOpen = false;
    }
    public void openClaw(){
        claw.setPosition(CLAW_OPEN);
        outtakeClawOpen = true;
    }
    public void setWristReset(){
        wrist.setPosition(WRIST_POS_REST);
        wristPos = OuttakeWristPos.RESET;
    }

    public void setWristDepoReset(){
        wrist.setPosition(WRIST_POS_DEPO_RESET);
    }

    public void setWristDepoResetTele(){
        wrist.setPosition(WRIST_POS_DEPO_RESET_TELEOP);
    }
    public void setWristSpecimanPickup(){
        wrist.setPosition(WRIST_POS_SPECIMAN_PICK);
        wristPos = OuttakeWristPos.SPECDROPPICK;
    }
    public void setWristSpecimanBack(){
        wrist.setPosition(WRIST_POS_SPECIMAN_BACK);
        wristPos = OuttakeWristPos.SPECDROPBACK;
    }
    public void bucketDrop(){
        wrist.setPosition(WRIST_POS_BUCKET_DROP);
        wristPos = OuttakeWristPos.SPECDROPBACK;
    }

    public void setWristSpecimanFront(){
        wrist.setPosition(WRIST_POS_SPECIMAN_FRONT);
        wristPos = OuttakeWristPos.SPECDROPFRONT;
    }



}
