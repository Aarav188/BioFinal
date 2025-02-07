package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.configs.FieldConstants.blueBucketLeftSampleControlPose;
import static org.firstinspires.ftc.teamcode.configs.FieldConstants.blueBucketRightSampleControlPose;
import static org.firstinspires.ftc.teamcode.configs.FieldConstants.blueBucketRightSamplePose;
import static org.firstinspires.ftc.teamcode.configs.FieldConstants.blueBucketScorePose;
import static org.firstinspires.ftc.teamcode.configs.FieldConstants.blueBucketStartPose;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.AutoSubsystems.ElevatorSubsystem;
import org.firstinspires.ftc.teamcode.AutoSubsystems.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.AutoSubsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.AutoSubsystems.OuttakeArmSubsystem;
import org.firstinspires.ftc.teamcode.AutoSubsystems.OuttakeClawSubsystem;
import org.firstinspires.ftc.teamcode.commands.intake.Intake;
import org.firstinspires.ftc.teamcode.configs.FieldConstants.RobotStart;
import org.firstinspires.ftc.teamcode.configs.FieldConstants;

public class Auto {

    private RobotStart startLocation;

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


    public Follower follower;
    public Telemetry telemetry;

    public boolean actionBusy, liftPIDF = true;
    public double liftManual = 0;

    public Timer transferTimer = new Timer(), bucketTimer = new Timer(), chamberTimer = new Timer(), intakeTimer = new Timer(), parkTimer = new Timer(), specimenTimer = new Timer(), chamberTimer2 = new Timer();
    public int transferState = -1, bucketState = -1, chamberState = -1, intakeState = -1, parkState = -1, specimenState = -1;

    public Path element1, score1, element2, score2, element3, score3;
    public PathChain pushSamples, preload,specimen1, specimen2, specimen3, specimen4, grab1, grab2, grab3, grab4, park;
    public Pose startPose, preloadPose, sample1Pose, sample1ControlPose, sample2Pose, sample2ControlPose, sample3Pose, sample3ControlPose, sampleScorePose, parkControlPose, parkPose, grab1Pose, specimen1Pose, grab2Pose, specimen2Pose, grab3Pose, specimen3Pose, grab4Pose, specimen4Pose, specimenSetPose;

    public Auto(HardwareMap hardwareMap, Telemetry telemetry, Follower follower, boolean isBlue, boolean isBucket) {
        claw = new OuttakeClawSubsystem(hardwareMap, wristState, sampleGrabState, clawGrabState);
        elevatorSubsystem = new ElevatorSubsystem(hardwareMap, telemetry);
        extend = new ExtendoSubsystem(hardwareMap, telemetry);
        intake = new IntakeSubsystem(hardwareMap, intakeSpinState, rotatorState, stopperState);
        arm = new OuttakeArmSubsystem(hardwareMap, armState);

        this.follower = follower;
        this.telemetry = telemetry;

        startLocation = isBlue ? (isBucket ? RobotStart.BLUE_BUCKET : RobotStart.BLUE_OBSERVATION) : (isBucket ? RobotStart.RED_BUCKET : RobotStart.RED_OBSERVATION);

        createPoses();
        buildPaths();

        init();
    }

    public void init() {
        if(startLocation == RobotStart.BLUE_BUCKET || startLocation == RobotStart.RED_BUCKET){
            claw.openClaw();
        }
        else{
            claw.closeClaw();
        }
        claw.reset();
        claw.lockSample();
        elevatorSubsystem.init();
        extend.init();
        intake.transfer();
        arm.frontSpecDrop();
        telemetryUpdate();

        follower.setStartingPose(startPose);
    }

    public void start() {
        elevatorSubsystem.start();
        extend.start();
        extend.init();
        intake.transfer();
        claw.closeClaw();

        follower.setStartingPose(startPose);
    }

    public void update() {
        follower.update();

        elevatorSubsystem.updatePIDF();

        transfer();
        bucket();
        chamber();
        intake();
        park();
        specimen();
        telemetryUpdate();
    }

