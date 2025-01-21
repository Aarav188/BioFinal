package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_AUTODROP;

import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autoActions.commands.elevator.AutoElevatorDownCommand;
//import org.firstinspires.ftc.teamcode.autoActions.commands.elevator.AutoElevatorSpecDropCommand;
import org.firstinspires.ftc.teamcode.autoActions.commands.elevator.AutoElevatorUpHighCommand;
import org.firstinspires.ftc.teamcode.autoActions.commands.elevator.AutoElevatorUpSpecCommand;
import org.firstinspires.ftc.teamcode.autoActions.commands.extendo.ExtendoOut;
import org.firstinspires.ftc.teamcode.autoActions.commands.extendo.ExtendoReset;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.Intake;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.IntakeServoDeposit;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.IntakeServosToGround;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.IntakeStopperDown;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.IntakeStopperUp;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.Outtake;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.ClawClose;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.DepoArmReset;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.DepoPickUpPos;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.DepoWristReset;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.OuttakeLockSample;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.OuttakeUnlockSample;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.SpecArmPosPickup;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.SpecDropOffFromBack;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.SpecWristPosPickup;


@Autonomous
public class BucketAuto extends BaseOpMode{
    Pose2d startingPose = new Pose2d(-12,-63,Math.toRadians(0));
    @Override
    public void runOpMode(){
        super.initialize();
        MecanumDrive drive = new MecanumDrive(hardwareMap, startingPose);
        TrajectoryActionBuilder initialSpecDrop = drive.actionBuilder(startingPose)
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-60,-55, Math.toRadians(65)), Math.toRadians(180)).afterDisp(10, new SequentialAction(new AutoElevatorDownCommand(elevatorSubsystem)))
                .afterDisp(0, new SequentialAction(new ExtendoOut(extendoSubsystem)))
                .afterDisp(5, new ParallelAction(new IntakeServosToGround(intakeSubsystem), new Intake(intakeSubsystem)))
                .setTangent(Math.toRadians(50))
                .splineToLinearHeading(new Pose2d(-55, -41, Math.toRadians(70)), Math.toRadians(60)).afterDisp(0,new SequentialAction(new DepoPickUpPos(outtakePivotSubsystem, outtakeClawSubsystem)))
                .setTangent(Math.toRadians(-130))
                .splineToLinearHeading(new Pose2d(-60,-55, Math.toRadians(65)), Math.toRadians(180)).afterDisp(5,new ParallelAction(new IntakeServoDeposit(intakeSubsystem), new ExtendoReset(extendoSubsystem), new DepoPickUpPos(outtakePivotSubsystem, outtakeClawSubsystem))).afterDisp(15, new SequentialAction(new IntakeStopperUp(intakeSubsystem))).afterDisp(20, new OuttakeLockSample(outtakeClawSubsystem))
                .waitSeconds(0.5)
                // elevator up
                .afterDisp(0, new OuttakeUnlockSample(outtakeClawSubsystem))
                .setTangent(45).afterDisp(0,new SequentialAction(new ExtendoOut(extendoSubsystem)))
                .splineToLinearHeading(new Pose2d(-59, -41, Math.toRadians(90)), Math.toRadians(45))
                .setTangent(-90)
                .splineToLinearHeading(new Pose2d(-60,-55, Math.toRadians(65)), Math.toRadians(180))
                .setTangent(30)
                .splineToLinearHeading(new Pose2d(-63, -41, Math.toRadians(120)), Math.toRadians(30))
                .setTangent(-130)
                .splineToLinearHeading(new Pose2d(-60,-55, Math.toRadians(65)), Math.toRadians(180));

        TrajectoryActionBuilder moveToFirstGroundSpecAndDrop = initialSpecDrop.endTrajectory().fresh()
                .splineToLinearHeading(new Pose2d(30, -18, Math.toRadians(90)),Math.toRadians(90));
        //    .splineToLinearHeading(new Pose2d(53, -50, Math.toRadians(70)), Math.toRadians(70)).afterDisp(20, new Outtake(intakeSubsystem));

        TrajectoryActionBuilder moveToSecondGroundSpecAndDrop = moveToFirstGroundSpecAndDrop.endTrajectory().fresh();

        //actions on init put in here
        Actions.runBlocking(
                new SequentialAction(
                        new AutoElevatorDownCommand(elevatorSubsystem),
                        new ClawClose(outtakeClawSubsystem),
                        new DepoWristReset(outtakeClawSubsystem),
                        new DepoArmReset(outtakePivotSubsystem),
                        new ExtendoReset(extendoSubsystem),
                        new IntakeServoDeposit(intakeSubsystem),
                        new IntakeStopperDown(intakeSubsystem)
                )

        );

        waitForStart();



        if (isStopRequested()) return;


        Actions.runBlocking(
                new SequentialAction(
                        initialSpecDrop.build(),
                        // claw open
                        moveToFirstGroundSpecAndDrop.build()
                )
        );
    }
}
