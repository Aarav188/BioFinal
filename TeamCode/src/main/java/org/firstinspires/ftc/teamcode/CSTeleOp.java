package org.firstinspires.ftc.teamcode;


import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.drivetrain.RobotCentricDriveCommand;
import org.firstinspires.ftc.teamcode.commands.elevator.AutoElevatorDownCommand;
import org.firstinspires.ftc.teamcode.commands.elevator.AutoElevatorSpecDropCommand;
import org.firstinspires.ftc.teamcode.commands.elevator.AutoElevatorUpHighCommand;
import org.firstinspires.ftc.teamcode.commands.elevator.AutoElevatorUpLowCommand;
import org.firstinspires.ftc.teamcode.commands.elevator.AutoElevatorUpMiddleCommand;
import org.firstinspires.ftc.teamcode.commands.elevator.AutoElevatorUpSpecCommand;
import org.firstinspires.ftc.teamcode.commands.elevator.ElevatorIncrementCommand;
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
import org.firstinspires.ftc.teamcode.commands.outtakeRotator.ArmBucketDropPosMid;
import org.firstinspires.ftc.teamcode.commands.outtakeRotator.ArmDropPos;
import org.firstinspires.ftc.teamcode.commands.outtakeRotator.ClawClose;
import org.firstinspires.ftc.teamcode.commands.outtakeRotator.ClawOpen;
import org.firstinspires.ftc.teamcode.commands.outtakeRotator.DepoPickUpPos;
import org.firstinspires.ftc.teamcode.commands.outtakeRotator.DepoReset;
import org.firstinspires.ftc.teamcode.commands.outtakeRotator.SpecimanDropoff;
import org.firstinspires.ftc.teamcode.commands.outtakeRotator.SpecimanPickup;


