package org.firstinspires.ftc.teamcode.bio;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.configs.RobotConfig;

import java.util.HashMap;
import java.util.Map;

public class MecanumDriveSubsystem extends TacoSubsystem implements IMecanumDriveSubsystem {

    private final MecanumDrive md;
    private final MotorEx fL;
    private final MotorEx fR;
    private final MotorEx bL;
    private final MotorEx bR;

    public MecanumDriveSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        super(hardwareMap, telemetry);

        fL = new MotorEx(hardwareMap, RobotConfig.DT_LEFT_FRONT, Motor.GoBILDA.RPM_435);
        //   fL.setInverted(true);
        fR = new MotorEx(hardwareMap, RobotConfig.DT_RIGHT_FRONT, Motor.GoBILDA.RPM_435);
        //    fR.setInverted(true);
        bL = new MotorEx(hardwareMap, RobotConfig.DT_LEFT_REAR, Motor.GoBILDA.RPM_435);
        //    bL.setInverted(true);
        bR = new MotorEx(hardwareMap, RobotConfig.DT_RIGHT_REAR, Motor.GoBILDA.RPM_435);
        //     bR.setInverted(true);


        MotorGroup mg = new MotorGroup(fL, fR, bL, bR);
        mg.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        mg.setRunMode(Motor.RunMode.RawPower);

        md = new MecanumDrive(fL, fR, bL, bR);


    }


    @Override
    public void robotCentricDrive(double strafeSpeed, double forwardSpeed, double turnSpeed) {
        md.driveRobotCentric(strafeSpeed, forwardSpeed, turnSpeed);
    }

    @Override
    public void robotCentricDrive(double strafeSpeed, double forwardSpeed, double turnSpeed, boolean squareInputs) {
        md.driveRobotCentric(strafeSpeed, forwardSpeed, turnSpeed, squareInputs);
    }

    public MecanumDrive getMecanumDrive() {
        return md;
    }

}
