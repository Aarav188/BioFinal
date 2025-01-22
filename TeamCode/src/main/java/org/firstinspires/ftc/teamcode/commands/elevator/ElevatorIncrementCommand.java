package org.firstinspires.ftc.teamcode.commands.elevator;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.bio.ElevatorSubsystem;


public class ElevatorIncrementCommand extends CommandBase{

    protected final ElevatorSubsystem elevatorSubsystem;
    protected final int increment;

    public ElevatorIncrementCommand(ElevatorSubsystem elevatorSubsystem, int increment) {
        this.elevatorSubsystem = elevatorSubsystem;
        this.increment = increment;
        addRequirements(elevatorSubsystem);
    }



    @Override
    public void initialize() {
            elevatorSubsystem.elevatorIncrement(-increment);

    }

    @Override
    public void execute() {
        elevatorSubsystem.updateElevationIncrementPosition();

    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.elevatorAutoStop();
    }

    @Override
    public boolean isFinished() {
        return elevatorSubsystem.isAtIncrementTarget(elevatorSubsystem.getPosition() - increment);
    }
}