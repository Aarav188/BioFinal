package org.firstinspires.ftc.teamcode.bio;


import static org.firstinspires.ftc.teamcode.configs.RobotConfig.BACK_INTAKE_SERVO;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.BACK_ROTATION_SERVO;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.HashMap;
import java.util.Map;


//TODO no clue if the rotation servo actually does that so fix that then get the values
public class BackIntakeSubsystem extends TacoSubsystem {

    private final Servo backIntakeServo;
    private final Servo backRotationServo;

    public BackIntakeSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        super(hardwareMap, telemetry);
        backIntakeServo = hardwareMap.get(Servo.class, BACK_INTAKE_SERVO);
        backRotationServo = hardwareMap.get(Servo.class, BACK_ROTATION_SERVO);
    }

    public void pickUp() {
        backIntakeServo.setPosition(0);
    }

    public void reset() {
        backIntakeServo.setPosition(0);
    } //TODO get both these values

    public void rotateToReservoir(){
        backRotationServo.setPosition(0);
    }

    public void rotateToPickUp(){
        backRotationServo.setPosition(0);
    }

}
