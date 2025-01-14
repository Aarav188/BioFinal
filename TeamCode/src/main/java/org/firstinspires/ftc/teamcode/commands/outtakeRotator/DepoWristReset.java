package org.firstinspires.ftc.teamcode.commands.outtakeRotator;


import com.arcrobotics.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.bio.OuttakeArmSubsystem;
import org.firstinspires.ftc.teamcode.bio.OuttakeClawSubsystem;


public class DepoWristReset extends InstantCommand {
    private final OuttakeClawSubsystem outtakeClawSubsystem;


    public DepoWristReset(OuttakeClawSubsystem outtakeClawSubsystem) {
        this.outtakeClawSubsystem = outtakeClawSubsystem;

        addRequirements(outtakeClawSubsystem);
    }

    @Override
    public void execute() {
        outtakeClawSubsystem.setWristReset();
        outtakeClawSubsystem.closeClaw();

    }


}