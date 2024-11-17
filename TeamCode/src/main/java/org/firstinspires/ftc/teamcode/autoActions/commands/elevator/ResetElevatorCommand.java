//package org.firstinspires.ftc.teamcode.autoActions.commands.elevator;
//
//import com.arcrobotics.ftclib.command.CommandBase;
//
//import org.tacobots.centerstage.subsystems.bio.ElevatorSubsystem;
//
//public class ResetElevatorCommand extends CommandBase {
//
//    private final ElevatorSubsystem elevatorSubsystem;
//
//    public ResetElevatorCommand(ElevatorSubsystem elevatorSubsystem) {
//        this.elevatorSubsystem = elevatorSubsystem;
//        addRequirements(elevatorSubsystem, elevatorSubsystem);
//    }
//
//    @Override
//    public void execute() {
//        elevatorSubsystem.resetEncoder();
//        elevatorSubsystem.stop();
//
//    }
//
//}
