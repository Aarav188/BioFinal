package org.firstinspires.ftc.teamcode.commands.intake;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.bio.IntakeSubsystem;


public class IntakeStopperUp extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;

    public IntakeStopperUp(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute() {
        intakeSubsystem.unlockSample();
    }
}