    public void createPoses() { //Able to be cut
        switch (startLocation) {
            case BLUE_BUCKET:
                startPose = FieldConstants.blueBucketStartPose;
                preloadPose = FieldConstants.blueBucketPreloadPose;
                sample1ControlPose = FieldConstants.blueBucketLeftSampleControlPose;
                sample1Pose = FieldConstants.blueBucketLeftSamplePose;
                sample2ControlPose = FieldConstants.blueBucketMidSampleControlPose;
                sample2Pose = FieldConstants.blueBucketMidSamplePose;
                sample3ControlPose = FieldConstants.blueBucketRightSampleControlPose;
                sample3Pose = FieldConstants.blueBucketRightSamplePose;
                sampleScorePose = FieldConstants.blueBucketScorePose;
                parkControlPose = FieldConstants.blueBucketParkControlPose;
                parkPose = FieldConstants.blueBucketParkPose;
                break;

            case BLUE_OBSERVATION:
                startPose = FieldConstants.blueObservationStartPose;
                preloadPose = FieldConstants.blueObservationPreloadPose;
                specimenSetPose = FieldConstants.blueObservationSpecimenSetPose;
                grab1Pose = FieldConstants.blueObservationSpecimenPickupPose;
                grab2Pose = FieldConstants.blueObservationSpecimenPickup2Pose;
                grab3Pose = FieldConstants.blueObservationSpecimenPickup3Pose;
                grab4Pose = FieldConstants.blueObservationSpecimenPickup4Pose;
                specimen1Pose = FieldConstants.blueObservationSpecimen1Pose;
                specimen2Pose = FieldConstants.blueObservationSpecimen2Pose;
                specimen3Pose = FieldConstants.blueObservationSpecimen3Pose;
                specimen4Pose = FieldConstants.blueObservationSpecimen4Pose;


                parkPose = FieldConstants.blueObservationParkPose;
                break;

            case RED_BUCKET:
                startPose = FieldConstants.redBucketStartPose;
                //parkPose = redBucketPark;
                break;

            case RED_OBSERVATION:
                startPose = FieldConstants.redObservationStartPose;
                //parkPose = redObservationPark;
                break;
        }

        follower.setStartingPose(startPose);
    }

