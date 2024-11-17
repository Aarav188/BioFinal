package org.firstinspires.ftc.teamcode.autoActions.commands.intake;


import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.bio.IntakeSubsystem;


public class IntakeStopperUp implements Action {
    private final IntakeSubsystem intakeSubsystem;

    public IntakeStopperUp(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
    }

    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        intakeSubsystem.unlockSample();
        return false;
    }
}