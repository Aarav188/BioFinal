package org.firstinspires.ftc.teamcode.bio;


import static org.firstinspires.ftc.teamcode.configs.RobotConfig.HANG;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.HashMap;
import java.util.Map;


public class HangSubsystem extends TacoSubsystem {

    private final MotorEx hangMotor;
    private int target = 0;


    public HangSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        super(hardwareMap, telemetry);
        hangMotor = new MotorEx(hardwareMap, HANG, Motor.GoBILDA.RPM_312);
        hangMotor.resetEncoder();

        //rightIntakeServo.setDirection(Servo.Direction.REVERSE);
    }
    public void hang(int position) {
        hangMotor.setTargetPosition(position);
        target = position;
    }

    public void reset(int position){
        hangMotor.setTargetPosition(position);
        target = position;
        updateHangPostion();
    }

    public void updateHangPostion(){
        hangMotor.set(-1);
    }

    public void killPower(){
        hangMotor.set(0);
    }

    public void resetHangPostion(){
        hangMotor.set(1);
    }


    public boolean isAtTarget() {
        if(Math.abs(getPosition() - target )< 10){
            return true;
        }
        return false;
    }

    public int getPosition() {
        return hangMotor.getCurrentPosition();
    }

    /**
     * this is a teleop method that sets position based on joystick location
     * change this to work later
     */
//    public void setPosition(double x, double y){
//        if(y>0.9 && x<0.1 && x > -0.1){
//            intakeLeftServo.setPosition(LEFT_INTAKE_SERVO_STACK_TOP_POSITION); // up
//            intakeRightServo.setPosition(RIGHT_INTAKE_SERVO_STACK_TOP_POSITION);
//        } else if (y<-0.9 && x<0.1 && x > -0.1) {
//            intakeLeftServo.setPosition(LEFT_INTAKE_SERVO_STACK_MIDDLE_POSITION); // down
//            intakeRightServo.setPosition(RIGHT_INTAKE_SERVO_STACK_MIDDLE_POSITION);
//        } else if(x>0.9 && y<0.1 && y > -0.1){
//            intakeLeftServo.setPosition(LEFT_INTAKE_SERVO_STACK_FOURTH_POSITION);
//            intakeRightServo.setPosition(RIGHT_INTAKE_SERVO_STACK_FOURTH_POSITION);
//        } else if (x<-0.9 && y<0.1 && y > -0.1){
//            intakeLeftServo.setPosition(LEFT_INTAKE_SERVO_STACK_SECOND_POSITION);
//            intakeRightServo.setPosition(RIGHT_INTAKE_SERVO_STACK_SECOND_POSITION);
//        }
//
//    }


}