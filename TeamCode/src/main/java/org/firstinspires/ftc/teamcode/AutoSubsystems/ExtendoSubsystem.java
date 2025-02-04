package org.firstinspires.ftc.teamcode.AutoSubsystems;

import static org.firstinspires.ftc.teamcode.configs.RobotConfig.EXTENDO_LEFT_IN_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.EXTENDO_LEFT_MAX_OUT_POSITION;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ExtendoSubsystem{
    private Telemetry telemetry;

    double pos = 0;
    public Servo leftExtend, rightExtend;
    public RunAction reset, halfExtend, fullExtend;

    public ExtendoSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        leftExtend = hardwareMap.get(Servo.class, "leftExtend");
        rightExtend = hardwareMap.get(Servo.class, "rightExtend");
        rightExtend.setDirection(Servo.Direction.REVERSE);

        reset = new RunAction(this::reset);
        halfExtend = new RunAction(this::halfExtend);
        fullExtend = new RunAction(this::fullExtend);
    }

    //TODO solve these positions and map the displacement. try to 0 them so 0 and 1 are perfectly equal then reverse one of them
    //TODO right one normal set to 0, left one reveresed set to 1
    public void setTarget(double b){
        leftExtend.setPosition(b);
        rightExtend.setPosition(b);
        pos = b;
    }

    public void reset(){
        setTarget(EXTENDO_LEFT_IN_POSITION);
    }

    public void halfExtend(){
        setTarget(EXTENDO_LEFT_MAX_OUT_POSITION/2);
    }

    public void fullExtend(){
        setTarget(EXTENDO_LEFT_MAX_OUT_POSITION);
    }

    public double getPos(){
        return pos;
    }

    public void init() {
        reset();
    }

    public void start(){
        reset();
    }
}