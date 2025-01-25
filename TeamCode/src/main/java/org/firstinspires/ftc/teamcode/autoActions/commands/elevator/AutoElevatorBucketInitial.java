package org.firstinspires.ftc.teamcode.autoActions.commands.elevator;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.bio.ElevatorSubsystem;
import org.firstinspires.ftc.teamcode.configs.ElevatorHeights;

public class AutoElevatorBucketInitial implements Action {
    protected final ElevatorSubsystem elevatorSubsystem;
    double timer;

    public AutoElevatorBucketInitial(ElevatorSubsystem elevatorSubsystem) {
        this.elevatorSubsystem = elevatorSubsystem;
        this.timer = System.currentTimeMillis();
    }


    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        if(elevatorSubsystem.targetPosition() != ElevatorHeights.HIGHDROP){
            elevatorSubsystem.setTargetPosition(ElevatorHeights.HIGHDROP);
            timer = System.currentTimeMillis();
        }
        elevatorSubsystem.updateElevationPosition();


        return System.currentTimeMillis() - timer < 3000 && !elevatorSubsystem.isAtTarget();
    }
}