    public void buildPaths() {
        if((startLocation == RobotStart.BLUE_BUCKET) || (startLocation == RobotStart.RED_BUCKET)) {
            preload = follower.pathBuilder()
                    .addPath(new BezierLine(new Point(startPose), new Point(preloadPose)))
                    .setLinearHeadingInterpolation(startPose.getHeading(), preloadPose.getHeading())
                    .build();

            element1 = new Path(new BezierCurve(new Point(preloadPose), new Point(sample1ControlPose), new Point(sample1Pose)));
            element1.setLinearHeadingInterpolation(preloadPose.getHeading(), sample1Pose.getHeading());

            score1 = new Path(new BezierLine(new Point(sample1Pose), new Point(sampleScorePose)));
            score1.setLinearHeadingInterpolation(sample1Pose.getHeading(), sampleScorePose.getHeading());

            element2 = new Path(new BezierCurve(new Point(sampleScorePose), new Point(sample2ControlPose), new Point(sample2Pose)));
            element2.setLinearHeadingInterpolation(sampleScorePose.getHeading(), sample2Pose.getHeading());

            score2 = new Path(new BezierLine(new Point(sample2Pose), new Point(sampleScorePose)));
            score2.setLinearHeadingInterpolation(sample2Pose.getHeading(), sampleScorePose.getHeading());

            element3 = new Path(new BezierCurve(new Point(sampleScorePose), new Point(sample3ControlPose), new Point(sample3Pose)));
            element3.setLinearHeadingInterpolation(sampleScorePose.getHeading(), sample3Pose.getHeading());

            score3 = new Path(new BezierLine(new Point(sample3Pose), new Point(sampleScorePose)));
            score3.setLinearHeadingInterpolation(sample3Pose.getHeading(), sampleScorePose.getHeading());

            park = follower.pathBuilder()
                    .addPath(new BezierCurve(new Point(sampleScorePose), new Point(parkControlPose), new Point(parkPose)))
                    .setLinearHeadingInterpolation(sampleScorePose.getHeading(), parkPose.getHeading())
                    .build();
        }

        if (startLocation == RobotStart.BLUE_OBSERVATION || startLocation == RobotStart.RED_OBSERVATION) {
            preload = follower.pathBuilder()
                    .addPath(new BezierLine(new Point(startPose), new Point(preloadPose)))
                    .setLinearHeadingInterpolation(startPose.getHeading(), preloadPose.getHeading())
                    .setZeroPowerAccelerationMultiplier(4)
                    .build();

            pushSamples = follower.pathBuilder()
                    .addPath(new BezierCurve(new Point(preloadPose), new Point(15, 36, Point.CARTESIAN), new Point(61, 36.25, Point.CARTESIAN), new Point(59, 26.000, Point.CARTESIAN)))
                    .setLinearHeadingInterpolation(preloadPose.getHeading(), Math.toRadians(180))
                    .addPath(new BezierLine(new Point(59.000, 26.000, Point.CARTESIAN), new Point(28, 26.000, Point.CARTESIAN)))
                    .setLinearHeadingInterpolation(Math.toRadians(180),Math.toRadians(180))
                    .addPath(new BezierCurve(new Point(28, 26.000, Point.CARTESIAN), new Point(52.000, 30.000, Point.CARTESIAN), new Point(58.000, 16.000, Point.CARTESIAN)))
                    .setLinearHeadingInterpolation(Math.toRadians(180),Math.toRadians(180))
                    .addPath(new BezierLine(new Point(58.000, 16.000, Point.CARTESIAN),new Point(28, 16.000, Point.CARTESIAN)))
                    .setLinearHeadingInterpolation(Math.toRadians(180),Math.toRadians(180))
                    .addPath(new BezierCurve(new Point(28, 16.000, Point.CARTESIAN), new Point(56.000, 16.000, Point.CARTESIAN), new Point(56.000, 10, Point.CARTESIAN)))
                    .setLinearHeadingInterpolation(Math.toRadians(180),Math.toRadians(180))
                    .addPath(new BezierLine(new Point(56.000, 10, Point.CARTESIAN), new Point(28, 10, Point.CARTESIAN)))
                    .setLinearHeadingInterpolation(Math.toRadians(180),Math.toRadians(180))
                    //.setZeroPowerAccelerationMultiplier(0.5)
                    .build();

            grab1 = follower.pathBuilder()
                    .addPath(new BezierLine(new Point(28,10,Point.CARTESIAN), new Point(grab1Pose)))
                    .setLinearHeadingInterpolation(Math.toRadians(180), grab1Pose.getHeading())
                    .setZeroPowerAccelerationMultiplier(1)
                    .build();

            specimen1 = follower.pathBuilder()
                    .addPath(new BezierCurve(new Point(grab1Pose), new Point(specimen1Pose.getX()-10, specimen1Pose.getY(), Point.CARTESIAN), new Point(specimen1Pose)))
                    .setLinearHeadingInterpolation(grab1Pose.getHeading(), specimen1Pose.getHeading())
                    .setZeroPowerAccelerationMultiplier(1)
                    .build();

            grab2 = follower.pathBuilder()
                    .addPath(new BezierLine(new Point(specimen1Pose), new Point(grab2Pose)))
                    .setLinearHeadingInterpolation(specimen1Pose.getHeading(), grab2Pose.getHeading())
                    .setZeroPowerAccelerationMultiplier(1)
                    .build();

            specimen2 = follower.pathBuilder()
                    .addPath(new BezierCurve(new Point(grab2Pose), new Point(specimen2Pose.getX() - 10, specimen2Pose.getY(), Point.CARTESIAN),new Point(specimen2Pose)))
                    .setLinearHeadingInterpolation(grab2Pose.getHeading(), specimen2Pose.getHeading())
                    .setZeroPowerAccelerationMultiplier(1)
                    .build();

            grab3 = follower.pathBuilder()
                    .addPath(new BezierLine(new Point(specimen2Pose), new Point(grab3Pose)))
                    .setLinearHeadingInterpolation(specimen2Pose.getHeading(), grab3Pose.getHeading())
                    .setZeroPowerAccelerationMultiplier(1)
                    .build();

            specimen3 = follower.pathBuilder()
                    .addPath(new BezierCurve(new Point(grab3Pose), new Point(specimen3Pose.getX() - 10, specimen3Pose.getY(), Point.CARTESIAN),new Point(specimen3Pose)))
                    .setLinearHeadingInterpolation(grab3Pose.getHeading(), specimen3Pose.getHeading())
                    .setZeroPowerAccelerationMultiplier(1)
                    .build();

            grab4 = follower.pathBuilder()
                    .addPath(new BezierLine(new Point(specimen3Pose), new Point(grab4Pose)))
                    .setLinearHeadingInterpolation(specimen3Pose.getHeading(), grab4Pose.getHeading())
                    .setZeroPowerAccelerationMultiplier(1)
                    .build();

            specimen4 = follower.pathBuilder()
                    .addPath(new BezierCurve(new Point(grab4Pose), new Point(specimen4Pose.getX() - 10, specimen3Pose.getY(), Point.CARTESIAN),new Point(specimen3Pose)))
                    .setLinearHeadingInterpolation(grab4Pose.getHeading(), specimen4Pose.getHeading())
                    .setZeroPowerAccelerationMultiplier(1)
                    .build();

            park = follower.pathBuilder()
                    .addPath(new BezierLine(new Point(specimen4Pose), new Point(parkPose)))
                    .setLinearHeadingInterpolation(specimen4Pose.getHeading(), parkPose.getHeading())
                    .setZeroPowerAccelerationMultiplier(3)
                    .build();

        }
    }

