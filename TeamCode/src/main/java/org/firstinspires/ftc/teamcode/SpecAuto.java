package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_AUTODROP;

import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autoActions.commands.elevator.AutoElevatorDownCommand;
//import org.firstinspires.ftc.teamcode.autoActions.commands.elevator.AutoElevatorSpecDropCommand;
import org.firstinspires.ftc.teamcode.autoActions.commands.elevator.AutoElevatorUpSpecCommand;
import org.firstinspires.ftc.teamcode.autoActions.commands.extendo.ExtendoOut;
import org.firstinspires.ftc.teamcode.autoActions.commands.extendo.ExtendoReset;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.Intake;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.IntakeServoDeposit;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.IntakeServosToGround;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.IntakeStopperDown;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.IntakeStopperUp;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.Outtake;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.StopIntake;
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
public class SpecAuto extends BaseOpMode{
    Pose2d startingPose = new Pose2d(8.5,-63,Math.toRadians(-90));
    @Override
    public void runOpMode(){
        super.initialize();
        MecanumDrive drive = new MecanumDrive(hardwareMap, startingPose);
        TrajectoryActionBuilder initialSpecDrop = drive.actionBuilder(startingPose)
               // .afterDisp(10, new ParallelAction(new AutoElevatorUpSpecCommand(elevatorSubsystem), new SpecDropOffFromBack(outtakePivotSubsystem, outtakeClawSubsystem)))
                .lineToYLinearHeading(-35, Math.toRadians(-90));

        TrajectoryActionBuilder moveToFirstGroundSpecAndDrop = initialSpecDrop.endTrajectory().fresh()
                .afterDisp(10, new SequentialAction(new ExtendoOut(extendoSubsystem)))
                .afterDisp(15, new ParallelAction(new IntakeServosToGround(intakeSubsystem), new Intake(intakeSubsystem)))
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(26, -46, Math.toRadians(50)), Math.toRadians(50))
                .splineToLinearHeading(new Pose2d(32, -40, Math.toRadians(55)), Math.toRadians(50)).afterDisp(20, new SequentialAction(new Outtake(intakeSubsystem)))
                .splineToLinearHeading(new Pose2d(32, -50, Math.toRadians(-70)), Math.toRadians(70)).afterDisp(5, new SequentialAction(new Intake(intakeSubsystem)))
                .splineToLinearHeading(new Pose2d(33, -46, Math.toRadians(50)), Math.toRadians(50))
                .splineToLinearHeading(new Pose2d(39, -40, Math.toRadians(50)), Math.toRadians(50)).afterDisp(20, new SequentialAction(new Outtake(intakeSubsystem)))
                .splineToLinearHeading(new Pose2d(39, -50, Math.toRadians(-70)), Math.toRadians(70)).afterDisp(5, new SequentialAction(new Intake(intakeSubsystem)))
                .splineToLinearHeading(new Pose2d(40, -46, Math.toRadians(50)), Math.toRadians(50))
                .splineToLinearHeading(new Pose2d(46, -40, Math.toRadians(50)), Math.toRadians(50)).afterDisp(5, new ParallelAction(new IntakeServoDeposit(intakeSubsystem), new ExtendoReset(extendoSubsystem), new DepoPickUpPos(outtakePivotSubsystem, outtakeClawSubsystem))).afterDisp(15, new SequentialAction(new IntakeStopperUp(intakeSubsystem))).afterDisp(20, new OuttakeLockSample(outtakeClawSubsystem)).afterDisp(23, new SequentialAction(new SpecArmPosPickup(outtakePivotSubsystem))).afterDisp(30, new SpecWristPosPickup(outtakeClawSubsystem))//, new SpecWristPosPickup(outtakeClawSubsystem)))
                .splineToLinearHeading(new Pose2d(40, -62, Math.toRadians(100)), Math.toRadians(-80))
                .waitSeconds(1)
                .afterDisp(0, new OuttakeUnlockSample(outtakeClawSubsystem))
                .splineToLinearHeading(new Pose2d(40, -68, Math.toRadians(100)), Math.toRadians(-80)).afterDisp(0, new ClawClose(outtakeClawSubsystem)).waitSeconds(1);
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
