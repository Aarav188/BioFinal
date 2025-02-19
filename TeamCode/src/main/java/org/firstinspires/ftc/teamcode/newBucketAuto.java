package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;
@Autonomous(name="BlueBucket", group="A")
public class newBucketAuto extends OpMode {
    public int pathState;
    public Auto auto;

    @Override
    public void init() {
        Constants.setConstants(FConstants.class, LConstants.class);
        auto = new Auto(hardwareMap, telemetry, new Follower(hardwareMap), true, true);

        telemetry.addData("state", pathState);
        telemetry.addData("x", auto.follower.getPose().getX());
        telemetry.addData("y", auto.follower.getPose().getY());
        telemetry.addData("h", auto.follower.getPose().getHeading());
        telemetry.addData("actionBusy", auto.actionBusy);
        telemetry.update();
    }

    @Override
    public void start() {
        auto.start();
        setPathState(0);
    }

    @Override
    public void loop() {
        auto.update();
        pathUpdate();

        telemetry.addData("state", pathState);
        telemetry.addData("x", auto.follower.getPose().getX());
        telemetry.addData("y", auto.follower.getPose().getY());
        telemetry.addData("h", auto.follower.getPose().getHeading());
        telemetry.addData("actionBusy", auto.actionBusy);
        telemetry.update();
    }

    public void pathUpdate() {
        switch (pathState) {
            case 0:
                auto.follower.setMaxPower(0.7);
//                auto.startChamber();
                auto.startBucket();
                auto.follower.followPath(auto.preload);
                setPathState(1);
                break;
            case 1:
                if(!auto.follower.isBusy() && auto.actionNotBusy()) {
                    auto.setIntakeState(3);
                    auto.follower.followPath(auto.element1);
                    setPathState(2);
                }
                break;
            case 2:
                if(!auto.follower.isBusy()) {
                    //auto.startIntake();
                    auto.follower.setMaxPower(0.5);
                    auto.follower.followPath(auto.score1);
                    setPathState(3);
                }
                break;
//            case 3:
//                if(/*auto.actionNotBusy() && */!auto.follower.isBusy()) {
//                    //auto.startTransfer();
//                    auto.follower.setMaxPower(0.5);
//                    auto.follower.followPath(auto.element2);
//                    setPathState(4);
//                }
//                break;
//            case 4:
//                if(auto.actionNotBusy() && !auto.follower.isBusy()) {
//                    ///auto.startBucket();
//                    auto.follower.setMaxPower(0.5);
//                    auto.follower.followPath(auto.score1);
//                    setPathState(5);
//                }
//                break;
//            case 5:
//                if(!auto.follower.isBusy() && auto.actionNotBusy()) {
//                    //auto.startIntake();
//                    auto.follower.setMaxPower(0.5);
//                    auto.follower.followPath(auto.element3);
//                    setPathState(6);
//                }
//                break;
//            case 6:
//                if(auto.actionNotBusy() && !auto.follower.isBusy()) {
//                    //auto.startTransfer();
//                    auto.follower.setMaxPower(0.5);
//                    auto.follower.followPath(auto.score1);
//                    setPathState(7);
//                }
//                break;
//            case 7:
//                if(auto.actionNotBusy() && !auto.follower.isBusy()) {
//                    //auto.startBucket();
//                    auto.follower.setMaxPower(0.5);
//                    auto.follower.followPath(auto.park);
//                    setPathState(-1);
//                }
//                break;
//            case 8:
//                if(!auto.follower.isBusy() && auto.actionNotBusy()) {
//                    //auto.startIntake();
//                    auto.follower.setMaxPower(0.5);
//                    auto.follower.followPath(auto.element3);
//                    setPathState(9);
//                }
//                break;
//            case 9:
//                if(auto.actionNotBusy() && !auto.follower.isBusy()) {
//                    //auto.startTransfer();
//                    setPathState(10);
//                }
//                break;
//            case 10:
//                if(auto.actionNotBusy() && !auto.follower.isBusy()) {
//                    //auto.startBucket();
//                    auto.follower.setMaxPower(0.5);
//                    auto.follower.followPath(auto.score3);
//                    setPathState(11);
//                }
//                break;
//            case 11:
//                if(auto.actionNotBusy() && !auto.follower.isBusy()) {
//                    //auto.startPark();
//                    auto.follower.setMaxPower(0.9);
//                    auto.follower.followPath(auto.park);
//                    setPathState(-1);
//                }
//                break;
        }
    }

    public void setPathState(int x) {
        pathState = x;
    }
}
