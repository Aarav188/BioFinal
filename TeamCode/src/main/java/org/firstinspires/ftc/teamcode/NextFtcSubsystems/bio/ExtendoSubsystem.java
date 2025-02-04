package org.firstinspires.ftc.teamcode.NextFtcSubsystems.bio;

import static org.firstinspires.ftc.teamcode.configs.RobotConfig.EXTENDO_LEFT_IN_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.EXTENDO_LEFT_MAX_OUT_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.EXTENDO_RIGHT_IN_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.EXTENDO_RIGHT_MAX_OUT_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.LEFT_LINKAGE;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.RIGHT_LINKAGE;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.MultipleServosToSeperatePositions;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

import org.firstinspires.ftc.teamcode.commands.extendo.ExtendoOut;

import java.util.HashMap;
import java.util.Map;

public class ExtendoSubsystem extends Subsystem {
    // BOILERPLATE
    public static final ExtendoSubsystem INSTANCE = new ExtendoSubsystem();
    private ExtendoSubsystem() { }

    // USER CODE
    public Servo leftExtendo;
    public Servo rightExtendo;
    HashMap<Servo, Double> servos = new HashMap<>();

    public Command extendoIn() {
        HashMap<Servo, Double> servos = new HashMap<>();
        servos.put(leftExtendo, EXTENDO_LEFT_IN_POSITION);
        servos.put(rightExtendo, EXTENDO_RIGHT_IN_POSITION);
        return new MultipleServosToSeperatePositions(servos,this);
    }

    public Command extendoOut() {
        HashMap<Servo, Double> servos = new HashMap<>();
        servos.put(leftExtendo, EXTENDO_LEFT_MAX_OUT_POSITION);
        servos.put(rightExtendo, EXTENDO_RIGHT_MAX_OUT_POSITION);
        return new MultipleServosToSeperatePositions(servos,this); // IMPLEMENTED SUBSYSTEM
    }

    @Override
    public void initialize() {
        leftExtendo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, LEFT_LINKAGE);
        rightExtendo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, RIGHT_LINKAGE);
    }
}