package org.firstinspires.ftc.teamcode.bio;



import static org.firstinspires.ftc.teamcode.configs.RobotConfig.CLAW;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.CLAW_CLOSE;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.CLAW_OPEN;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.WRIST;
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

    public OuttakeClawSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        super(hardwareMap, telemetry);
        claw = hardwareMap.get(Servo.class, CLAW);
        wrist = hardwareMap.get(Servo.class, WRIST);
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

    public void setWristSpecimanPickup(){
        wrist.setPosition(WRIST_POS_SPECIMAN_PICK);
        wristPos = OuttakeWristPos.SPECDROPPICK;
    }
    public void setWristSpecimanBack(){
        wrist.setPosition(WRIST_POS_SPECIMAN_BACK);
        wristPos = OuttakeWristPos.SPECDROPBACK;
    }

    public void setWristSpecimanFront(){
        wrist.setPosition(WRIST_POS_SPECIMAN_FRONT);
        wristPos = OuttakeWristPos.SPECDROPFRONT;
    }



}
