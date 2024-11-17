package org.firstinspires.ftc.teamcode.commands.elevator;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.bio.ElevatorSubsystem;
import org.firstinspires.ftc.teamcode.configs.ElevatorHeights;


public class AutoElevatorDownCommand extends CommandBase {
    protected final ElevatorSubsystem elevatorSubsystem;
    ElapsedTime timer = new ElapsedTime();

    public AutoElevatorDownCommand(ElevatorSubsystem elevatorSubsystem) {
        this.elevatorSubsystem = elevatorSubsystem;

        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {


        elevatorSubsystem.setTargetPosition(ElevatorHeights.RESET);
    }

    @Override
    public void execute() {
        timer.reset();
        elevatorSubsystem.updateElevatorDownPostion();
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.elevatorAutoStop();
    }
    public boolean isFinished() {
        if (timer.milliseconds() > 2000) {
            return true;
        }
        return elevatorSubsystem.isAtTarget();
    }


}
