package org.firstinspires.ftc.teamcode;


import static org.firstinspires.ftc.teamcode.configs.RobotConfig.DT_LEFT_FRONT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.DT_LEFT_REAR;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.DT_RIGHT_FRONT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.DT_RIGHT_REAR;

import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TestOdoManual extends LinearOpMode {

    private MotorEx leftEncoder;
    private MotorEx rightEncoder;
    private MotorEx perpEncoder;

    @Override
    public void runOpMode() throws InterruptedException {
        leftEncoder = new MotorEx(hardwareMap, DT_LEFT_FRONT); //
        rightEncoder = new MotorEx(hardwareMap, DT_LEFT_REAR); //
        perpEncoder = new MotorEx(hardwareMap, DT_RIGHT_FRONT); // right

        leftEncoder.resetEncoder();
        rightEncoder.resetEncoder();
        perpEncoder.resetEncoder();

        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("left", leftEncoder.getCurrentPosition());
            telemetry.addData("right", rightEncoder.getCurrentPosition());
            telemetry.addData("perp", perpEncoder.getCurrentPosition());
            telemetry.update();

        }
    }
}