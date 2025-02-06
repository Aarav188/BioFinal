package org.firstinspires.ftc.teamcode.AutoSubsystems;


import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_LARGE_ROTATOR_DOWN_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_LARGE_ROTATOR_UP_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_POWER;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_ROTATION_MOTOR;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_ROTATOR;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_STOPPER;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_STOPPER_DOWN_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_STOPPER_UP_POSITION;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class IntakeSubsystem{
    private final MotorEx rotationMotor;
    private final Servo intakeStopper;
    private final Servo intakeRotator;
    public enum SpinState{
        INTAKE, OUTTAKE, STOP
    }
    public enum StopperState{
        LOCK, UNLOCK
    }
    public enum RotatorState{
        TRANSFER, PICKUP
    }
    private SpinState spinState;
    private RotatorState rotatorState;
    private StopperState stopperState;

    public RunAction intake, outtake, stop, transfer, pickup, lock, unlock;

    public IntakeSubsystem(HardwareMap hardwareMap, SpinState spinState, RotatorState rotatorState, StopperState stopperState) {

        intakeRotator = hardwareMap.get(Servo.class, INTAKE_ROTATOR);

        intakeStopper = hardwareMap.get(Servo.class, INTAKE_STOPPER);

        rotationMotor = new MotorEx(hardwareMap, INTAKE_ROTATION_MOTOR, Motor.GoBILDA.RPM_1150); //TODO check with harish the rpm of this motor
        rotationMotor.setRunMode(Motor.RunMode.RawPower);
        intake = new RunAction(this::intake);
        outtake = new RunAction(this:: outtake);
        stop = new RunAction(this::stop);
        transfer = new RunAction(this::transfer);
        pickup = new RunAction(this::pickup);
        lock = new RunAction(this::lockSample);
        unlock = new RunAction(this::unlockSample);

    }
    //Rotator//
    public void setSpinState(SpinState spinState, boolean changeStateOnly) {
        if (changeStateOnly) {
            this.spinState = spinState;
        } else {
            if (spinState == SpinState.INTAKE) {
                intake();
            } else if (spinState == SpinState.OUTTAKE) {
                outtake();
            } else if (spinState == SpinState.STOP) {
                stop();
            }
        }
    }
    public void intake(double power){
        rotationMotor.set(power);
    }
    public void intake() {
        rotationMotor.set(INTAKE_POWER);
        this.spinState = SpinState.INTAKE;
    }
    public void outtake() {
        rotationMotor.set(-0.7);
        this.spinState = SpinState.OUTTAKE;
    }

    public void stop(){
        rotationMotor.set(0);
        this.spinState = SpinState.STOP;
    }

    //Stopper//
    public void setPivotState(StopperState stopperState) {
        if (stopperState == StopperState.LOCK) {
            intakeStopper.setPosition(INTAKE_STOPPER_DOWN_POSITION);
            this.stopperState = StopperState.LOCK;
        } else if (stopperState == StopperState.UNLOCK) {
            intakeStopper.setPosition(INTAKE_STOPPER_UP_POSITION);
            this.stopperState = StopperState.UNLOCK;
        }
    }
    public void lockSample(){
        setPivotState(StopperState.LOCK);
    }

    public void unlockSample(){
        setPivotState(StopperState.UNLOCK);
    }

    //Pivot//
    public void setRotatorState(RotatorState rotatorState) {
        if (rotatorState == RotatorState.TRANSFER) {
            intakeRotator.setPosition(INTAKE_LARGE_ROTATOR_UP_POSITION);
            this.rotatorState = RotatorState.TRANSFER;
        } else if (rotatorState == RotatorState.PICKUP) {
            intakeRotator.setPosition(INTAKE_LARGE_ROTATOR_DOWN_POSITION);
            this.rotatorState = RotatorState.PICKUP;
        }
    }
    public void pickup(){
        setRotatorState(RotatorState.PICKUP);
    }

    public void transfer(){
        setRotatorState(RotatorState.TRANSFER);
    }





}
