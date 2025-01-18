package org.firstinspires.ftc.teamcode.commands.outtakeRotator;


import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.bio.OuttakeArmSubsystem;
import org.firstinspires.ftc.teamcode.bio.OuttakeClawSubsystem;


public class ArmDropHighBucket extends CommandBase {
    private final OuttakeArmSubsystem outtakeArmSubsystem;
    private final OuttakeClawSubsystem outtakeClawSubsystem;


    public ArmDropHighBucket(OuttakeArmSubsystem outtakeArmSubsystem, OuttakeClawSubsystem outtakeClawSubsystem) {
        this.outtakeArmSubsystem = outtakeArmSubsystem;
        this.outtakeClawSubsystem =  outtakeClawSubsystem;

        addRequirements(outtakeArmSubsystem);
        addRequirements(outtakeClawSubsystem);

    }

    @Override
    public void execute() {
        outtakeArmSubsystem.bucketDropHigh();
        outtakeClawSubsystem.bucketDrop();

    }


}