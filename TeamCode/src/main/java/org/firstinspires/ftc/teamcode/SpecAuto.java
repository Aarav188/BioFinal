package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autoActions.commands.elevator.AutoElevatorDownCommand;
import org.firstinspires.ftc.teamcode.autoActions.commands.elevator.AutoElevatorUpHighCommand;
import org.firstinspires.ftc.teamcode.autoActions.commands.elevator.AutoElevatorUpLowCommand;

@Autonomous
public class SpecAuto extends BaseOpMode{
    Pose2d startingPose = new Pose2d(0,0,0);
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, startingPose);
        Action traj1 = drive.actionBuilder(startingPose)
                .lineToX(60)
                .build();
        Action traj2 = drive.actionBuilder(startingPose)
                .lineToX(0)
                .build();



        waitForStart();

        if (isStopRequested()) return;



        Actions.runBlocking(
                new SequentialAction(
                    new ParallelAction(
                            traj1,
                            new AutoElevatorUpHighCommand(elevatorSubsystem)
                    ),
                    new ParallelAction(
                            traj2,
                            new AutoElevatorDownCommand(elevatorSubsystem)
                    )
                )
        );
    }
}
