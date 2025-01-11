package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_SPECIMAN;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.EXTENDO_LEFT_IN_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.EXTENDO_RIGHT_IN_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_LARGE_ROTATOR_DOWN_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_LARGE_ROTATOR_UP_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_STOPPER_DOWN_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.WRIST_POS_REST;

import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class AllMotorAndServoTester extends OpMode {
    private DcMotor motor1;
    private DcMotor motor2;
    private DcMotor motor3;
    private DcMotor motor4;
    private DcMotor motor5;
    private DcMotor motor6;
    private DcMotor motor7;
    private DcMotor motor8;

    private Servo stopper;
    private Servo intakeRotator;
    private Servo claw;
    private Servo wrist;
    private Servo leftClawRotator;
    private Servo rightClawRotator;

    private Servo leftLinkage;

    private Servo rightLinkage;

    @Override
    public void init() {

        motor1 = hardwareMap.dcMotor.get("intake") ;


        stopper = hardwareMap.servo.get("stopper");
        intakeRotator = hardwareMap.servo.get("intakeRotator");
        claw = hardwareMap.servo.get("claw");
        wrist = hardwareMap.servo.get("outtakeRotator");
        leftClawRotator = hardwareMap.servo.get("leftArmRotator");
        rightClawRotator = hardwareMap.servo.get("rightArmRotator");
        leftLinkage = hardwareMap.servo.get("leftLinkage");
        rightLinkage = hardwareMap.servo.get("rightLinkage");
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            stopper.setPosition(INTAKE_STOPPER_DOWN_POSITION);
        }
        if (gamepad1.b) { // 0.27 depo reset
            intakeRotator.setPosition(INTAKE_LARGE_ROTATOR_UP_POSITION);
        }
        if (gamepad1.y) {
            claw.setPosition(INTAKE_STOPPER_DOWN_POSITION);
        }
        if (gamepad1.x) {
            motor1.setPower(1);
        }
        else{
            motor1.setPower(0);
        }
        if (gamepad1.dpad_down) {
            leftClawRotator.setPosition(ARM_POS_SPECIMAN);
            rightClawRotator.setPosition(1-ARM_POS_SPECIMAN); // reverse right
        }
        if (gamepad1.dpad_left) {
            wrist.setPosition(WRIST_POS_REST);
        }

        if (gamepad2.y) {
            leftLinkage.setPosition(EXTENDO_LEFT_IN_POSITION);
        }
        if (gamepad2.x) {
            rightLinkage.setPosition(EXTENDO_RIGHT_IN_POSITION);
        }

    }
}

