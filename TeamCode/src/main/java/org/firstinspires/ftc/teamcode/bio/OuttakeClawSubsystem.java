package org.firstinspires.ftc.teamcode.bio;



import static org.firstinspires.ftc.teamcode.configs.RobotConfig.CLAW;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.CLAW_CLOSE;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.CLAW_OPEN;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.WRIST;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.WRIST_POS_REST;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.WRIST_POS_SPECIMAN;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.HashMap;
import java.util.Map;

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
    }
    public void openClaw(){
        claw.setPosition(CLAW_OPEN);
    }
    public void setWristReset(){
        wrist.setPosition(WRIST_POS_REST);
    }
    public void setWristSpeciman(){
        wrist.setPosition(WRIST_POS_SPECIMAN);
    }



}
