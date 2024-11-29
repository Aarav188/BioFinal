package org.firstinspires.ftc.teamcode.commands.extendo;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.bio.ExtendoSubsystem;


public class ExtendoReset extends CommandBase {
    private final ExtendoSubsystem extendoSubsystem;
    public ExtendoReset(ExtendoSubsystem extendoSubsystem) {
        this.extendoSubsystem = extendoSubsystem;
        addRequirements(extendoSubsystem);
    }

    @Override
    public void execute(){extendoSubsystem.reset();}
}

