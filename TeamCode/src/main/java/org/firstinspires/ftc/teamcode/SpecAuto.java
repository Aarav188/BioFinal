package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autoActions.commands.elevator.AutoElevatorDownCommand;
//import org.firstinspires.ftc.teamcode.autoActions.commands.elevator.AutoElevatorSpecDropCommand;
import org.firstinspires.ftc.teamcode.autoActions.commands.elevator.AutoElevatorDeliverCommand;
import org.firstinspires.ftc.teamcode.autoActions.commands.elevator.AutoElevatorUpSpecCommand;
import org.firstinspires.ftc.teamcode.autoActions.commands.extendo.ExtendoOut;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.IntakeServoDeposit;
import org.firstinspires.ftc.teamcode.autoActions.commands.intake.IntakeServosToGround;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.ClawOpen;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.DepoReset;
import org.firstinspires.ftc.teamcode.autoActions.commands.outtakeRotator.SpecimanDropoff;
//import org.firstinspires.ftc.teamcode.commands.elevator.AutoElevatorSpecDropCommand;


@Autonomous
public class SpecAuto extends BaseOpMode{
    Pose2d startingPose = new Pose2d(0,0,0);
    Pose2d back = new Pose2d(10,0,0);
    Pose2d submer = new Pose2d(12, 0, 0);
    @Override
    public void runOpMode() throws InterruptedException {
        super.initialize();
        MecanumDrive drive = new MecanumDrive(hardwareMap, startingPose);
        Action traj1 = drive.actionBuilder(startingPose)
                .lineToX(26.5)
                .build();
        Action traj2 = drive.actionBuilder(back)
                .lineToX(-10)
                .build();



        waitForStart();



        if (isStopRequested()) return;


        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0, 0, 0))
                        .afterDisp(5, new AutoElevatorUpSpecCommand(elevatorSubsystem))
                        .afterDisp(8, new SpecimanDropoff(outtakePivotSubsystem, outtakeClawSubsystem))
                        //.afterDisp(26,new ClawOpen(outtakeClawSubsystem))
                        .lineToX(31.5)
                        .stopAndAdd(new AutoElevatorDeliverCommand(elevatorSubsystem))
                        .stopAndAdd(new ClawOpen(outtakeClawSubsystem))
                        .stopAndAdd(new DepoReset(outtakePivotSubsystem, outtakeClawSubsystem))
                        .stopAndAdd(new IntakeServoDeposit(intakeSubsystem))
                        .lineToX(10)
                        .stopAndAdd(new AutoElevatorDownCommand(elevatorSubsystem))
                        .stopAndAdd(new ExtendoOut(extendoSubsystem))
                        .lineToX(9)
                        .stopAndAdd(new IntakeServosToGround(intakeSubsystem))
//                        .afterDisp(2, new ClawOpen(outtakeClawSubsystem))
//                        .afterDisp(3, new DepoReset(outtakePivotSubsystem, outtakeClawSubsystem))
//                        .afterDisp(6, new AutoElevatorDownCommand(elevatorSubsystem))
//                           .splineTo(new Vector2d(, -30), Math.PI / 2)
                        //.lineToXConstantHeading(-8)
//                        .splineTo(new Vector2d(34, -30), 125)
//                        .splineTo(new Vector2d(32, -42), 125)
//                        .splineTo(new Vector2d(-5, -40), 125)
                        .build()
        );


    }
}
