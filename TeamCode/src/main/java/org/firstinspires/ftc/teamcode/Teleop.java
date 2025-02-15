package org.firstinspires.ftc.teamcode;


import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.AutoSubsystems.ElevatorSubsystem;
import org.firstinspires.ftc.teamcode.AutoSubsystems.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.AutoSubsystems.HangSubsystem;
import org.firstinspires.ftc.teamcode.AutoSubsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.AutoSubsystems.OuttakeArmSubsystem;
import org.firstinspires.ftc.teamcode.AutoSubsystems.OuttakeClawSubsystem;

public class Teleop {
    public HangSubsystem hang;
    public OuttakeClawSubsystem claw;
    public OuttakeClawSubsystem.ClawGrabState clawGrabState;
    public OuttakeClawSubsystem.WristState wristState;
    public OuttakeClawSubsystem.SampleGrabState sampleGrabState;
    public ElevatorSubsystem elevatorSubsystem;
    public ExtendoSubsystem extend;
    public IntakeSubsystem intake;
    public IntakeSubsystem.SpinState intakeSpinState;
    public IntakeSubsystem.StopperState stopperState;
    public IntakeSubsystem.RotatorState rotatorState;
    public OuttakeArmSubsystem arm;
    public OuttakeArmSubsystem.OuttakeArmPos armState;

    private Follower follower;
    private Pose startPose;

    private Telemetry telemetry;

    private Gamepad gamepad1, gamepad2;
    private Gamepad currentGamepad1 = new Gamepad();
    private Gamepad currentGamepad2 = new Gamepad();
    private Gamepad previousGamepad1 = new Gamepad();
    private Gamepad previousGamepad2 = new Gamepad();

    private Timer autoBucketTimer = new Timer();

    private int flip = 1, autoBucketState = -1;

    public double speed = 1;

    private boolean fieldCentric, actionBusy;

    private PathChain autoBucketTo, autoBucketBack;
    private Pose autoBucketToEndPose, autoBucketBackEndPose;
    private boolean intakeActive;


    public Teleop(HardwareMap hardwareMap, Telemetry telemetry, Follower follower, Pose startPose, Gamepad gamepad1, Gamepad gamepad2) {
        claw = new OuttakeClawSubsystem(hardwareMap, wristState, sampleGrabState, clawGrabState);
        elevatorSubsystem = new ElevatorSubsystem(hardwareMap, telemetry);
        extend = new ExtendoSubsystem(hardwareMap, telemetry);
        intake = new IntakeSubsystem(hardwareMap, intakeSpinState, rotatorState, stopperState, telemetry);
        arm = new OuttakeArmSubsystem(hardwareMap, armState);
        hang = new HangSubsystem(hardwareMap, telemetry);
        this.follower = follower;
        this.telemetry = telemetry;

        this.startPose = new Pose(56,102.25,Math.toRadians(270));

        this.fieldCentric = fieldCentric;
        this.telemetry = telemetry;
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
        this.intakeActive = false;
    }

    public void init() {}

    public void start() {
        elevatorSubsystem.start();
        extend.start();
        extend.init();
        intake.transfer();
        claw.closeClaw();
        follower.setPose(startPose);
        follower.startTeleopDrive();
    }

    public void update() {

        if (actionNotBusy()) {
            previousGamepad1.copy(currentGamepad1);
            previousGamepad2.copy(currentGamepad2);
            currentGamepad1.copy(gamepad1);
            currentGamepad2.copy(gamepad2);

            intakeActive = gamepad1.a || gamepad1.b || gamepad1.right_trigger > 0 || gamepad1.left_trigger > 0;
            if(!intakeActive){
                stopIntake();
            }

            if(gamepad1.a){
                extendAndIntake();
            }
            if(gamepad1.b){
                transfer();
            }
            if(gamepad1.x){
                reset();
            }
            if(gamepad1.y){
                hang.hang();
            }

            if(gamepad1.right_trigger > 0){
                outtake();
            }
            else if(gamepad1.left_trigger > 0){
                intake();
            }

            if(gamepad1.left_bumper){
                fullLock();
            }
            if(gamepad1.right_bumper){
                fullUnlock();
            }

            if(gamepad1.dpad_right){
                specPick();
            }
            if(gamepad1.dpad_left){
                highSampleDrop();
            }
            if(gamepad1.dpad_up){
                elevatorIncrement();
            }
            if(gamepad1.dpad_down){
                specDrop();
            }


            follower.setTeleOpMovementVectors(flip * -gamepad1.left_stick_y * speed, flip * -gamepad1.left_stick_x * speed, -gamepad1.right_stick_x * speed * 0.5, true);
        } else {
            if(gamepad2.dpad_right) {
                stopActions();
            }
        }

        elevatorSubsystem.updatePIDF();

       // autoBucket();

        follower.update();

        telemetry.addData("X", follower.getPose().getX());
        telemetry.addData("Y", follower.getPose().getY());
        telemetry.addData("Heading", Math.toDegrees(follower.getPose().getHeading()));

        telemetry.addData("Lift Pos", elevatorSubsystem.getPos());
        telemetry.addData("Extend Pos", extend.leftExtend.getPosition());
        telemetry.addData("Claw Grab State", claw.clawGrabState);
        telemetry.addData("Claw Pivot State", claw.wristState);
        telemetry.addData("Claw Sample Stopper State", claw.sampleGrabState);
        telemetry.addData("Intake Spin State", intakeSpinState);
        telemetry.addData("Intake Pivot State", rotatorState);
        telemetry.addData("Arm State", arm.state);
        telemetry.addData("Action Busy", actionBusy);
        telemetry.addData("Auto Bucket State", autoBucketState);
        telemetry.update();
    }

