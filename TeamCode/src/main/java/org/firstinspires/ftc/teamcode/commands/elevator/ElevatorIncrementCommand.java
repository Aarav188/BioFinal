package org.firstinspires.ftc.teamcode.commands.elevator;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.bio.ElevatorSubsystem;


public class ElevatorIncrementCommand extends CommandBase{

    protected final ElevatorSubsystem elevatorSubsystem;

    public ElevatorIncrementCommand(ElevatorSubsystem elevatorSubsystem) {
        this.elevatorSubsystem = elevatorSubsystem;
        addRequirements(elevatorSubsystem);
    }



    @Override
    public void initialize() {
        elevatorSubsystem.specDropOff();
    }

    @Override
    public void execute() {
        elevatorSubsystem.updateElevationPosition();

    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.elevatorAutoStop();
    }

    @Override
    public boolean isFinished() {
        return elevatorSubsystem.isAtIncrementTarget(elevatorSubsystem.getPosition() - 100);
    }
}