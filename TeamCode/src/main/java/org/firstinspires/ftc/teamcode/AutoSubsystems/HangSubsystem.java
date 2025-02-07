package org.firstinspires.ftc.teamcode.AutoSubsystems;

import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_HIGHDROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_LEFT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MOTOR_RIGHT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_RESET;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_SPEC;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_SPECDROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.HANG;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class HangSubsystem {
    private Telemetry telemetry;

    public DcMotor hangMotor;
    public int pos, bottom;
    public static int target;
    public HangSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        hangMotor = hardwareMap.get(DcMotor.class, HANG);

        hangMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hangMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hangMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }


    public void init() {
        bottom = getPos();
        target = 0;
    }

    public void hang() {
        hangMotor.setTargetPosition(-30000);
        hangMotor.setPower(1);
        if (!hangMotor.isBusy()) {
            hangMotor.setPower(0);
        }
    }



    public int getPos() {
        pos = hangMotor.getCurrentPosition() - bottom;
        return hangMotor.getCurrentPosition() - bottom;
    }
}