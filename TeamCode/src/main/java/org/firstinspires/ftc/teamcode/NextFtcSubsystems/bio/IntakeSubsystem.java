package org.firstinspires.ftc.teamcode.NextFtcSubsystems.bio;


import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_LEFT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_RIGHT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.INTAKE_ROTATION_MOTOR;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.control.coefficients.PIDCoefficients;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDController;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorGroup;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToVelocity;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.SetPower;

import org.firstinspires.ftc.teamcode.configs.ElevatorHeights;

import java.util.Collections;
import java.util.HashSet;


public class IntakeSubsystem extends Subsystem {
    // BOILERPLATE
    public static final IntakeSubsystem INSTANCE = new IntakeSubsystem();
    private IntakeSubsystem() { }

    // USER CODE
    public MotorEx intake;


    public Command intake() {
        return new SetPower(intake, 1, this);
    }

    public Command outtake() {
        return new SetPower(intake, -1, this);
    }
    public Command stop() {
        return new SetPower(intake, 0, this);
    }

    @Override
    public void initialize() {
        intake = new MotorEx(INTAKE_ROTATION_MOTOR);
    }
}
