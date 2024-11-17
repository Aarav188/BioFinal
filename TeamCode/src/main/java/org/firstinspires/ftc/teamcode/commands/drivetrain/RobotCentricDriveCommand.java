package org.firstinspires.ftc.teamcode.commands.drivetrain;


import static org.firstinspires.ftc.teamcode.configs.RobotConfig.DT_SQUARE_INPUT;

import com.arcrobotics.ftclib.command.CommandBase;


import org.firstinspires.ftc.teamcode.bio.MecanumDriveSubsystem;

import java.util.function.DoubleSupplier;

public class RobotCentricDriveCommand extends CommandBase {

    private final MecanumDriveSubsystem mecanumDriveSubsystem;
    private final DoubleSupplier strafe;
    private final DoubleSupplier forward;
    private final DoubleSupplier turn;
    private final double multiplier;

    public RobotCentricDriveCommand(MecanumDriveSubsystem mecanumDriveSubsystem, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn) {

        this(mecanumDriveSubsystem, strafe, forward, turn, 1.0);

    }

    public RobotCentricDriveCommand(MecanumDriveSubsystem mecanumDriveSubsystem, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn, double multiplier) {
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;

        this.strafe = strafe;
        this.forward = forward;
        this.turn = turn;
        this.multiplier = multiplier;
        addRequirements(mecanumDriveSubsystem);
    }


    @Override
    public void execute() {
        mecanumDriveSubsystem.robotCentricDrive(strafe.getAsDouble() * multiplier, forward.getAsDouble() * multiplier, turn.getAsDouble() * multiplier, DT_SQUARE_INPUT);
    }

}