    private void extendAndIntake(){
        extend.fullExtend();
        intake.lockSample();
        Timer extendAndIntakeTimer = new Timer();
        if (extendAndIntakeTimer.getElapsedTimeSeconds() >= 0.30) {
            intake.pickup();
            intake.intake();
        }

    }
    public void transfer(){
        intake.stop();
        intake.transfer();
        arm.transfer();
        claw.transfer();
        Timer transferTimer = new Timer();
        if(transferTimer.getElapsedTimeSeconds() >= 0.2){
            extend.reset();
        }
        intake.intake();

        if(transferTimer.getElapsedTimeSeconds() >= 0.6) {
            intake.unlockSample();
        }
        if(transferTimer.getElapsedTimeSeconds() >= 0.75){
            claw.lockSample();
        }
    }
    public void reset(){
        arm.reset();
        claw.reset();
        fullUnlock();
        Timer resetTimer = new Timer();
        if(resetTimer.getElapsedTimeSeconds() > 2){
            elevatorSubsystem.toReset();
        }
    }

    public void outtake(){
        intake.outtake();
    }
    public void intake(){
        intake.intake();
    }
    public void stopIntake(){
        intake.stop();
    }

    public void fullLock(){
        claw.closeClaw();
        claw.lockSample();
    }
    public void fullUnlock(){
        claw.openClaw();
        claw.unlockSample();
    }

    public void specPick(){
        arm.specPickUp();
        claw.pickup();
        claw.openClaw();
    }
    public void specDrop(){
        elevatorSubsystem.toHighChamber();
        arm.frontSpecDrop();
        claw.specFront();
    }
    public void highSampleDrop(){
        elevatorSubsystem.toHighBucket();
        arm.bucketDrop();
        claw.bucketDrop();
    }
    public void elevatorIncrement(){
        elevatorSubsystem.setTarget(elevatorSubsystem.getPos() - 200);
    }

    private void autoBucket() {
        switch (autoBucketState) {
            case 1:
                actionBusy = true;
                intake.transfer();
                intake.intake();
                claw.openClaw();
                claw.transfer();
                extend.reset();
                arm.transfer();

                follower.breakFollowing();
                follower.setMaxPower(0.85);

                autoBucketToEndPose = new Pose(17.750, 125.500, Math.toRadians(-45));

                autoBucketTo = follower.pathBuilder()
                        .addPath(new BezierCurve(new Point(follower.getPose()), new Point(58.000, 119.000, Point.CARTESIAN), new Point(autoBucketToEndPose)))
                        .setLinearHeadingInterpolation(follower.getPose().getHeading(), autoBucketToEndPose.getHeading())
                        .build();

                follower.followPath(autoBucketTo, true);

                setAutoBucketState(2);
                break;
            case 2:
                if (autoBucketTimer.getElapsedTimeSeconds() > 2) {
                    claw.openClaw();
                    setAutoBucketState(3);
                }
                break;
            case 3:
                if (autoBucketTimer.getElapsedTimeSeconds() > 0.5) {
                    elevatorSubsystem.toHighBucket();
                    setAutoBucketState(4);
                }
                break;
            case 4:
                if (autoBucketTimer.getElapsedTimeSeconds() > 0.5) {
                    arm.bucketDrop();
                    claw.openClaw();
                    intake.stop();
                    setAutoBucketState(5);
                }
                break;
            case 5:
                if (((follower.getPose().getX() <  autoBucketToEndPose.getX() + 0.5) && (follower.getPose().getY() > autoBucketToEndPose.getY() - 0.5)) && (elevatorSubsystem.getPos() > 50) && autoBucketTimer.getElapsedTimeSeconds() > 1) {
                    claw.openClaw();
                    setAutoBucketState(9);
                    //setAutoBucketState(6);
                }
                break;
            case 6:
                if(autoBucketTimer.getElapsedTimeSeconds() > 0.5) {
                    autoBucketBackEndPose = new Pose(60, 104, Math.toRadians(270));

                    autoBucketBack = follower.pathBuilder()
                            .addPath(new BezierCurve(new Point(follower.getPose()), new Point(58.000, 119.000, Point.CARTESIAN), new Point(autoBucketBackEndPose)))
                            .setLinearHeadingInterpolation(follower.getPose().getHeading(), autoBucketToEndPose.getHeading())
                            .build();

                    follower.followPath(autoBucketBack, true);

                    claw.openClaw();
                    claw.transfer();
                    arm.transfer();
                    setAutoBucketState(7);
                }
                break;
            case 7:
                if(autoBucketTimer.getElapsedTimeSeconds() > 0.5) {
                    elevatorSubsystem.toReset();
                    extend.fullExtend();
                    setAutoBucketState(8);
                }
                break;
            case 8:
                if((follower.getPose().getX() >  autoBucketBackEndPose.getX() - 0.5) && (follower.getPose().getY() < autoBucketBackEndPose.getY() + 0.5)) {
                    intake.pickup();
                    setAutoBucketState(9);
                }
                break;
            case 9:
                follower.breakFollowing();
                follower.setMaxPower(1);
                follower.startTeleopDrive();
                actionBusy = false;
                setAutoBucketState(-1);
                break;
        }
    }

    public void setAutoBucketState(int x) {
        autoBucketState = x;
        autoBucketTimer.resetTimer();
    }

    public void startAutoBucket() {
        setAutoBucketState(1);
    }

    private boolean actionNotBusy() {
        return !actionBusy;
    }

    private void stopActions() {
        follower.breakFollowing();
        follower.setMaxPower(1);
        follower.startTeleopDrive();
        actionBusy = false;
        setAutoBucketState(-1);
    }

}