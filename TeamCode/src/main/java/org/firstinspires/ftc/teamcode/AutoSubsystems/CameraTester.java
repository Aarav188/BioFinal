package org.firstinspires.ftc.teamcode.AutoSubsystems;


import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Auto;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;
@TeleOp(name="CameraTester", group="A")
public class CameraTester extends OpMode {

    Follower follower;

    Pose startPose = new Pose(0,0,0);
    public CameraSubsystem camera;
    public ExtendoSubsystem extendoSubsystem;
    @Override
    public void init() {
        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(hardwareMap);
        extendoSubsystem = new ExtendoSubsystem(hardwareMap, telemetry);
        camera = new CameraSubsystem(hardwareMap, telemetry, extendoSubsystem);
        camera.start();
        telemetry.update();
        follower.setStartingPose(startPose);
    }

    @Override
    public void start() {
        camera.updateColor();
    }

    @Override
    public void loop() {
        camera.updateColor();
        if(gamepad1.a){
            moveToSample();
        }
    }


    public void moveToSample(){
        PathChain preload = follower.pathBuilder()
                .addPath(new BezierLine(new Point(startPose), new Point(camera.driveAlign(), 0, 0)))
                .setLinearHeadingInterpolation(startPose.getHeading(), 0)
                .build();
        follower.followPath(preload);
        camera.extendAlign();
    }
}
