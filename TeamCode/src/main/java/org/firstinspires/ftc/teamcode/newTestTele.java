package org.firstinspires.ftc.teamcode;


import static org.firstinspires.ftc.teamcode.configs.FieldConstants.blueObservationParkPose;

import com.pedropathing.follower.Follower;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;


@TeleOp(name="Drive", group="A")
public class newTestTele extends OpMode {

    private Teleop teleop;

    @Override
    public void init() {
        Constants.setConstants(FConstants.class, LConstants.class);
        teleop = new Teleop(hardwareMap, telemetry, new Follower(hardwareMap), blueObservationParkPose, gamepad1, gamepad2);
        teleop.init();
    }

    @Override
    public void start() {
        teleop.start();
    }

    @Override
    public void loop() {
        teleop.update();
    }
}