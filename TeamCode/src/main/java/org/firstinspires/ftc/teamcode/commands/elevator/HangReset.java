package org.firstinspires.ftc.teamcode.commands.elevator;



import static org.firstinspires.ftc.teamcode.configs.RobotConfig.HANGPOS;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.bio.HangSubsystem;


public class HangReset extends CommandBase {
    protected final HangSubsystem hangSubsystem;
    double timer;

    public HangReset(HangSubsystem hangSubsystem) {
        this.hangSubsystem = hangSubsystem;
        this.timer = System.currentTimeMillis();
        addRequirements(hangSubsystem);
    }

    @Override
    public void initialize() {
        hangSubsystem.setElevationMotorTargetPosition(0);

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
        if (timer > 42000) {
            return true;
        }
        return hangSubsystem.isAtTarget();
    }


}
