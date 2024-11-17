package org.firstinspires.ftc.teamcode.commands.outtakeRotator;


import com.arcrobotics.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.bio.OuttakeArmSubsystem;
import org.firstinspires.ftc.teamcode.bio.OuttakeClawSubsystem;


public class DepoReset extends InstantCommand {
    private final OuttakeClawSubsystem outtakeClawSubsystem;
    private final OuttakeArmSubsystem outtakeArmSubsystem;


    public DepoReset(OuttakeArmSubsystem outtakeArmSubsystem, OuttakeClawSubsystem outtakeClawSubsystem) {
        this.outtakeArmSubsystem = outtakeArmSubsystem;
        this.outtakeClawSubsystem = outtakeClawSubsystem;

        addRequirements(outtakeArmSubsystem, outtakeClawSubsystem);
    }

    @Override
    public void execute() {
        outtakeArmSubsystem.resetArm();
        outtakeClawSubsystem.setWristReset();
        outtakeClawSubsystem.openClaw();

    }


}