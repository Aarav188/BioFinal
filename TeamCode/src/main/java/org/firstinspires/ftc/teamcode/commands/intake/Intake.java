package org.firstinspires.ftc.teamcode.commands.intake;


import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.bio.IntakeSubsystem;


public class Intake extends InstantCommand {
    private final IntakeSubsystem intakeSubsystem;

    public Intake(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;


        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute() {
        intakeSubsystem.intake(-1);
    }
}