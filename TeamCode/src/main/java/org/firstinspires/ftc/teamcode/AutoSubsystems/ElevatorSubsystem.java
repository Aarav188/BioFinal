package org.firstinspires.ftc.teamcode.AutoSubsystems;

import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_HIGHDROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_LEFT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_RIGHT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_RESET;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_SPEC;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_SPECDROP;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ElevatorSubsystem{
    private Telemetry telemetry;

    public DcMotor rightElevationMotor, leftElevationMotor;
    public boolean manual = false;
    public int pos, bottom;
    public RunAction toReset, toHighBucket, toHighChamber, toSpecPickup;
    public PIDController liftPID;
    public static int target;
    public static double p = 0.01, i = 0, d = 0;
    public static double f = 0.005;

    public ElevatorSubsystem(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;
        this.telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        rightElevationMotor = hardwareMap.get(DcMotor.class, ELEVATOR_MOTOR_RIGHT);
        leftElevationMotor = hardwareMap.get(DcMotor.class, ELEVATOR_MOTOR_LEFT);

        rightElevationMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftElevationMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        rightElevationMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightElevationMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftElevationMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftElevationMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftPID = new PIDController(p, i, d);

        toReset = new RunAction(this::toReset);
        toHighBucket = new RunAction(this::toHighBucket);
        toHighChamber = new RunAction(this::toHighChamber);
        toSpecPickup = new RunAction(this::toSpecPickup);
    }


    public void init() {
        liftPID.setPID(p,i,d);
        bottom = getPos();
    }
    public void start() {
        target = 0;
    }
    public void updatePIDF(){
        if (!manual) {
            liftPID.setPID(p,i,d);

            rightElevationMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            leftElevationMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

            double pid = liftPID.calculate(getPos(), target);
            double ticks_in_degrees = 537.7 / 360.0;
            double ff = Math.cos(Math.toRadians(target / ticks_in_degrees)) * f;
            double power = pid + ff;

            rightElevationMotor.setPower(power);
            leftElevationMotor.setPower(power);

            telemetry.addData("lift pos", getPos());
            telemetry.addData("lift target", target);
        }
    }

    public void manual(double n){
        manual = true;

        rightElevationMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftElevationMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        rightElevationMotor.setPower(n);
        leftElevationMotor.setPower(n);
    }

    public int getPos() {
        pos = rightElevationMotor.getCurrentPosition() - bottom;
        return leftElevationMotor.getCurrentPosition() - bottom;
    }
    public double getTarget() {
        return target;
    }
    public void setTarget(int b) {
        target = b;
    }
    public void toReset(){
        manual = false;
        setTarget(ELEVATOR_RESET);
    }
    public void addToTarget(int b) {
        target += b;
        setTarget(target);
    }

    public void toHighBucket(){
        manual = false;
        setTarget(ELEVATOR_HIGHDROP);
    }

    public void toHighChamber(){
        manual = false;
        setTarget(ELEVATOR_SPECDROP);
    }

    public void toSpecPickup(){
        manual = false;
        setTarget(ELEVATOR_SPEC);
    }
}