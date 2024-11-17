package org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator;


import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.bio.OuttakeArmSubsystem;


public class ArmBucketDropPosMid implements Action {
    private final OuttakeArmSubsystem outtakeArmSubsystem;


    public ArmBucketDropPosMid(OuttakeArmSubsystem outtakeArmSubsystem) {
        this.outtakeArmSubsystem = outtakeArmSubsystem;

    }

    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        outtakeArmSubsystem.bucketDrop();
        return false;
    }
}