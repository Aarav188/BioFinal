package org.firstinspires.ftc.teamcode.commands.elevator;



import static org.firstinspires.ftc.teamcode.configs.RobotConfig.HANGPOS;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.bio.HangSubsystem;


public class HangIncrementCommand extends CommandBase {
    protected final HangSubsystem hangSubsystem;
    protected final int increment;

    public HangIncrementCommand(HangSubsystem hangSubsystem, int increment) {
        this.hangSubsystem = hangSubsystem;
        this.increment = increment;
        addRequirements(hangSubsystem);
    }

    @Override
    public void initialize() {
        hangSubsystem.setElevationMotorTargetPosition(increment);

    }

    @Override
    public void execute() {
        hangSubsystem.updateElevationPosition();
    }

    @Override
    public void end(boolean interrupted) {
        hangSubsystem.stop();
    }
    public boolean isFinished() {
        return hangSubsystem.isAtIncrementTarget(hangSubsystem.getPosition() + increment);
    }


}
