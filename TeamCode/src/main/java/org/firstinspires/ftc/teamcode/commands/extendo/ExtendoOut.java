package org.firstinspires.ftc.teamcode.commands.extendo;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.bio.ExtendoSubsystem;


public class ExtendoOut extends CommandBase {
    private final ExtendoSubsystem extendoSubsystem;
    public ExtendoOut(ExtendoSubsystem extendoSubsystem) {
        this.extendoSubsystem = extendoSubsystem;
        addRequirements(extendoSubsystem);
    }

    @Override
    public void execute(){extendoSubsystem.extend();}
}

