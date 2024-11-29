package org.firstinspires.ftc.teamcode.commands.elevator;



import static org.firstinspires.ftc.teamcode.configs.RobotConfig.UNHANGPOS;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.bio.HangSubsystem;


public class HangReset extends CommandBase {
    protected final HangSubsystem hangSubsystem;
    ElapsedTime timer = new ElapsedTime();

    public HangReset(HangSubsystem hangSubsystem) {
        this.hangSubsystem = hangSubsystem;
        addRequirements(hangSubsystem);
    }

    public void initialize(){
        hangSubsystem.hang(UNHANGPOS);
    }
    @Override
    public void execute() {
        timer.reset();
        hangSubsystem.resetHangPostion();
    }

    @Override
    public void end(boolean interrupted) {
        hangSubsystem.killPower();
    }
    public boolean isFinished() {
        if (timer.milliseconds() > 2000) {
            return true;
        }
        return hangSubsystem.isAtTarget();
    }


}