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
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.StopIntake;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.ArmDropHighBucket;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.ClawClose;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.DepoArmReset;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.DepoPickUpPos;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.DepoWristReset;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.OuttakeLockSample;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.OuttakeUnlockSample;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.SpecArmPosPickup;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.SpecWristPosPickup;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.SpecDropOffFromBack;
import org.firstinspires.ftc.teamcode.commands.elevator.AutoElevatorSpecDropCommand;
import org.firstinspires.ftc.teamcode.autoActions.commands.elevator.MaintainPosition;



@Autonomous
public class BucketAuto extends BaseOpMode{
    Pose2d startingPose = new Pose2d(-12,-63,Math.toRadians(0));
    @Override
    public void runOpMode(){
        super.initialize();
        MecanumDrive drive = new MecanumDrive(hardwareMap, startingPose);
        TrajectoryActionBuilder initialSpecDrop = drive.actionBuilder(startingPose)
                .afterDisp(10, new ParallelAction(new AutoElevatorUpHighCommand(elevatorSubsystem), new ArmDropHighBucket(outtakePivotSubsystem, outtakeClawSubsystem)))
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-60,-54, Math.toRadians(65)), Math.toRadians(180)) //drop first sample
                .stopAndAdd(new OuttakeUnlockSample(outtakeClawSubsystem));

        TrajectoryActionBuilder moveToFirstGroundSpecAndDrop = initialSpecDrop.endTrajectory().fresh()
                .setTangent(Math.toRadians(50))
                .afterDisp(0,  new ParallelAction(new IntakeServoDeposit(intakeSubsystem)))
                .afterDisp(3, new ParallelAction(new DepoArmReset(outtakePivotSubsystem), new DepoWristReset(outtakeClawSubsystem)))
                .afterDisp(6, new AutoElevatorDownCommand(elevatorSubsystem))
                .afterDisp(7, new ExtendoOut(extendoSubsystem))
                .afterDisp(11, new ParallelAction(new IntakeServosToGround(intakeSubsystem)))
                .afterDisp(15, new Intake(intakeSubsystem))
                .splineToLinearHeading(new Pose2d(-49, -46, Math.toRadians(80)), Math.toRadians(80)) //pickup second sample
                .waitSeconds(0.5)
              .stopAndAdd(new ParallelAction(new ExtendoReset(extendoSubsystem), new IntakeServoDeposit(intakeSubsystem)))
                .waitSeconds(0.5)
                .stopAndAdd(new IntakeStopperUp(intakeSubsystem))
                .stopAndAdd(new ParallelAction(new OuttakeLockSample(outtakeClawSubsystem), new AutoElevatorUpHighCommand(elevatorSubsystem), new ArmDropHighBucket(outtakePivotSubsystem, outtakeClawSubsystem)))
                .setTangent(Math.toRadians(-130))
                .splineToLinearHeading(new Pose2d(-53,-55, Math.toRadians(65)), Math.toRadians(180)) //drop off second sample
                .setTangent(45)
                .afterDisp(0,  new ParallelAction(new IntakeServoDeposit(intakeSubsystem)))
                .afterDisp(3, new ParallelAction(new DepoArmReset(outtakePivotSubsystem), new DepoWristReset(outtakeClawSubsystem), new ExtendoOut(extendoSubsystem)))
                .afterDisp(6, new AutoElevatorDownCommand(elevatorSubsystem))
                .afterDisp(7, new ExtendoOut(extendoSubsystem))
                .afterDisp(11, new ParallelAction(new IntakeServosToGround(intakeSubsystem)))
                .afterDisp(15, new Intake(intakeSubsystem))
                .splineToLinearHeading(new Pose2d(-55, -40, Math.toRadians(90)), Math.toRadians(45)) //pickup 3rd sample

                .stopAndAdd(new ParallelAction(new ExtendoReset(extendoSubsystem), new IntakeServoDeposit(intakeSubsystem)))
                .waitSeconds(0.5)
                .stopAndAdd(new IntakeStopperUp(intakeSubsystem))
                .waitSeconds(0.5)
                .setTangent(-90)
                .stopAndAdd(new ParallelAction(new OuttakeLockSample(outtakeClawSubsystem), new AutoElevatorUpHighCommand(elevatorSubsystem), new ArmDropHighBucket(outtakePivotSubsystem, outtakeClawSubsystem)))
                .setTangent(30)
                .afterDisp(3, new ParallelAction(new DepoArmReset(outtakePivotSubsystem), new DepoWristReset(outtakeClawSubsystem), new ExtendoOut(extendoSubsystem)))
                .afterDisp(10, new ParallelAction(new AutoElevatorDownCommand(elevatorSubsystem), new IntakeServosToGround(intakeSubsystem)))
                .afterDisp(15, new Intake(intakeSubsystem))
                .splineToLinearHeading(new Pose2d(-54, -50, Math.toRadians(90)), Math.toRadians(30)) //pickup 4th sample
                .setTangent(-60)
                .afterDisp(0, new ParallelAction(new ExtendoReset(extendoSubsystem), new IntakeServoDeposit(intakeSubsystem)))
                .afterDisp(5, new IntakeStopperUp(intakeSubsystem))
                .afterDisp(6, new ParallelAction(new OuttakeLockSample(outtakeClawSubsystem), new AutoElevatorUpHighCommand(elevatorSubsystem), new ArmDropHighBucket(outtakePivotSubsystem, outtakeClawSubsystem)))
                .splineToLinearHeading(new Pose2d(-53,-55, Math.toRadians(65)), Math.toRadians(180)) //drop 4th sample
                .afterDisp(0, new ParallelAction(new DepoArmReset(outtakePivotSubsystem), new DepoWristReset(outtakeClawSubsystem), new ExtendoOut(extendoSubsystem)))
                .afterDisp(5, new ParallelAction(new AutoElevatorDownCommand(elevatorSubsystem)))
//


                ;
   //     TrajectoryActionBuilder moveToSecondGroundSpecAndDrop = moveToFirstGroundSpecAndDrop.endTrajectory().fresh();

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
                        new OuttakeLockSample(outtakeClawSubsystem)
                )

        );

        waitForStart();



        if (isStopRequested()) return;


        Actions.runBlocking(
                new SequentialAction(
                        initialSpecDrop.build(),
                        new ParallelAction(
                                new OuttakeUnlockSample(outtakeClawSubsystem),
                                new MaintainPosition(elevatorSubsystem)
                        ),
                        moveToFirstGroundSpecAndDrop.build()
                )
        );
    }
}
