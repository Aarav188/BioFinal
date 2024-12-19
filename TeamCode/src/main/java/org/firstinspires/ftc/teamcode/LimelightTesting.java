package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.hardware.limelightvision.Limelight3A;
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
import com.qualcomm.robotcore.util.SerialNumber;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Autonomous
public class LimelightTesting extends BaseOpMode{
    Pose2d startingPose = new Pose2d(0,0,0);
    Pose2d back = new Pose2d(10,0,0);
    Pose2d submer = new Pose2d(12, 0, 0);
    Limelight3A limelight;

    @Override
    public void runOpMode() throws InterruptedException {
        super.initialize();
        SerialNumber serialNumber = hardwareMap.get(SerialNumber.class, "limelight_serial");
        InetAddress ipAddress = null; // Replace with your Limelight's IP
        try {
            ipAddress = InetAddress.getByName("10.0.0.11");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        limelight = new Limelight3A(serialNumber, "Limelight", ipAddress);
        telemetry.setMsTransmissionInterval(11);

        limelight.pipelineSwitch(0); //change to correct one
        MecanumDrive drive = new MecanumDrive(hardwareMap, startingPose);
        Action traj1 = drive.actionBuilder(startingPose)
                .lineToX(26.5)
                .build();
        Action traj2 = drive.actionBuilder(back)
                .lineToX(-10)
                .build();

        limelight.start();

        waitForStart();



        if (isStopRequested()) return;
        LLResult result = limelight.getLatestResult();
        double xOffset = result.getTx(); // Horizontal offset
        double yOffset = result.getTy(); // Vertical offset
        double targetArea = result.getTa(); // Target area (size)

        telemetry.addData("X Offset", xOffset);
        telemetry.addData("Y Offset", yOffset);
        telemetry.addData("Target Area", targetArea);
        telemetry.update();
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
