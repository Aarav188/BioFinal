
package org.firstinspires.ftc.teamcode.bio;




import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_KP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_LEFT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_POWER;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_RIGHT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_TOLERANCE;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.HANG;
import static org.firstinspires.ftc.teamcode.configs.RobotState.targetHeight;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.configs.ElevatorHeights;

import java.util.HashMap;
import java.util.Map;


public class HangSubsystem extends TacoSubsystem {

    private final MotorEx hangMotor;



    public HangSubsystem(HardwareMap hardwareMap, Telemetry dashboardTelemetry) {
        super(hardwareMap, dashboardTelemetry);
        hangMotor = new MotorEx(hardwareMap, HANG, Motor.GoBILDA.RPM_435);


        configureMotors();
        resetEncoder();

    }




    public void setElevationMotorTargetPosition(int position) {
        hangMotor.setTargetPosition(position);
    }


    public void updateElevationPosition(){
        hangMotor.set(1);
    }


    /**
     * decrease that 20 if pid is good, increase if bad, essentially the margin of error
     */
    public boolean isAtTarget() {
        if(Math.abs(getPosition() - hangMotor.getCurrentPosition()) < 50){
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
        return hangMotor.getCurrentPosition();
    }

    public void stop() {
        hangMotor.set(0);
    }


    public void resetEncoder() {
        hangMotor.resetEncoder();
    }




    public void setTargetPositionMaintain(ElevatorHeights elevatorHeights){
        setElevationMotorTargetPosition(elevatorHeights.getMotorPosition());
    }

    public void goToRawPosition(int target, double power) {
        hangMotor.setTargetPosition(target);
        while (!hangMotor.atTargetPosition()) {
            if (hangMotor.getCurrentPosition() > target) {
                break;
            }
            hangMotor.set(power);
        }
        hangMotor.set(0);
    }


    public void configureMotors() {
        hangMotor.setRunMode(Motor.RunMode.PositionControl);
        hangMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        hangMotor.setPositionCoefficient(ELEVATOR_MOTOR_KP);
        hangMotor.setPositionTolerance(ELEVATOR_MOTOR_TOLERANCE);
    }



}
