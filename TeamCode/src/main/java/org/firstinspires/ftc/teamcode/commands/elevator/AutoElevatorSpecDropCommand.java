package org.firstinspires.ftc.teamcode.commands.elevator;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.bio.ElevatorSubsystem;
import org.firstinspires.ftc.teamcode.configs.ElevatorHeights;


public class AutoElevatorSpecDropCommand extends CommandBase {
    protected final ElevatorSubsystem elevatorSubsystem;
    ElapsedTime timer = new ElapsedTime();

    public AutoElevatorSpecDropCommand(ElevatorSubsystem elevatorSubsystem) {
        this.elevatorSubsystem = elevatorSubsystem;

        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {
        elevatorSubsystem.setTargetPosition(ElevatorHeights.SPECDROP);
    }

    @Override
    public void execute() {
        timer.reset();
        elevatorSubsystem.updateElevationIncrementPosition();
    }
    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.maintainPosition();
    }

    public boolean isFinished() {
        return elevatorSubsystem.isAtTarget();
    }


}
