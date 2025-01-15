package org.firstinspires.ftc.teamcode.commands.outtakeRotator;


import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.bio.OuttakeArmSubsystem;
import org.firstinspires.ftc.teamcode.bio.OuttakeClawSubsystem;

public class SpecDropOffFromBack extends CommandBase {
    private final OuttakeClawSubsystem outtakeClawSubsystem;
    private final OuttakeArmSubsystem outtakeArmSubsystem;


    public SpecDropOffFromBack(OuttakeArmSubsystem outtakeArmSubsystem, OuttakeClawSubsystem outtakeClawSubsystem) {
        this.outtakeArmSubsystem = outtakeArmSubsystem;
        this.outtakeClawSubsystem = outtakeClawSubsystem;

        addRequirements(outtakeArmSubsystem, outtakeClawSubsystem);
    }

    @Override
    public void execute() {
        outtakeArmSubsystem.specimanDropBack();
        outtakeClawSubsystem.setWristSpecimanBack();

    }


}