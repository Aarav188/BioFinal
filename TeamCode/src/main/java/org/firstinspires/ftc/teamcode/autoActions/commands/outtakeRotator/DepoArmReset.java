package org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator;


import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.bio.OuttakeArmSubsystem;
import org.firstinspires.ftc.teamcode.bio.OuttakeClawSubsystem;


public class DepoArmReset implements Action {
    private final OuttakeArmSubsystem outtakeArmSubsystem;


    public DepoArmReset(OuttakeArmSubsystem outtakeArmSubsystem) {
        this.outtakeArmSubsystem = outtakeArmSubsystem;

    }

    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        outtakeArmSubsystem.resetArm();
        return false;
    }
}