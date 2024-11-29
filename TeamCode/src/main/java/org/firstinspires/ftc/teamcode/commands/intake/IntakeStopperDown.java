package org.firstinspires.ftc.teamcode.commands.intake;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.bio.IntakeSubsystem;


public class IntakeStopperDown extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;

    public IntakeStopperDown(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute() {
        intakeSubsystem.lockSample();
    }
}