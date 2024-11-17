package org.firstinspires.ftc.teamcode.autoActions.commands.intake;


import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.bio.IntakeSubsystem;


public class Outtake implements Action {
    private final IntakeSubsystem intakeSubsystem;

    public Outtake(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
    }

    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        intakeSubsystem.outtake();
        return false;
    }
}