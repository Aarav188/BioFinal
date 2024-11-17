package org.firstinspires.ftc.teamcode.commands.backIntake;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.bio.BackIntakeSubsystem;


public class SpecimanToReservoir extends InstantCommand {
    private final BackIntakeSubsystem backIntakeSubsystem;

    public SpecimanToReservoir(BackIntakeSubsystem backIntakeSubsystem) {
        this.backIntakeSubsystem = backIntakeSubsystem;

        addRequirements(backIntakeSubsystem);
    }


    @Override
    public void execute() {
        backIntakeSubsystem.rotateToReservoir();
    }
}