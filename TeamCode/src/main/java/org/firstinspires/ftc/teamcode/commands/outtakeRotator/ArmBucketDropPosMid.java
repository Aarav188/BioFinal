package org.firstinspires.ftc.teamcode.commands.outtakeRotator;


import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.bio.OuttakeArmSubsystem;


public class ArmBucketDropPosMid extends CommandBase {
    private final OuttakeArmSubsystem outtakeArmSubsystem;


    public ArmBucketDropPosMid(OuttakeArmSubsystem outtakeArmSubsystem) {
        this.outtakeArmSubsystem = outtakeArmSubsystem;

        addRequirements(outtakeArmSubsystem);
    }

    @Override
    public void execute() {
        outtakeArmSubsystem.bucketDrop();

    }


}