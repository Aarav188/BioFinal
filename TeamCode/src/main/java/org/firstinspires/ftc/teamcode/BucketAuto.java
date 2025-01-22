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
                .afterDisp(10, new SequentialAction(
                        new AutoElevatorUpHighCommand(elevatorSubsystem),
                        new ArmDropHighBucket(outtakePivotSubsystem, outtakeClawSubsystem)))
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-63,-55, Math.toRadians(65)), Math.toRadians(180)) //drop first sample
                .waitSeconds(1.5);

        TrajectoryActionBuilder moveToFirstGroundSpecAndDrop = initialSpecDrop.endTrajectory().fresh()
                .setTangent(Math.toRadians(50))
                .splineToLinearHeading(new Pose2d(-53, -40, Math.toRadians(70)), Math.toRadians(60)) //pickup second sample
                .setTangent(Math.toRadians(-130))
                .splineToLinearHeading(new Pose2d(-60,-55, Math.toRadians(65)), Math.toRadians(180)) //drop off second sample
                .setTangent(45)
                .splineToLinearHeading(new Pose2d(-57, -40, Math.toRadians(90)), Math.toRadians(45)) //pickup 3rd sample
                .setTangent(-90)
                .splineToLinearHeading(new Pose2d(-60,-55, Math.toRadians(65)), Math.toRadians(180)) //drop 3rd sample
                .setTangent(30)
                .splineToLinearHeading(new Pose2d(-61, -40, Math.toRadians(90)), Math.toRadians(30)) //pickup 4th sample
              ;
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
