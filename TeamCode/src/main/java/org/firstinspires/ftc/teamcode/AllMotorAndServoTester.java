package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_BUCKET_DROP_HIGH;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_SPECIMAN;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_KP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_LEFT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_POWER;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_RIGHT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_TOLERANCE;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.EXTENDO_LEFT_IN_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.EXTENDO_RIGHT_IN_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.HANG;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_LARGE_ROTATOR_DOWN_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_LARGE_ROTATOR_UP_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_STOPPER_DOWN_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_STOPPER_UP_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.WRIST_POS_REST;
import static org.firstinspires.ftc.teamcode.configs.RobotState.targetHeight;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.AutoSubsystems.ElevatorSubsystem;
import org.firstinspires.ftc.teamcode.AutoSubsystems.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.AutoSubsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.AutoSubsystems.OuttakeArmSubsystem;
import org.firstinspires.ftc.teamcode.AutoSubsystems.OuttakeClawSubsystem;
import org.firstinspires.ftc.teamcode.commands.elevator.ElevatorIncrementCommand;
import org.firstinspires.ftc.teamcode.commands.elevator.ResetElevatorCommand;
import org.firstinspires.ftc.teamcode.configs.ElevatorHeights;
import org.firstinspires.ftc.teamcode.configs.OuttakeArmPos;

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
    private ElevatorSubsystem elevatorSubsystem;
    private ExtendoSubsystem extendoSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private IntakeSubsystem.SpinState spinState;
    private IntakeSubsystem.RotatorState rotatorState;
    private IntakeSubsystem.StopperState stopperState;
    private OuttakeArmSubsystem outtakeArmSubsystem;
    private OuttakeArmSubsystem.OuttakeArmPos outtakeArmPos;
    private OuttakeClawSubsystem outtakeClawSubsystem;
    private OuttakeClawSubsystem.ClawGrabState clawGrabState;
    private OuttakeClawSubsystem.SampleGrabState sampleGrabState;
    private OuttakeClawSubsystem.WristState wristState;
    private int position;

    @Override
    public void init() {

        motor1 = hardwareMap.dcMotor.get(ELEVATOR_MOTOR_LEFT) ;
        motor2 = hardwareMap.dcMotor.get(ELEVATOR_MOTOR_RIGHT);


//        stopper = hardwareMap.servo.get("stopper");
//        intakeRotator = hardwareMap.servo.get("intakeRotator");
//        claw = hardwareMap.servo.get("claw");
//        wrist = hardwareMap.servo.get("outtakeRotator");
//        leftClawRotator = hardwareMap.servo.get("leftArmRotator");
//        rightClawRotator = hardwareMap.servo.get("rightArmRotator");
//        rightClawRotator.setDirection(Servo.Direction.REVERSE);
//        leftLinkage = hardwareMap.servo.get("leftLinkage");
//        rightLinkage = hardwareMap.servo.get("rightLinkage");
        FtcDashboard dashboard = FtcDashboard.getInstance();
        Telemetry dashboardTelemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        elevatorSubsystem = new ElevatorSubsystem(hardwareMap, dashboardTelemetry);
        elevatorSubsystem.init();
//        intakeSubsystem = new IntakeSubsystem(hardwareMap, spinState, rotatorState, stopperState);
//        outtakeArmSubsystem = new OuttakeArmSubsystem(hardwareMap, outtakeArmPos);
//        outtakeClawSubsystem = new OuttakeClawSubsystem(hardwareMap, wristState, sampleGrabState, clawGrabState);
//        extendoSubsystem = new ExtendoSubsystem(hardwareMap, telemetry);

        position = 0;
    }

    @Override
    public void start(){
        elevatorSubsystem.start();
    }

    @Override
    public void loop() {
        elevatorSubsystem.updateTelem();
        elevatorSubsystem.updatePIDF();
//        if (gamepad1.x) {
//            motor2.setPower(-0.5);
//        }
//        if (gamepad1.y) { // 0.27 depo reset
//            motor2.setPower(-0.5);
//        }
//        if(gamepad1.a){
//            motor1.setPower(-1); //towards back
//            motor2.setPower(-1);
//        }
//        else{
//            motor1.setPower(0);
//            motor2.setPower(0);
//        }
//        if(gamepad1.b){
//            motor2.setPower(0.5); // towards front
//        }
//        if(gamepad1.dpad_right){
//            outtakeClawSubsystem.lockSample();
//            intakeSubsystem.lockSample();
//        }
//        if(gamepad1.dpad_up){
//            extendoSubsystem.fullExtend();
//        }
        if(gamepad2.x){
            elevatorSubsystem.toHighBucket();
        }
        if(gamepad2.y){
            elevatorSubsystem.toReset();
        }
//        if(gamepad2.y){
//            outtakeArmSubsystem.backSpecDrop();
//            outtakeClawSubsystem.specBack();
//        }
//
//        if(gamepad2.a){
//            outtakeArmSubsystem.frontSpecDrop();
//            outtakeClawSubsystem.specFront();
//        }
//
//        if(gamepad2.dpad_left){
//            outtakeArmSubsystem.bucketDrop();
//            outtakeClawSubsystem.bucketDrop();
//        }
//        if(gamepad2.dpad_right){
//            outtakeArmSubsystem.reset();
//            outtakeClawSubsystem.reset();
//        }
//
//
//
////        if (gamepad1.y) {
////            claw.setPosition(INTAKE_STOPPER_DOWN_POSITION);
////        }
////        if (gamepad1.x) {
////            motor1.setPower(1);
////        }
////        else{
////            motor1.setPower(0);
////        }
//        if (gamepad1.dpad_down) {
//
//        }
//        if (gamepad1.dpad_left) {
//            wrist.setPosition(WRIST_POS_REST);
//        }

    }
}

