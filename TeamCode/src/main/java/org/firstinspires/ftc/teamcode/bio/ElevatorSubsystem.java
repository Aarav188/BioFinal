
package org.firstinspires.ftc.teamcode.bio;




import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_KP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_LEFT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_POWER;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_RIGHT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_TOLERANCE;
import static org.firstinspires.ftc.teamcode.configs.RobotState.targetHeight;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.configs.ElevatorHeights;

import java.util.HashMap;
import java.util.Map;


//TODO No clue if this class works at all so test it out asap once all encoder values have been set
public class ElevatorSubsystem extends TacoSubsystem {

    private final MotorEx leftElevationMotor;
    private final MotorEx rightElevationMotor;
    private final MotorGroup elevationMotors;

    private ElevatorHeights elevatorHeights;


    public ElevatorSubsystem(HardwareMap hardwareMap, Telemetry dashboardTelemetry) {
        super(hardwareMap, dashboardTelemetry);
        leftElevationMotor = new MotorEx(hardwareMap, ELEVATOR_MOTOR_LEFT, Motor.GoBILDA.RPM_1150); //TODO Check the rpm
        rightElevationMotor = new MotorEx(hardwareMap, ELEVATOR_MOTOR_RIGHT, Motor.GoBILDA.RPM_1150);
        rightElevationMotor.setInverted(true); //TODO check which one is inverted, if any
        elevationMotors = new MotorGroup(rightElevationMotor, leftElevationMotor);
        elevatorHeights = ElevatorHeights.RESET;


        configureMotors();
        resetEncoder();

    }


    public void setSlidesPower(){
        elevationMotors.set(0.0);
    }


    public void setElevationMotorTargetPosition(int position) {
        elevationMotors.setTargetPosition(position);
    }

    public int getCurrentPosition(){
        return elevatorHeights.getMotorPosition();
    } //dunno if this works or not


    public void updateElevationPosition(ElevatorHeights elevatorHeights) {
        if(elevatorHeights == ElevatorHeights.RESET){
            elevationMotors.set(0.2);
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
        elevationMotors.set(0.4);
    }

    /**
     * decrease that 20 if pid is good, increase if bad, essentially the margin of error
     */
    public boolean isAtTarget() {
        if(Math.abs(getPosition() - elevatorHeights.getMotorPosition()) < 50){
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
        elevationMotors.set(0.2);
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
