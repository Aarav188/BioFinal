package org.firstinspires.ftc.teamcode.autoActions.commands.elevator;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.bio.ElevatorSubsystem;
import org.firstinspires.ftc.teamcode.configs.ElevatorHeights;


public class AutoElevatorDeliverCommand implements Action {
    protected final ElevatorSubsystem elevatorSubsystem;
    double timer;

    public AutoElevatorDeliverCommand(ElevatorSubsystem elevatorSubsystem) {
        this.elevatorSubsystem = elevatorSubsystem;
    }


    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {

        if(elevatorSubsystem.targetPosition() != ElevatorHeights.SPECDROP){
            elevatorSubsystem.setTargetPosition(ElevatorHeights.SPECDROP);
            timer = System.currentTimeMillis();
        }
        elevatorSubsystem.updateElevationPosition();


        return System.currentTimeMillis() - timer < 5000 && !elevatorSubsystem.isAtTarget();
    }
}
