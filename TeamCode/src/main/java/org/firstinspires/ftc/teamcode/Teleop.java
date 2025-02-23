package org.firstinspires.ftc.teamcode;


import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.AutoSubsystems.ElevatorSubsystem;
import org.firstinspires.ftc.teamcode.AutoSubsystems.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.AutoSubsystems.HangSubsystem;
import org.firstinspires.ftc.teamcode.AutoSubsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.AutoSubsystems.OuttakeArmSubsystem;
import org.firstinspires.ftc.teamcode.AutoSubsystems.OuttakeClawSubsystem;
import org.firstinspires.ftc.teamcode.configs.FieldConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

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

    private boolean hangActive;

    private float starting_left_stick_y;
    private float starting_left_stick_x;
    private float starting_right_stick_x;
    public String color;
    public FieldConstants.RobotStart startLocation;
    public boolean triggerAction;



    public Teleop(HardwareMap hardwareMap, Telemetry telemetry, Follower follower, Pose startPose, Gamepad gamepad1, Gamepad gamepad2,  boolean isBlue, boolean isBucket) {

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
        this.hangActive = false;

        startLocation = isBlue ? (isBucket ? FieldConstants.RobotStart.BLUE_BUCKET : FieldConstants.RobotStart.BLUE_OBSERVATION) : (isBucket ? FieldConstants.RobotStart.RED_BUCKET : FieldConstants.RobotStart.RED_OBSERVATION);

        color = isBlue ? "BLUE" : "RED";
    }

    public void init() {
        follower.setPose(startPose);
        starting_left_stick_y = gamepad1.left_stick_y;
        starting_left_stick_x = gamepad1.left_stick_x;
        starting_right_stick_x = gamepad1.right_stick_y;
        telemetry.addData("",starting_left_stick_x);
        telemetry.addData("", starting_left_stick_y);
        telemetry.addData("", starting_right_stick_x);
        triggerAction = false;
    }

    public void start() {
        elevatorSubsystem.start();
        extend.start();
        extend.init();
        intake.transfer();
        claw.closeClaw();
        intake.lockSample();
        reset();

        follower.startTeleopDrive();
    }

    public void update() {

        if (actionNotBusy()) {
            previousGamepad1.copy(currentGamepad1);
            previousGamepad2.copy(currentGamepad2);
            currentGamepad1.copy(gamepad1);
            currentGamepad2.copy(gamepad2);

            if(!intakeActive){
                stopIntake();
            }

            if(intake.checkStop(color) == IntakeSubsystem.SpinState.OUTTAKE){
                outtake();
            }
            else if(intake.checkStop(color) == IntakeSubsystem.SpinState.STOP){
                transfer();
            }

            if(gamepad1.a){ //done
                intakeActive = true;
                extendAndIntake();
            }
            if(System.currentTimeMillis() - extendTimer >= 600 && extendoState == ExtendoState.INTAKE){
                intake.pickup();
                intake.intake();
                extendTimer = 0;
                extendoState = null;
            }


            if(gamepad1.b){ //done
                transfer();
                intakeActive = false;
            }

            if(System.currentTimeMillis() - transferTimer >= 200 && transferState == 0){
                extend.reset();
                intake.intake();
                transferState++;
            }
            else if(System.currentTimeMillis() - transferTimer >= 600 && transferState == 1){
                intake.unlockSample();
                transferState++;
            }
            else if(System.currentTimeMillis() - transferTimer >= 1300 && transferState == 2){
                claw.lockSample();
                transferState++;
            }
            else if(System.currentTimeMillis() - transferTimer >= 1600 && transferState == 3){
                arm.reset();
                claw.reset();
                intake.stop();
                intakeActive = false;
                transferState = -1;
            }


            if(System.currentTimeMillis() - outtakeTimer >= 500 && outtakeState){
                intake();
                outtakeState = false;
            }
            if(gamepad1.x){ //done
                reset();
            }
            if(gamepad1.y){
                gameHang();
            }

            if(gamepad1.right_trigger > 0){ //done
                triggerAction = true;
                intakeActive = true;
                outtake();
            }
            else if(gamepad1.left_trigger > 0){ //done
                triggerAction = true;
                intakeActive = true;
                intake();

            }
            else if(gamepad1.right_trigger == 0 && gamepad1.left_trigger == 0 && triggerAction){
                triggerAction = false;
                intakeActive = false;
            }

            if(gamepad1.left_bumper){ //done
                fullLock();
            }
            if(gamepad1.right_bumper){ //done
                fullUnlock();
            }

            if(gamepad1.dpad_right){ //tuned
                specPick();
            }
            if(gamepad1.dpad_left){ //done
                highSampleDrop();
            }
            if(gamepad1.dpad_up){ //done
                elevatorIncrement();
            }
            if(gamepad1.dpad_down){
                specDrop();
            }


            follower.setTeleOpMovementVectors(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, true);

        } else {
            if(gamepad2.dpad_right) {
                stopActions();
            }
        }

        elevatorSubsystem.updatePIDF();
        follower.update();
       // autoBucket();

//        follower.update();

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
    double extendTimer = 0;
    enum ExtendoState{
        TRANSFER, INTAKE
    }
    ExtendoState extendoState = null;
    private void extendAndIntake(){
        extend.fullExtend();

        Timer extendAndIntakeTimer = new Timer();

        intake.lockSample();


        extendTimer = System.currentTimeMillis();
        extendoState = ExtendoState.INTAKE;

//        intake.stop("BLUE");

    }

    public void gameHang(){
        if (!hangActive){
            hang.hang();
            hangActive = true;
        }
        else{
            hang.lower();
            hangActive = false;
        }
    }
    double transferTimer = 0;
    int transferState = -1;
    public void transfer(){
        transferState = 0;
        intake.intake();
        intake.transfer();
        arm.transfer();
        claw.transfer();
        claw.unlockSample();
        transferTimer = System.currentTimeMillis();
        extendoState = ExtendoState.TRANSFER;



//        if(transferTimer.getElapsedTimeSeconds() >= 0.6) {
//            intake.unlockSample();
//        }
//        if(transferTimer.getElapsedTimeSeconds() >= 0.75){
//            claw.lockSample();
//        }




    }

    public void reset(){
        arm.reset();
        claw.reset();
        fullUnlock();
        elevatorSubsystem.toReset();
    }

    double outtakeTimer = 0;
    boolean outtakeState = false;
    public void outtake(){
        intake.outtake();
        outtakeTimer = System.currentTimeMillis();
        outtakeState = true;

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
        long specPickTimer = System.currentTimeMillis();
        while(System.currentTimeMillis()<1000+specPickTimer){}
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