package org.firstinspires.ftc.teamcode.autoActions.commands.backIntake;


import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.bio.BackIntakeSubsystem;

public class PickUpSpeciman implements Action {
    private final BackIntakeSubsystem backIntakeSubsystem;

    public PickUpSpeciman(BackIntakeSubsystem backIntakeSubsystem) {
        this.backIntakeSubsystem = backIntakeSubsystem;
    }


    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        backIntakeSubsystem.pickUp();
        return false;
    }
}
