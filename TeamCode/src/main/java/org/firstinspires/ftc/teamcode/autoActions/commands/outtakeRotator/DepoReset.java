package org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator;


import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.bio.OuttakeArmSubsystem;
import org.firstinspires.ftc.teamcode.bio.OuttakeClawSubsystem;


public class DepoReset implements Action {
    private final OuttakeArmSubsystem outtakeArmSubsystem;
    private final OuttakeClawSubsystem outtakeClawSubsystem;


    public DepoReset(OuttakeArmSubsystem outtakeArmSubsystem, OuttakeClawSubsystem outtakeClawSubsystem) {
        this.outtakeArmSubsystem = outtakeArmSubsystem;
        this.outtakeClawSubsystem = outtakeClawSubsystem;

    }

    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        outtakeArmSubsystem.resetArm();
        outtakeClawSubsystem.setWristReset();
        outtakeClawSubsystem.openClaw();
        return false;
    }
}