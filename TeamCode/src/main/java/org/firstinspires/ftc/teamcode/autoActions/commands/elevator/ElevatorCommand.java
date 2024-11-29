//package org.firstinspires.ftc.teamcode.autoActions.commands.elevator;
//
//import com.arcrobotics.ftclib.command.CommandBase;
//
//import org.tacobots.centerstage.configs.ElevatorHeights;
//import org.tacobots.centerstage.subsystems.bio.ElevatorSubsystem;
//
//
//public class ElevatorCommand extends CommandBase {
//    protected final ElevatorSubsystem elevatorSubsystem;
//    protected final ElevatorHeights elevatorHeights;
//
//    public ElevatorCommand(ElevatorSubsystem elevatorSubsystem, ElevatorHeights elevatorHeights) {
//        this.elevatorSubsystem = elevatorSubsystem;
//        this.elevatorHeights = elevatorHeights;
//        addRequirements(elevatorSubsystem);
//    }
//
//    @Override
//    public void initialize() {
//        elevatorSubsystem.setTargetPosition(elevatorHeights);
//    }
//
//    @Override
//    public void execute() {
//        elevatorSubsystem.updateElevationPosition(elevatorHeights);
//    }
//
//    @Override
//    public void end(boolean interrupted) {
//        elevatorSubsystem.elevatorAutoStop();
//    }
//
//    @Override
//    public boolean isFinished() {
//        return elevatorSubsystem.isAtTarget();
//    }
//}
