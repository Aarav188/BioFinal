package org.firstinspires.ftc.teamcode.autoActions.commands.elevator;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.bio.ElevatorSubsystem;
import org.firstinspires.ftc.teamcode.configs.ElevatorHeights;


public class AutoElevatorUpLowCommand implements Action {
    protected final ElevatorSubsystem elevatorSubsystem;
    ElapsedTime timer = new ElapsedTime();

    public AutoElevatorUpLowCommand(ElevatorSubsystem elevatorSubsystem) {
        this.elevatorSubsystem = elevatorSubsystem;
    }


    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        if(elevatorSubsystem.targetPosition() != ElevatorHeights.SPEC)
            elevatorSubsystem.setTargetPosition(ElevatorHeights.SPEC);
        elevatorSubsystem.updateElevationPosition();
        return !elevatorSubsystem.isAtTarget();
    }
}
