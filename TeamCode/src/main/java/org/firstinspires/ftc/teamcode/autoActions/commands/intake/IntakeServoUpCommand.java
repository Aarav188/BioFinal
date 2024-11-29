package org.firstinspires.ftc.teamcode.autoActions.commands.intake;//package org.tacobots.centerstage.commands.intakeServo;
//
//
//import com.arcrobotics.ftclib.command.InstantCommand;
//
//import org.tacobots.centerstage.subsystems.bio.IntakeServoSubsystem;
//
//
//public class IntakeServoUpCommand extends InstantCommand {
//    private final IntakeServoSubsystem intakeServoSubsystem;
//
//    public IntakeServoUpCommand(IntakeServoSubsystem intakeServoSubsystem) {
//        this.intakeServoSubsystem = intakeServoSubsystem;
//
//
//        addRequirements(intakeServoSubsystem);
//    }
//
//    @Override
//    public void execute() {
//        intakeServoSubsystem.intakeUp();
//    }
//}