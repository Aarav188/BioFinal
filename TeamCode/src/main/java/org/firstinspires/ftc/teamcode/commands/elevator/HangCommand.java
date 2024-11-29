package org.firstinspires.ftc.teamcode.commands.elevator;



import static org.firstinspires.ftc.teamcode.configs.RobotConfig.HANGPOS;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.bio.HangSubsystem;


public class HangCommand extends CommandBase {
    protected final HangSubsystem hangSubsystem;
    double timer;

    public HangCommand(HangSubsystem hangSubsystem) {
        this.hangSubsystem = hangSubsystem;
        this.timer = System.currentTimeMillis();
        addRequirements(hangSubsystem);
    }

    @Override
    public void initialize() {
        hangSubsystem.hang(HANGPOS);

    }

    @Override
    public void execute() {
        hangSubsystem.updateHangPostion();
    }

    @Override
    public void end(boolean interrupted) {
          hangSubsystem.hang(HANGPOS);
    }
    public boolean isFinished() {
        if (timer > 42000) {
            return true;
        }
        return hangSubsystem.isAtTarget();
    }


}
