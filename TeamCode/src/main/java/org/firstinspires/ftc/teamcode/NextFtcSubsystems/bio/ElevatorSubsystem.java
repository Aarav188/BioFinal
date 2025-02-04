
package org.firstinspires.ftc.teamcode.NextFtcSubsystems.bio;


import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_LEFT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_RIGHT;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.control.coefficients.PIDCoefficients;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDController;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorGroup;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;

import org.firstinspires.ftc.teamcode.configs.ElevatorHeights;

import java.util.Collections;
import java.util.HashSet;


public class ElevatorSubsystem extends Subsystem {
    // BOILERPLATE
    public static final ElevatorSubsystem INSTANCE = new ElevatorSubsystem();
    private ElevatorSubsystem() { }

    // USER CODE
    public MotorEx leftMotor;
    public MotorEx rightMotor;
    public MotorGroup elevatorMotors;

    public PIDController controller = new PIDController(new PIDCoefficients(0.005, 0.0, 0.0));

    public Command Reset() {
        return new RunToPosition(elevatorMotors, // MOTOR TO MOVE
                ElevatorHeights.RESET.getMotorPosition(), // TARGET POSITION, IN TICKS
                controller, // CONTROLLER TO IMPLEMENT
                this); // IMPLEMENTED SUBSYSTEM
    }

    public Command SpecDrop() {
        return new RunToPosition(elevatorMotors, // MOTOR TO MOVE
                ElevatorHeights.SPECDROP.getMotorPosition(), // TARGET POSITION, IN TICKS
                controller, // CONTROLLER TO IMPLEMENT
                this); // IMPLEMENTED SUBSYSTEM
    }

    public Command HighBucket() {
        return new RunToPosition(elevatorMotors, // MOTOR TO MOVE
                ElevatorHeights.HIGHDROP.getMotorPosition(), // TARGET POSITION, IN TICKS
                controller, // CONTROLLER TO IMPLEMENT
                this); // IMPLEMENTED SUBSYSTEM
    }


    @Override
    public void initialize() {
        leftMotor = new MotorEx(ELEVATOR_MOTOR_LEFT);
        rightMotor = new MotorEx(ELEVATOR_MOTOR_RIGHT);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        elevatorMotors = new MotorGroup(leftMotor,rightMotor);
    }
}