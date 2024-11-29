package org.firstinspires.ftc.teamcode.commands.outtakeRotator;


import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.bio.OuttakeClawSubsystem;


public class ClawOpen extends CommandBase {
    private final OuttakeClawSubsystem outtakeClawSubsystem;


    public ClawOpen(OuttakeClawSubsystem outtakeClawSubsystem) {
        this.outtakeClawSubsystem = outtakeClawSubsystem;

        addRequirements(outtakeClawSubsystem);
    }

    @Override
    public void execute() {
        outtakeClawSubsystem.openClaw();
    }


}