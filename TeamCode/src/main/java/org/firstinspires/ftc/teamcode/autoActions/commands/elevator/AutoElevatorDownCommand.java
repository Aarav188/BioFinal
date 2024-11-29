package org.firstinspires.ftc.teamcode.autoActions.commands.elevator;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.bio.ElevatorSubsystem;
import org.firstinspires.ftc.teamcode.configs.ElevatorHeights;


public class AutoElevatorDownCommand implements Action {
    protected final ElevatorSubsystem elevatorSubsystem;
    ElapsedTime timer = new ElapsedTime();

    public AutoElevatorDownCommand(ElevatorSubsystem elevatorSubsystem) {
        this.elevatorSubsystem = elevatorSubsystem;
    }


    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        if(elevatorSubsystem.targetPosition() != ElevatorHeights.RESET)
            elevatorSubsystem.setTargetPosition(ElevatorHeights.RESET);
        elevatorSubsystem.updateElevatorDownPostion();
        return !elevatorSubsystem.isAtTarget();
    }
}
