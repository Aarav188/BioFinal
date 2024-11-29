package org.firstinspires.ftc.teamcode.autoActions.commands.extendo;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.bio.ExtendoSubsystem;

public class ExtendoOut implements Action {
    private final ExtendoSubsystem extendoSubsystem;
    public ExtendoOut(ExtendoSubsystem extendoSubsystem) {
        this.extendoSubsystem = extendoSubsystem;
    }

    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        extendoSubsystem.extend();
        return false;
    }
}

