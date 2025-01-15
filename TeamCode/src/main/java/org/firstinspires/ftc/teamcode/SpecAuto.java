package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autoActions.commands.elevator.AutoElevatorDownCommand;
//import org.firstinspires.ftc.teamcode.autoActions.commands.elevator.AutoElevatorSpecDropCommand;
import org.firstinspires.ftc.teamcode.autoActions.commands.elevator.AutoElevatorUpSpecCommand;
import org.firstinspires.ftc.teamcode.autoActions.commands.extendo.ExtendoOut;
import org.firstinspires.ftc.teamcode.autoActions.commands.extendo.ExtendoReset;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.Intake;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.IntakeServoDeposit;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.IntakeStopperDown;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.Outtake;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.ClawClose;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.DepoArmReset;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.DepoWristReset;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.SpecDropOffFromBack;
//import org.firstinspires.ftc.teamcode.commands.elevator.AutoElevatorSpecDropCommand;


@Autonomous
public class SpecAuto extends BaseOpMode{
    Pose2d startingPose = new Pose2d(12,-63,180);
    @Override
    public void runOpMode(){
        super.initialize();
        MecanumDrive drive = new MecanumDrive(hardwareMap, startingPose);
        TrajectoryActionBuilder initialSpecDrop = drive.actionBuilder(startingPose)
                .splineTo(new Vector2d(12, -30), startingPose.heading).afterDisp(10, new ParallelAction(new AutoElevatorUpSpecCommand(elevatorSubsystem), new SpecDropOffFromBack(outtakePivotSubsystem, outtakeClawSubsystem)));

        TrajectoryActionBuilder moveToFirstGroundSpecAndDrop = initialSpecDrop.endTrajectory().fresh()
                .splineTo(new Vector2d(48, -24), 90).afterDisp(5, new ParallelAction(new Intake(intakeSubsystem), new ClawClose(outtakeClawSubsystem), new AutoElevatorDownCommand(elevatorSubsystem), new DepoArmReset(outtakePivotSubsystem), new DepoWristReset(outtakeClawSubsystem), new ExtendoOut(extendoSubsystem)))
                .splineTo(new Vector2d(55, -60), 45).afterDisp(20, new Outtake(intakeSubsystem));

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
