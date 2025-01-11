package org.firstinspires.ftc.teamcode.bio;


import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_LARGE_ROTATOR_DOWN_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_LARGE_ROTATOR_UP_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_POWER;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_ROTATION_MOTOR;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_ROTATOR;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_STOPPER;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_STOPPER_DOWN_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_STOPPER_UP_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotState.intakeDepo;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class IntakeSubsystem extends TacoSubsystem {
    private final MotorEx rotationMotor;
    private final Servo intakeStopper;
    private final Servo intakeRotator;

    public IntakeSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        super(hardwareMap, telemetry);

        intakeRotator = hardwareMap.get(Servo.class, INTAKE_ROTATOR);

        intakeStopper = hardwareMap.get(Servo.class, INTAKE_STOPPER);

        rotationMotor = new MotorEx(hardwareMap, INTAKE_ROTATION_MOTOR, Motor.GoBILDA.RPM_1150); //TODO check with harish the rpm of this motor
        rotationMotor.setRunMode(Motor.RunMode.RawPower);


    }
    public void intake(double power){
        rotationMotor.set(power);
    }
    public void intake() {
        rotationMotor.set(INTAKE_POWER);
    } //change once we figure out which side is positive

    public void lockSample(){
        intakeStopper.setPosition(INTAKE_STOPPER_DOWN_POSITION);
    }

    public void unlockSample(){
       // if(beamServo.getPosition()!= INTAKE_STOPPER_UP_POSITION) {
            intakeStopper.setPosition(INTAKE_STOPPER_UP_POSITION);
//        }
//        else{
//            lockSample();
//        }
    }

    public void intakePosition(){
        if(intakeRotator.getPosition() != INTAKE_LARGE_ROTATOR_DOWN_POSITION) {
            intakeRotator.setPosition(INTAKE_LARGE_ROTATOR_DOWN_POSITION);
            intakeDepo = false;
        }
        else{
            depoPosition();
        }
    }

    public void depoPosition(){
        intakeRotator.setPosition(INTAKE_LARGE_ROTATOR_UP_POSITION);
        intakeDepo = true;
    }



    public void outtake() {
        rotationMotor.set(1);
    }

    public void stop(){
        rotationMotor.set(0);
    }


    //TODO change to teleop stuff once we figure that out
    public void intake(double x, double y){
        if(y>0.9 && x<0.1 && x > -0.1){
            rotationMotor.set(-INTAKE_POWER); // up
        } else if (y<-0.9 && x<0.1 && x > -0.1){
            rotationMotor.set(-INTAKE_POWER); // down
        } else {
            rotationMotor.set(0);
        }
    }

}
