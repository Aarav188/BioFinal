package org.firstinspires.ftc.teamcode.bio;

import static org.firstinspires.ftc.teamcode.configs.RobotConfig.EXTENDO_LEFT_IN_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.EXTENDO_LEFT_MAX_OUT_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.EXTENDO_RIGHT_IN_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.EXTENDO_RIGHT_MAX_OUT_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.LEFT_LINKAGE;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.RIGHT_LINKAGE;
import static org.firstinspires.ftc.teamcode.configs.RobotState.extendoOut;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import java.util.HashMap;
import java.util.Map;


public class ExtendoSubsystem extends TacoSubsystem {

    private final Servo leftAxon;
    private final Servo rightAxon;


    public ExtendoSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        super(hardwareMap, telemetry);
        leftAxon = hardwareMap.get(Servo.class, LEFT_LINKAGE);
        rightAxon = hardwareMap.get(Servo.class, RIGHT_LINKAGE);


        //rightIntakeServo.setDirection(Servo.Direction.REVERSE);
    }
    public void extend() {
        leftAxon.setPosition(EXTENDO_LEFT_MAX_OUT_POSITION);
        rightAxon.setPosition(EXTENDO_RIGHT_MAX_OUT_POSITION);
        extendoOut = true;

    }

    public void reset(){
        leftAxon.setPosition(EXTENDO_LEFT_IN_POSITION);
        rightAxon.setPosition(EXTENDO_RIGHT_IN_POSITION);
        extendoOut = false;
    }
    public double position(){
        return leftAxon.getPosition();
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
