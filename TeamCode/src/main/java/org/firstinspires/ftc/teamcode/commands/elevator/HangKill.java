//package org.firstinspires.ftc.teamcode.commands.elevator;
//
//
//
//import static org.firstinspires.ftc.teamcode.configs.RobotConfig.HANGPOS;
//
//import com.arcrobotics.ftclib.command.CommandBase;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.teamcode.bio.HangSubsystem;
//
//
//public class HangKill extends CommandBase {
//    protected final HangSubsystem hangSubsystem;
//    double timer;
//
//    public HangKill(HangSubsystem hangSubsystem) {
//        this.hangSubsystem = hangSubsystem;
//        this.timer = System.currentTimeMillis();
//        addRequirements(hangSubsystem);
//    }
//
//    @Override
//    public void initialize() {
//
//    }
//
//    @Override
//    public void execute() {
//        //hangSubsystem.killPower();
//    }
//
//    @Override
//    public void end(boolean interrupted) {
////        hangSubsystem.killPower();
//    }
//    public boolean isFinished() {
//        return true;
//    }
//
//
//}
