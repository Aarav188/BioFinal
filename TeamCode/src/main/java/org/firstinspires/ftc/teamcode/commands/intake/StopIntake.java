package org.firstinspires.ftc.teamcode.commands.intake;


import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.bio.IntakeSubsystem;


public class StopIntake extends InstantCommand {
    private final IntakeSubsystem intakeSubsystem;

    public StopIntake(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;


        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute() {
        intakeSubsystem.stop();
    }
}