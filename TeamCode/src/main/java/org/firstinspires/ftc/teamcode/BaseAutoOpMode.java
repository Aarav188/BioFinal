package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.bio.ElevatorSubsystem;
import org.firstinspires.ftc.teamcode.bio.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.bio.HangSubsystem;
import org.firstinspires.ftc.teamcode.bio.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.bio.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.bio.OuttakeArmSubsystem;
import org.firstinspires.ftc.teamcode.bio.OuttakeClawSubsystem;

public abstract class BaseAutoOpMode extends CommandOpMode {


    protected FtcDashboard dashboard = FtcDashboard.getInstance();
    protected Telemetry dashboardTelemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

    protected GamepadEx driverPad;
    protected GamepadEx operatorPad;

    protected MecanumDriveSubsystem mecanumDriveSubsystem;
    //  protected MecanumDriveSubsystem mecanumDriveSubsystem;
//    protected IntakeServoSubsystem intakeServoSubsystem;
    protected IntakeSubsystem intakeSubsystem;
    //protected OuttakeRotatorSubsystem outtakeRotatorSubsystem;
    protected ElevatorSubsystem elevatorSubsystem;
    protected OuttakeClawSubsystem outtakeDropperSubsystem;
    protected OuttakeArmSubsystem outtakePivotSubsystem;
    protected OuttakeClawSubsystem outtakeClawSubsystem;
    protected ExtendoSubsystem extendoSubsystem;
    protected HangSubsystem hangSubsystem;


    //protected ColorSensorSubsystem intakeColorSubsystem;

//    protected TacoOdometrySubsystem tacoOdometrySubsystem;


    @Override
    public void initialize() {
        driverPad = new GamepadEx(gamepad1);
        operatorPad = new GamepadEx(gamepad2);

        //Do this before drivetrain initializes.
        new IMUSingleton(hardwareMap, dashboardTelemetry);



        //Using the roadrunner drivetrain and odometery from it.
        mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap, dashboardTelemetry);

        /* Do not use the roadrunner mechanism and use the FTCLib drivetrain and odometry
        mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap, dashboardTelemetry);
        periodicTelemetry.register(mecanumDriveSubsystem.telemetryCallback());
        tacoOdometrySubsystem = new TacoOdometrySubsystem(hardwareMap, dashboardTelemetry);
        periodicTelemetry.register(tacoOdometrySubsystem.telemetryCallback());

        */

//        airplaneSubsystem = new BackIntakeSubsystem(hardwareMap, dashboardTelemetry);
//        periodicTelemetry.register(airplaneSubsystem.telemetryCallback());

        intakeSubsystem = new IntakeSubsystem(hardwareMap, dashboardTelemetry);

//        intakeServoSubsystem = new IntakeServoSubsystem(hardwareMap, dashboardTelemetry);
//        periodicTelemetry.register(intakeServoSubsystem.telemetryCallback());

        outtakeDropperSubsystem = new OuttakeClawSubsystem(hardwareMap, dashboardTelemetry);

        outtakePivotSubsystem = new OuttakeArmSubsystem(hardwareMap, dashboardTelemetry);

//        outtakeRotatorSubsystem = new OuttakeRotatorSubsystem(hardwareMap, dashboardTelemetry);
//        periodicTelemetry.register(outtakeRotatorSubsystem.telemetryCallback());

        elevatorSubsystem = new ElevatorSubsystem(hardwareMap, dashboardTelemetry);

        outtakeClawSubsystem = new OuttakeClawSubsystem(hardwareMap, dashboardTelemetry);

        extendoSubsystem = new ExtendoSubsystem(hardwareMap, dashboardTelemetry);
        hangSubsystem = new HangSubsystem(hardwareMap, telemetry);



        register(mecanumDriveSubsystem,  elevatorSubsystem, intakeSubsystem, outtakeDropperSubsystem, outtakePivotSubsystem, outtakeClawSubsystem, extendoSubsystem, hangSubsystem);

        intakeSubsystem.depoPosition();
        outtakePivotSubsystem.resetArm();
        outtakeClawSubsystem.setWristReset();
        outtakeClawSubsystem.closeClaw();
        extendoSubsystem.reset();
        outtakeClawSubsystem.unlockSample();
    }
}
