package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_SPECIMAN;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_KP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_LEFT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_POWER;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_RIGHT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_TOLERANCE;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.EXTENDO_LEFT_IN_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.EXTENDO_RIGHT_IN_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_LARGE_ROTATOR_DOWN_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_LARGE_ROTATOR_UP_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_STOPPER_DOWN_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.WRIST_POS_REST;
import static org.firstinspires.ftc.teamcode.configs.RobotState.targetHeight;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.configs.ElevatorHeights;

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

    private MotorEx leftElevationMotor;
    private MotorEx rightElevationMotor;
    private MotorGroup elevationMotors;

    private ElevatorHeights elevatorHeights;

    private int position;

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
        leftElevationMotor = new MotorEx(hardwareMap, ELEVATOR_MOTOR_LEFT, Motor.GoBILDA.RPM_1150); //TODO Check the rpm
        rightElevationMotor = new MotorEx(hardwareMap, ELEVATOR_MOTOR_RIGHT, Motor.GoBILDA.RPM_1150);
        rightElevationMotor.setInverted(true); //TODO check which one is inverted, if any
        elevationMotors = new MotorGroup(rightElevationMotor, leftElevationMotor);
        elevatorHeights = ElevatorHeights.RESET;

        elevationMotors.setRunMode(Motor.RunMode.PositionControl);
        elevationMotors.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        elevationMotors.setPositionCoefficient(ELEVATOR_MOTOR_KP);
        elevationMotors.setPositionTolerance(ELEVATOR_MOTOR_TOLERANCE);

        position = 0;
        elevationMotors.resetEncoder();
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
            setElevationMotorTargetPosition(position);
            position = position - 10;
        }
        if (gamepad1.x) {
            setElevationMotorTargetPosition(position);
            position = position + 10;
        }

//        if (gamepad1.y) {
//            claw.setPosition(INTAKE_STOPPER_DOWN_POSITION);
//        }
//        if (gamepad1.x) {
//            motor1.setPower(1);
//        }
//        else{
//            motor1.setPower(0);
//        }
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

    public void setSlidesPower(){
        elevationMotors.set(0.0);
    }


    private void setElevationMotorTargetPosition(int position) {
        elevationMotors.setTargetPosition(position);
        if(isAtTarget()){
            elevationMotors.setTargetPosition(0);
        }
    }

    public int getCurrentPosition(){
        return elevatorHeights.getMotorPosition();
    } //dunno if this works or not


    public void updateElevationPosition(ElevatorHeights elevatorHeights) {
        if(elevatorHeights == ElevatorHeights.RESET){
            elevationMotors.set(1.0);
        } else {
            elevationMotors.set(ELEVATOR_MOTOR_POWER);
        }


    }
    public void specDropOff(){
        elevationMotors.setTargetPosition(rightElevationMotor.encoder.getPosition() - 400);
    }
    public void updateElevationPosition(){
        elevationMotors.set(ELEVATOR_MOTOR_POWER);
    }

    public void updateElevatorDownPostion(){
        elevationMotors.set(0.1);
    }

    /**
     * decrease that 20 if pid is good, increase if bad, essentially the margin of error
     */
    public boolean isAtTarget() {
        if(Math.abs(getPosition() - elevatorHeights.getMotorPosition()) < 10){
            return true;
        }
        return false;
    }
    public boolean isAtIncrementTarget(int target){
        if(Math.abs(getPosition() - target) < 50){
            return true;
        }
        return false;
    }

    public int getPosition() {
        return rightElevationMotor.getCurrentPosition();
    }

    public ElevatorHeights targetPosition(){
        return elevatorHeights;
    }
    public void stop() {
        elevationMotors.stopMotor();
    }


    public void resetEncoder() {
        elevationMotors.resetEncoder();
    }

    public void resetStop(){
        if(rightElevationMotor.encoder.getPosition() >=0 && rightElevationMotor.encoder.getPosition() <= 300) {
            elevationMotors.stopMotor();
        }
    }

    public void elevatorAutoStop(){
        if(elevatorHeights != ElevatorHeights.RESET) {
            elevationMotors.set(0.1);
        }
        else{
            elevationMotors.stopMotor();
        }
    }
    public void maintainPosition(){
        elevationMotors.set(0.1);
    }
    public void setTargetPosition(ElevatorHeights elevatorHeights) {
        if(this.elevatorHeights != null & this.elevatorHeights == elevatorHeights){
            this.elevatorHeights = ElevatorHeights.RESET;
            targetHeight = ElevatorHeights.RESET;
        }
        else {
            this.elevatorHeights = elevatorHeights;
            targetHeight = elevatorHeights;
        }
        setElevationMotorTargetPosition(elevatorHeights.getMotorPosition());

    }
    public void setTargetPositionMaintain(ElevatorHeights elevatorHeights){
        setElevationMotorTargetPosition(elevatorHeights.getMotorPosition());
    }

    public void goToRawPosition(int target, double power) {
        elevationMotors.setTargetPosition(target);
        while (!elevationMotors.atTargetPosition()) {
            if (elevationMotors.getCurrentPosition() > target) {
                break;
            }
            elevationMotors.set(power);
        }
        elevationMotors.set(0);
    }


    public void configureMotors() {
        elevationMotors.setRunMode(Motor.RunMode.PositionControl);
        elevationMotors.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        elevationMotors.setPositionCoefficient(ELEVATOR_MOTOR_KP);
        elevationMotors.setPositionTolerance(ELEVATOR_MOTOR_TOLERANCE);
    }
}