    public void transfer() {
        switch (transferState) {
            case 1:
                actionBusy = true;
                intake.transfer();
                intake.intake();
                elevatorSubsystem.toReset();
                arm.transfer();
                claw.transfer();
                claw.openClaw();
                extend.reset();
                transferTimer.resetTimer();
                setTransferState(2);
                break;
            case 2:
                if (transferTimer.getElapsedTimeSeconds() > 1.5) {
                    intake.stop();
                    transferTimer.resetTimer();
                    setTransferState(3);
                }
                break;
            case 3:
                if (transferTimer.getElapsedTimeSeconds() > 1) {
                    elevatorSubsystem.toReset();
                    transferTimer.resetTimer();
                    setTransferState(4);
                }
                break;
            case 4:
                if (transferTimer.getElapsedTimeSeconds() > 0.5) {
                    claw.closeClaw();
                    actionBusy = false;
                    setTransferState(-1);
                }
                break;
        }
    }

    public void setTransferState(int x) {
        transferState = x;
    }

    public void startTransfer() {
        if (actionNotBusy()) {
            setTransferState(1);
        }
    }

    public void bucket() {
        switch (bucketState) {
            case 1:
                actionBusy = true;
                intake.transfer();
                intake.stop();
                elevatorSubsystem.toHighBucket();
                claw.closeClaw();
                extend.reset();
                bucketTimer.resetTimer();
                setBucketState(2);
                break;
            case 2:
                if (bucketTimer.getElapsedTimeSeconds() > 0.5) {
                    arm.bucketDrop();
                    claw.bucketDrop();
                    bucketTimer.resetTimer();
                    setBucketState(3);
                }
                break;
            case 3:
                if (bucketTimer.getElapsedTimeSeconds() > 1) {
                    actionBusy = false;
                    setBucketState(-1);
                }

        }
    }

    public void setBucketState(int x) {
        bucketState = x;
    }

    public void startBucket() {
        if (actionNotBusy()) {
            setBucketState(1);
        }
    }

