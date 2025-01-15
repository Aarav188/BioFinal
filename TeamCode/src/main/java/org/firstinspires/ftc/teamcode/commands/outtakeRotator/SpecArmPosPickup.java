package org.firstinspires.ftc.teamcode.commands.outtakeRotator;


import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.bio.OuttakeArmSubsystem;


public class SpecArmPosPickup extends CommandBase {
    private final OuttakeArmSubsystem outtakeArmSubsystem;


    public SpecArmPosPickup(OuttakeArmSubsystem outtakeArmSubsystem) {
        this.outtakeArmSubsystem = outtakeArmSubsystem;

        addRequirements(outtakeArmSubsystem);
    }

    @Override
    public void execute() {
        outtakeArmSubsystem.specimanPickUp();

    }


}