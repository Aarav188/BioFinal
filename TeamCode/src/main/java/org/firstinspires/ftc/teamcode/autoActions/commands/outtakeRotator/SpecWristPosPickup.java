package org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator;


import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.bio.OuttakeArmSubsystem;
import org.firstinspires.ftc.teamcode.bio.OuttakeClawSubsystem;


public class SpecWristPosPickup implements Action {
    private final OuttakeClawSubsystem outtakeClawSubsystem;


    public SpecWristPosPickup(OuttakeClawSubsystem outtakeClawSubsystem) {
        this.outtakeClawSubsystem = outtakeClawSubsystem;

    }

    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        outtakeClawSubsystem.setWristReset();
        outtakeClawSubsystem.openClaw();
        return false;
    }
}