package org.firstinspires.ftc.teamcode.autoActions.commands.elevator;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.bio.ElevatorSubsystem;
import org.firstinspires.ftc.teamcode.configs.ElevatorHeights;


public class ElevatorIncrementCommand implements Action {
    protected final ElevatorSubsystem elevatorSubsystem;
    double timer;
    boolean firstTime = true;
    protected final int increment;
    public ElevatorIncrementCommand(ElevatorSubsystem elevatorSubsystem, int increment) {
        this.elevatorSubsystem = elevatorSubsystem;
        this.increment = increment;
    }


    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        if(firstTime) {
            elevatorSubsystem.elevatorIncrement(-increment);
        }
        elevatorSubsystem.updateElevationIncrementPosition();
        return !elevatorSubsystem.isAtIncrementTarget(elevatorSubsystem.getPosition() - increment) || System.currentTimeMillis() - timer < 2000;
    }
}
