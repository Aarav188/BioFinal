package org.firstinspires.ftc.teamcode;


import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.elevator.ElevatorIncrementCommand;
import org.firstinspires.ftc.teamcode.commands.elevator.HangCommand;
import org.firstinspires.ftc.teamcode.commands.elevator.HangIncrementCommand;
import org.firstinspires.ftc.teamcode.commands.elevator.HangReset;
import org.firstinspires.ftc.teamcode.commands.outtakeRotator.OuttakeLockSample;
import org.firstinspires.ftc.teamcode.commands.outtakeRotator.OuttakeUnlockSample;
import org.firstinspires.ftc.teamcode.commands.drivetrain.RobotCentricDriveCommand;
import org.firstinspires.ftc.teamcode.commands.elevator.AutoElevatorDownCommand;
import org.firstinspires.ftc.teamcode.commands.elevator.AutoElevatorSpecDropCommand;
import org.firstinspires.ftc.teamcode.commands.elevator.AutoElevatorUpHighCommand;
import org.firstinspires.ftc.teamcode.commands.elevator.AutoElevatorUpMiddleCommand;
import org.firstinspires.ftc.teamcode.commands.elevator.AutoElevatorUpSpecCommand;
import org.firstinspires.ftc.teamcode.commands.elevator.MaintainPosition;
import org.firstinspires.ftc.teamcode.commands.extendo.ExtendoOut;
import org.firstinspires.ftc.teamcode.commands.extendo.ExtendoReset;
import org.firstinspires.ftc.teamcode.commands.intake.Intake;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeServoDeposit;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeServosToGround;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopperDown;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopperUp;
import org.firstinspires.ftc.teamcode.commands.intake.Outtake;
import org.firstinspires.ftc.teamcode.commands.intake.StopIntake;
import org.firstinspires.ftc.teamcode.commands.outtakeRotator.ArmDropHighBucket;
import org.firstinspires.ftc.teamcode.commands.outtakeRotator.ClawClose;
import org.firstinspires.ftc.teamcode.commands.outtakeRotator.ClawOpen;
import org.firstinspires.ftc.teamcode.commands.outtakeRotator.DepoPickUpPos;
import org.firstinspires.ftc.teamcode.commands.outtakeRotator.DepoArmReset;
import org.firstinspires.ftc.teamcode.commands.outtakeRotator.DepoWristReset;
import org.firstinspires.ftc.teamcode.commands.outtakeRotator.SpecDropOffFromFront;
import org.firstinspires.ftc.teamcode.commands.outtakeRotator.SpecDropOffFromBack;
import org.firstinspires.ftc.teamcode.commands.outtakeRotator.SpecArmPosPickup;
import org.firstinspires.ftc.teamcode.commands.outtakeRotator.SpecWristPosPickup;


