package org.firstinspires.ftc.teamcode.commands.intake;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.bio.IntakeSubsystem;


public class IntakeServoDeposit extends CommandBase {
    private final IntakeSubsystem intakeServoSubsystem;
    public IntakeServoDeposit(IntakeSubsystem intakeServoSubsystem) {
        this.intakeServoSubsystem = intakeServoSubsystem;
        addRequirements(intakeServoSubsystem);
    }

    @Override
    public void execute(){intakeServoSubsystem.depoPosition();}
}

