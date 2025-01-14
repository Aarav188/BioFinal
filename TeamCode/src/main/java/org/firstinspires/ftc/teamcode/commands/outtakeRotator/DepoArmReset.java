package org.firstinspires.ftc.teamcode.commands.outtakeRotator;


import com.arcrobotics.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.bio.OuttakeArmSubsystem;
import org.firstinspires.ftc.teamcode.bio.OuttakeClawSubsystem;


public class DepoArmReset extends InstantCommand {
    private final OuttakeArmSubsystem outtakeArmSubsystem;


    public DepoArmReset(OuttakeArmSubsystem outtakeArmSubsystem) {
        this.outtakeArmSubsystem = outtakeArmSubsystem;

        addRequirements(outtakeArmSubsystem);
    }

    @Override
    public void execute() {
        outtakeArmSubsystem.resetArm();

    }


}