@TeleOp(group = "Competition")
public class TestingAndTuning extends BaseOpMode {
    @Override
    public void initialize() {
        super.initialize();
        RobotCentricDriveCommand normalDriveCommand = new RobotCentricDriveCommand(mecanumDriveSubsystem, driverPad::getLeftX, driverPad::getLeftY, driverPad::getRightX, -1);
        mecanumDriveSubsystem.setDefaultCommand(normalDriveCommand);


        new GamepadButton(driverPad, GamepadKeys.Button.A)
                .whenPressed(
                        new SequentialCommandGroup(
                                new IntakeStopperDown(intakeSubsystem).withTimeout(10),
                                new ExtendoOut(extendoSubsystem).withTimeout(150),
                                new IntakeServosToGround(intakeSubsystem).withTimeout(100),
                                new IntakeStopperDown(intakeSubsystem).withTimeout(100),
                                new OuttakeUnlockSample(outtakeClawSubsystem).withTimeout(100),
                                new Intake(intakeSubsystem)

                        )
                );
        telemetry.addData("Extendo",1);
        telemetry.update();

        new GamepadButton(driverPad, GamepadKeys.Button.B)
                .whenPressed(
                        new SequentialCommandGroup(
                                new Intake(intakeSubsystem),
                                new IntakeServoDeposit(intakeSubsystem).withTimeout(100),
                                new ExtendoReset(extendoSubsystem).withTimeout(100),
                                new DepoPickUpPos(outtakePivotSubsystem, outtakeClawSubsystem).withTimeout(200),
                                new WaitCommand(400),
                                new IntakeStopperUp(intakeSubsystem).withTimeout(400),
                                new OuttakeLockSample(outtakeClawSubsystem).withTimeout(100),
                                new StopIntake(intakeSubsystem).withTimeout(10)


                        )
                );
        telemetry.addData("Extendo",0);
        telemetry.update();

        new GamepadButton(driverPad, GamepadKeys.Button.X)
                .whenPressed(
                        new SequentialCommandGroup(
                                new ParallelCommandGroup(
                                        new DepoWristReset(outtakeClawSubsystem).withTimeout(2000),
                                        new DepoArmReset(outtakePivotSubsystem).withTimeout(2000)

                                ),
                                new OuttakeUnlockSample(outtakeClawSubsystem).withTimeout(100),
                                new WaitCommand(500),
                                new AutoElevatorDownCommand(elevatorSubsystem)

                        )
                );

        new GamepadButton(operatorPad, GamepadKeys.Button.B).whenPressed(
                new OuttakeUnlockSample(outtakeClawSubsystem).withTimeout(100)
        );

        new GamepadButton(operatorPad, GamepadKeys.Button.A).whenPressed(
                new OuttakeLockSample(outtakeClawSubsystem).withTimeout(100)
        );



        new GamepadButton(driverPad, GamepadKeys.Button.Y).toggleWhenPressed(
                new HangCommand(hangSubsystem), new HangReset(hangSubsystem)
        );

        new GamepadButton(driverPad, GamepadKeys.Button.BACK).whenPressed(
                new OuttakeUnlockSample(outtakeClawSubsystem).withTimeout(100)
        );
        new GamepadTrigger(driverPad, GamepadKeys.Trigger.RIGHT_TRIGGER)
                .whenPressed(
                        new SequentialCommandGroup(
                                new Outtake(intakeSubsystem),
                                new MaintainPosition(elevatorSubsystem)
                        )

                ).whenReleased(
                        new SequentialCommandGroup(
                                new StopIntake(intakeSubsystem)
                        )
                );



        // Increment/Decrement
        new GamepadTrigger(driverPad, GamepadKeys.Trigger.LEFT_TRIGGER)
                .whenPressed(
                        new SequentialCommandGroup(
                                new IntakeStopperDown(intakeSubsystem).withTimeout(100),
                                new Intake(intakeSubsystem),
                                new MaintainPosition(elevatorSubsystem)
                        )

                ).whenReleased(
                        new SequentialCommandGroup(
                                new StopIntake(intakeSubsystem)
                        )
                );

        new GamepadButton(driverPad, GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(
                        new SequentialCommandGroup(
                                new ClawClose(outtakeClawSubsystem).withTimeout(5),
                                new MaintainPosition(elevatorSubsystem)
                        )
                );
        //   clawOpen = false;

        // clawOpen = true;
        new GamepadButton(driverPad, GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(
                        new SequentialCommandGroup(
                                new ClawOpen(outtakeClawSubsystem).withTimeout(100),
                                new ParallelCommandGroup(
                                        new OuttakeUnlockSample(outtakeClawSubsystem).withTimeout(100),
                                        new MaintainPosition(elevatorSubsystem)
                                ))
                );
        new GamepadButton(driverPad, GamepadKeys.Button.RIGHT_STICK_BUTTON)
                .whenPressed(
                        new SequentialCommandGroup(
                                new AutoElevatorUpSpecCommand(elevatorSubsystem).withTimeout(100),
                                new SpecDropOffFromBack(outtakePivotSubsystem, outtakeClawSubsystem).withTimeout(200),
                                new MaintainPosition(elevatorSubsystem)
                        )

                );

        // spec pickup
        new GamepadButton(driverPad, GamepadKeys.Button.DPAD_RIGHT).
                whenPressed(
                        new ElevatorIncrementCommand(elevatorSubsystem, 100)
                );
        new GamepadButton(driverPad, GamepadKeys.Button.DPAD_LEFT).
                whenPressed(
                        new ElevatorIncrementCommand(elevatorSubsystem, -100)
                );
        new GamepadButton(driverPad, GamepadKeys.Button.DPAD_UP).
                whenPressed(
                        new HangIncrementCommand(hangSubsystem, 1000)
                );
        new GamepadButton(driverPad, GamepadKeys.Button.DPAD_DOWN).
                whenPressed(
                        new HangIncrementCommand(hangSubsystem, -1000)
                );
        new GamepadButton(driverPad, GamepadKeys.Button.BACK)
                .whenPressed(
                        new HangIncrementCommand(hangSubsystem, 5000)
                );



    }
}