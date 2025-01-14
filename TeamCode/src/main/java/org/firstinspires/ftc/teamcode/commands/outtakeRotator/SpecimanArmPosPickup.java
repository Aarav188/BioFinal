package org.firstinspires.ftc.teamcode.commands.outtakeRotator;


import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.bio.OuttakeArmSubsystem;
import org.firstinspires.ftc.teamcode.bio.OuttakeClawSubsystem;


public class SpecimanArmPosPickup extends CommandBase {
    private final OuttakeArmSubsystem outtakeArmSubsystem;


    public SpecimanArmPosPickup(OuttakeArmSubsystem outtakeArmSubsystem) {
        this.outtakeArmSubsystem = outtakeArmSubsystem;

        addRequirements(outtakeArmSubsystem);
    }

    @Override
    public void execute() {
        outtakeArmSubsystem.specimanPickUp();

    }


}