package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_AUTODROP;

import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autoActions.commands.elevator.AutoElevatorDownCommand;
import org.firstinspires.ftc.teamcode.autoActions.commands.elevator.AutoElevatorDeliverCommand;
import org.firstinspires.ftc.teamcode.autoActions.commands.elevator.AutoElevatorUpSpecCommand;
import org.firstinspires.ftc.teamcode.autoActions.commands.elevator.ElevatorIncrementCommand;
import org.firstinspires.ftc.teamcode.autoActions.commands.extendo.ExtendoOut;
import org.firstinspires.ftc.teamcode.autoActions.commands.extendo.ExtendoReset;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.Intake;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.IntakeServoDeposit;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.IntakeServosToGround;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.IntakeStopperDown;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.IntakeStopperUp;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.Outtake;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.StopIntake;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.AutoDepoWristReset;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.ClawClose;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.ClawOpen;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.DepoArmReset;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.DepoPickUpPos;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.DepoWristReset;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.OuttakeLockSample;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.OuttakeUnlockSample;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.SpecArmPosPickup;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.SpecDropOffFromFront;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.SpecWristPosPickup;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.SpecDropOffFromBack;
import org.firstinspires.ftc.teamcode.commands.elevator.AutoElevatorSpecDropCommand;
import org.firstinspires.ftc.teamcode.autoActions.commands.elevator.MaintainPosition;



@Autonomous
public class SpecAuto extends BaseOpMode{
    Pose2d startingPose = new Pose2d(8.5,-63,Math.toRadians(-90));
    @Override
    public void runOpMode(){
        super.initialize();
        MecanumDrive drive = new MecanumDrive(hardwareMap, startingPose);
        TrajectoryActionBuilder initialSpecDrop = drive.actionBuilder(startingPose)
               // .afterDisp(10, new ParallelAction(new AutoElevatorUpSpecCommand(elevatorSubsystem), new SpecDropOffFromBack(outtakePivotSubsystem, outtakeClawSubsystem)))
                .afterDisp(0, new SequentialAction(
                        new AutoElevatorDeliverCommand(elevatorSubsystem),
                        new SpecDropOffFromBack(outtakePivotSubsystem, outtakeClawSubsystem),
                        new MaintainPosition(elevatorSubsystem)
                ))
                .lineToYLinearHeading(-35, Math.toRadians(-90))
                .stopAndAdd(new SequentialAction(new ElevatorIncrementCommand(elevatorSubsystem, -500), new ClawOpen(outtakeClawSubsystem)))
                .waitSeconds(1);

        TrajectoryActionBuilder moveToFirstGroundSpecAndDrop = initialSpecDrop.endTrajectory().fresh()
                .afterDisp(0, new ParallelAction(new DepoArmReset(outtakePivotSubsystem), new AutoDepoWristReset(outtakeClawSubsystem)))
                .afterDisp(5, new SequentialAction(new AutoElevatorDownCommand(elevatorSubsystem)))
                .afterDisp(10, new SequentialAction(new ExtendoOut(extendoSubsystem)) )
                .afterDisp(15, new ParallelAction(new IntakeServosToGround(intakeSubsystem), new Intake(intakeSubsystem)))
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(28, -50, Math.toRadians(50)), Math.toRadians(50))
                .splineToLinearHeading(new Pose2d(31, -46, Math.toRadians(55)), Math.toRadians(50)).afterDisp(20, new SequentialAction(new Outtake(intakeSubsystem)))
                .splineToLinearHeading(new Pose2d(40, -54, Math.toRadians(-70)), Math.toRadians(70)).afterDisp(5, new SequentialAction(new Intake(intakeSubsystem)))
                .splineToLinearHeading(new Pose2d(36, -50, Math.toRadians(50)), Math.toRadians(50))
                .splineToLinearHeading(new Pose2d(42, -46, Math.toRadians(50)), Math.toRadians(50)).afterDisp(20, new SequentialAction(new Outtake(intakeSubsystem)))
                .splineToLinearHeading(new Pose2d(46, -50, Math.toRadians(-70)), Math.toRadians(70)).afterDisp(5, new SequentialAction(new Intake(intakeSubsystem)))
                .splineToLinearHeading(new Pose2d(48, -46, Math.toRadians(50)), Math.toRadians(50))
                .splineToLinearHeading(new Pose2d(54, -50, Math.toRadians(50)), Math.toRadians(50)).afterDisp(5, new ParallelAction(new IntakeServoDeposit(intakeSubsystem), new ExtendoReset(extendoSubsystem), new DepoPickUpPos(outtakePivotSubsystem, outtakeClawSubsystem))).afterDisp(15, new SequentialAction(new IntakeStopperUp(intakeSubsystem))).afterDisp(20, new OuttakeLockSample(outtakeClawSubsystem)).afterDisp(23, new SequentialAction(new SpecArmPosPickup(outtakePivotSubsystem))).afterDisp(30, new SpecWristPosPickup(outtakeClawSubsystem))//, new SpecWristPosPickup(outtakeClawSubsystem)))
                .splineToLinearHeading(new Pose2d(45, -62, Math.toRadians(100)), Math.toRadians(-100)).afterDisp(0, new SequentialAction(new ClawClose(outtakeClawSubsystem), new OuttakeUnlockSample(outtakeClawSubsystem)))
                .waitSeconds(1)
                .setTangent(Math.toRadians(90))
                .afterDisp(0, new SequentialAction(new AutoElevatorDeliverCommand(elevatorSubsystem), new SpecDropOffFromFront(outtakePivotSubsystem, outtakeClawSubsystem)))
                .splineToLinearHeading(new Pose2d(55, -74, Math.toRadians(100)), Math.toRadians(-100))
                .splineToLinearHeading(new Pose2d(12.5, -30, Math.toRadians(100)), Math.toRadians(-100))
                .waitSeconds(1)
                .setTangent(Math.toRadians(90))
                .afterDisp(0, new OuttakeUnlockSample(outtakeClawSubsystem))
                .splineToLinearHeading(new Pose2d(55, -74, Math.toRadians(100)), Math.toRadians(-100)).afterDisp(0, new ClawClose(outtakeClawSubsystem)).waitSeconds(1)
                .splineToLinearHeading(new Pose2d(12.5, -35, Math.toRadians(100)), Math.toRadians(100));
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
                    new IntakeStopperDown(intakeSubsystem),
                    new OuttakeUnlockSample(outtakeClawSubsystem)
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