    public void chamber() {
        switch (chamberState) {
            case 1:
                actionBusy = true;
                arm.frontSpecDrop();
                claw.closeClaw();
                claw.specFront();
                extend.reset();
                chamberTimer.resetTimer();
                setChamberState(2);
                break;
            case 2:
                if ((follower.getPose().getX() >= specimen1Pose.getX() - 0.5)) {
                    claw.openClaw();
                    chamberTimer.resetTimer();
                    setChamberState(3);
                }
                break;
            case 3:
                if(chamberTimer.getElapsedTimeSeconds() > 0.25) {
                    arm.reset();
                    actionBusy = false;
                    setChamberState(-1);
                }
                break;
        }
    }

    public void setChamberState(int x) {
        chamberState = x;
    }

    public void startChamber() {
        if(actionNotBusy()) {
            setChamberState(1);
        }
    }

    public void specimen() {
        switch (specimenState) {
            case 1:
                actionBusy = true;
                claw.openClaw();
                extend.reset();
                arm.specPickUp();
                claw.specFront();
                specimenTimer.resetTimer();
                setSpecimenState(2);
            case 2:
                if(specimenTimer.getElapsedTimeSeconds() > 0) {
                    actionBusy = false;
                    setSpecimenState(-1);
                }
                break;
        }
    }

    public void setSpecimenState(int x) {
        specimenState = x;
    }

    public void startSpecimen() {
        if(actionNotBusy()) {
            setSpecimenState(1);
        }
    }

    public void intake() {
        switch (intakeState) {
            case 1:
                actionBusy = true;
                claw.openClaw();
                intakeTimer.resetTimer();
                setTransferState(2);
                break;
            case 2:
                if(intakeTimer.getElapsedTimeSeconds() > 0.5) {
                    arm.transfer();
                    claw.transfer();
                    intake.transfer();
                    intake.outtake();
                    claw.openClaw();
                    extend.halfExtend();
                    intakeTimer.resetTimer();
                    setTransferState(3);
                }
                break;
            case 3:
                if (intakeTimer.getElapsedTimeSeconds() > 1) {
                    intake.pickup();
                    intake.intake();
                    intakeTimer.resetTimer();
                    setTransferState(4);
                }
                break;
            case 4:
                if (intakeTimer.getElapsedTimeSeconds() > 1.5) {
                    intake.outtake();
                    intakeTimer.resetTimer();
                    actionBusy = false;
                    setTransferState(-1);
                }
                break;
        }
    }

    public void setIntakeState(int x) {
        intakeState = x;
    }

    public void startIntake() {
        if (actionNotBusy()) {
            setIntakeState(1);
        }
    }

    public void park() {
        switch (parkState) {
            case 1:
                actionBusy = true;
                claw.openClaw();
                parkTimer.resetTimer();
                setParkState(2);
                break;
            case 2:
                if(parkTimer.getElapsedTimeSeconds() > 0.5) {
                    intake.transfer();
                    intake.stop();
                    elevatorSubsystem.toReset();
                    arm.transfer();
                    claw.transfer();
                    claw.openClaw();
                    extend.reset();
                    parkTimer.resetTimer();
                    actionBusy = false;
                    setTransferState(-1);
                }
                break;
        }
    }

    public void setParkState(int x) {
        parkState = x;
    }

    public void startPark() {
        if (actionNotBusy()) {
            setParkState(1);
        }
    }

    public boolean actionNotBusy() {
        return !actionBusy;
    }

    public boolean notBusy() {
        return (!follower.isBusy() && actionNotBusy());
    }

    public void telemetryUpdate() {
        telemetry.addData("X: ", follower.getPose().getX());
        telemetry.addData("Y: ", follower.getPose().getY());
        telemetry.addData("Heading: ", follower.getPose().getHeading());
        telemetry.addData("Action Busy?: ", actionBusy);
        telemetry.addData("Lift Pos", elevatorSubsystem.getPos());
        telemetry.addData("Extend Pos", extend.leftExtend.getPosition());
        telemetry.addData("Claw Grab State", claw.clawGrabState);
        telemetry.addData("Claw Pivot State", claw.sampleGrabState);
        telemetry.addData("Claw Pivot State", claw.wristState);
        telemetry.addData("Intake Pivot State", rotatorState);
        telemetry.addData("arm State", arm.state);
        telemetry.update();
    }
}