@TeleOp(group = "Competition")
public class CSTeleOp extends BaseOpMode {
    private boolean toggleBAction = false;
    @Override
    public void initialize() {
        super.initialize();
        new ClawOpen(outtakeClawSubsystem);
        new IntakeServoDeposit(intakeSubsystem);
        new DepoReset(outtakePivotSubsystem, outtakeClawSubsystem);
        new ExtendoReset(extendoSubsystem);

//        RobotCentricDriveCommand normalDriveCommand = new RobotCentricDriveCommand(mecanumDriveSubsystem, driverPad::getLeftX, driverPad::getLeftY, driverPad::getRightX, 0.5);
//        RobotCentricDriveCommand fastDriveCommand = new RobotCentricDriveCommand(mecanumDriveSubsystem, driverPad::getLeftX, driverPad::getLeftY, driverPad::getRightX, 1.0);

        RobotCentricDriveCommand normalDriveCommand = new RobotCentricDriveCommand(mecanumDriveSubsystem, driverPad::getLeftX, driverPad::getLeftY, driverPad::getRightX, -.8);
        mecanumDriveSubsystem.setDefaultCommand(normalDriveCommand);
//        intakeSubsystem.setDefaultCommand(new IntakeJoystickCommand(intakeSubsystem, operatorPad::getRightY, operatorPad::getLeftY));

        //This is resetting. TODO: Feed position after auto here.
        // SequentialCommandGroup resetcommand = new SequentialCommandGroup();


        //schedule(normalDriveCommand);
//        (new GamepadTrigger(driverPad, GamepadKeys.Trigger.LEFT_TRIGGER)).whileHeld(fastDriveCommand);
//        (new GamepadTrigger(driverPad, GamepadKeys.Trigger.RIGHT_TRIGGER)).whileHeld(slowDriveCommand);


        // Elevator Controls
        // Set Positions


        new GamepadButton(driverPad, GamepadKeys.Button.A)
                .whenPressed(
                        new SequentialCommandGroup(
                                new ExtendoOut(extendoSubsystem).withTimeout(150),
                                new IntakeServosToGround(intakeSubsystem).withTimeout(100),
                                new IntakeStopperDown(intakeSubsystem).withTimeout(100),
                                new Intake(intakeSubsystem)

                        )
                );
        telemetry.addData("Extendo",1);
        telemetry.update();

        Command oldTransfer = new SequentialCommandGroup(
                new Intake(intakeSubsystem),
                new IntakeServoDeposit(intakeSubsystem).withTimeout(100),
                new ExtendoReset(extendoSubsystem).withTimeout(100),
                new WaitCommand(600),
                new IntakeStopperUp(intakeSubsystem).withTimeout(100),
                new IntakeServoDeposit(intakeSubsystem).withTimeout(200),
                new ExtendoReset(extendoSubsystem).withTimeout(500),
                new StopIntake(intakeSubsystem).withTimeout(200),
                new DepoReset(outtakePivotSubsystem, outtakeClawSubsystem).withTimeout(200),
                new ClawOpen(outtakeClawSubsystem).withTimeout(100),
                new WaitCommand(500), //TODO tune this wait
                new DepoPickUpPos(outtakePivotSubsystem, outtakeClawSubsystem).withTimeout(200),
                new ClawClose(outtakeClawSubsystem).withTimeout(100),
                new AutoElevatorUpMiddleCommand(elevatorSubsystem).withTimeout(300),
                new ArmBucketDropPosMid(outtakePivotSubsystem)
        );

//        Command transfer = new SequentialCommandGroup(
//                new Intake(intakeSubsystem),
//                new ParallelCommandGroup(
//                        new IntakeServoDeposit(intakeSubsystem).withTimeout(100),
//                        new ExtendoReset(extendoSubsystem).withTimeout(100)
//                ),
//                new WaitCommand(600),
//                new ParallelCommandGroup(
//                        new IntakeStopperUp(intakeSubsystem).withTimeout(100),
//                        new IntakeServoDeposit(intakeSubsystem).withTimeout(200),
//                        new ExtendoReset(extendoSubsystem).withTimeout(500),
//                        new DepoReset(outtakePivotSubsystem, outtakeClawSubsystem).withTimeout(200)
//                        //new ClawOpen(outtakeClawSubsystem).withTimeout(100)
//                ),
//
//                new StopIntake(intakeSubsystem).withTimeout(200),
//                new WaitCommand(500), //TODO tune this wait
//                new DepoPickUpPos(outtakePivotSubsystem, outtakeClawSubsystem).withTimeout(200),
//                new ClawClose(outtakeClawSubsystem).withTimeout(100),
//                new AutoElevatorUpMiddleCommand(elevatorSubsystem).withTimeout(300),
//                new ArmBucketDropPosMid(outtakePivotSubsystem)
//        );

//        Command reset = new SequentialCommandGroup(
//                new DepoReset(outtakePivotSubsystem, outtakeClawSubsystem).withTimeout(300),
//                new AutoElevatorDownCommand(elevatorSubsystem).withTimeout(200),
//                new ExtendoReset(extendoSubsystem).withTimeout(10),
//                new IntakeServoDeposit(intakeSubsystem).withTimeout(10)
//        );
//
//        ConditionalCommand toggleCommand = new ConditionalCommand(
//                transfer, // Command when toggleBAction is true
//                reset,    // Command when toggleBAction is false
//                () -> toggleBAction // Condition to toggle between commands
//        );

//// Bind the command to the B button
//        new GamepadButton(driverPad, GamepadKeys.Button.B)
//                .whenPressed(toggleCommand, toggleBAction = !toggleBAction);
        new GamepadButton(driverPad, GamepadKeys.Button.B)
                .whenPressed(
                        new SequentialCommandGroup(
                                new Intake(intakeSubsystem),
                                new IntakeServoDeposit(intakeSubsystem).withTimeout(100),
                                new ExtendoReset(extendoSubsystem).withTimeout(100),
                                new WaitCommand(600),
                                new IntakeStopperUp(intakeSubsystem).withTimeout(100),
                                new IntakeServoDeposit(intakeSubsystem).withTimeout(200),
                                new ExtendoReset(extendoSubsystem).withTimeout(500),
                                new StopIntake(intakeSubsystem).withTimeout(200),
                                new DepoReset(outtakePivotSubsystem, outtakeClawSubsystem).withTimeout(200),
                                new ClawOpen(outtakeClawSubsystem).withTimeout(100),
                                new WaitCommand(500), //TODO tune this wait
                                new DepoPickUpPos(outtakePivotSubsystem, outtakeClawSubsystem).withTimeout(200),
                                new ClawClose(outtakeClawSubsystem).withTimeout(100)
//                                new AutoElevatorUpHighCommand(elevatorSubsystem).withTimeout(300),
//                                new ArmDropPos(outtakePivotSubsystem, outtakeClawSubsystem).withTimeout(100)

                        )
                );
        telemetry.addData("Extendo",0);
        telemetry.update();

//        boolean intakeServosUp = true;
//            new GamepadButton(driverPad, GamepadKeys.Button.X)
//                    .whenPressed(
//                            new SequentialCommandGroup(
//                                    new IntakeServosToGround(intakeSubsystem).withTimeout(100)
//                            )
//                    );
//            intakeServosUp = false;
//
//
//            new GamepadButton(driverPad, GamepadKeys.Button.Y)
//                    .whenPressed(
//                            new SequentialCommandGroup(
//                                    new IntakeServoDeposit(intakeSubsystem).withTimeout(100)
//                            )
//
//                    );
        //intakeServosUp = true;
        new GamepadButton(driverPad, GamepadKeys.Button.X)
                .whenPressed(
                        new SequentialCommandGroup(
                                new DepoReset(outtakePivotSubsystem, outtakeClawSubsystem).withTimeout(750),
                                new AutoElevatorDownCommand(elevatorSubsystem)
                        )
                );
        new GamepadButton(driverPad, GamepadKeys.Button.Y)
                .whenPressed(
                        new ParallelCommandGroup(
                                new ClawOpen(outtakeClawSubsystem).withTimeout(100),
                                new MaintainPosition(elevatorSubsystem)
                        )
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



        // boolean clawOpen = true; //TODO change this depending on what harish wants, itll prob change ill find a better way to do this, or ask if he wants a hold and release for claw
        new GamepadButton(driverPad, GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(
                        new ParallelCommandGroup(
                                new ClawClose(outtakeClawSubsystem).withTimeout(100),
                                new MaintainPosition(elevatorSubsystem)
                        )
                );
        //   clawOpen = false;

        // clawOpen = true;
        new GamepadButton(driverPad, GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(
                        new SequentialCommandGroup(
                                new SpecimanDropoff(outtakePivotSubsystem, outtakeClawSubsystem).withTimeout(100),
                                new ParallelCommandGroup(
                                        //new SpecimanDropoff(outtakePivotSubsystem, outtakeClawSubsystem).withTimeout(200),
                                        new AutoElevatorSpecDropCommand(elevatorSubsystem)

                                ),
                                new MaintainPosition(elevatorSubsystem)
                        )
                );



        //TODO tell harish not to use the 1 button reset for these, use the reset button. found an issue and will take time to fix it
        new GamepadButton(driverPad, GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(new SequentialCommandGroup(
                                new ParallelCommandGroup(
                                        new AutoElevatorUpHighCommand(elevatorSubsystem).withTimeout(100),
                                        new ArmDropPos(outtakePivotSubsystem, outtakeClawSubsystem).withTimeout(100)
                                ),
                                new MaintainPosition(elevatorSubsystem)
                        )
                );

        new GamepadButton(driverPad, GamepadKeys.Button.RIGHT_STICK_BUTTON)
                .whenPressed(
                        new SequentialCommandGroup(
                                new AutoElevatorUpSpecCommand(elevatorSubsystem).withTimeout(100),
                                new SpecimanDropoff(outtakePivotSubsystem, outtakeClawSubsystem).withTimeout(200),
                                new MaintainPosition(elevatorSubsystem)

//                                new ParallelCommandGroup(
//                                        new DepoReset(outtakePivotSubsystem, outtakeClawSubsystem).withTimeout(100),
//                                        new MaintainPosition(elevatorSubsystem)
//                                )
                        )

                );
        new GamepadButton(driverPad, GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(
                        new ParallelCommandGroup(
                                new AutoElevatorUpMiddleCommand(elevatorSubsystem),
                                new ArmBucketDropPosMid(outtakePivotSubsystem).withTimeout(100)

                        )
                );


        new GamepadButton(driverPad, GamepadKeys.Button.DPAD_DOWN).
                whenPressed(
                        new SequentialCommandGroup(
                                new SpecimanPickup(outtakePivotSubsystem, outtakeClawSubsystem).withTimeout(200)
                        )
                );

        new GamepadButton(driverPad, GamepadKeys.Button.DPAD_UP).whenPressed(
                new SequentialCommandGroup(
                        new AutoElevatorUpHighCommand(elevatorSubsystem).withTimeout(200),
                        // new SpecimanDropoff(outtakePivotSubsystem, outtakeClawSubsystem).withTimeout(200),
                        new WaitCommand(500),
                        new SpecimanPickup(outtakePivotSubsystem, outtakeClawSubsystem).withTimeout(100),
                        new AutoElevatorUpMiddleCommand(elevatorSubsystem).withTimeout(200),
                        new MaintainPosition(elevatorSubsystem)
                )
        );



    }
}