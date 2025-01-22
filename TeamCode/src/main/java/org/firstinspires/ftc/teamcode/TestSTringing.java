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
public class TestSTringing extends BaseOpMode {
    @Override
    public void initialize() {
        super.initialize();
        RobotCentricDriveCommand normalDriveCommand = new RobotCentricDriveCommand(mecanumDriveSubsystem, driverPad::getLeftX, driverPad::getLeftY, driverPad::getRightX, -1);
        mecanumDriveSubsystem.setDefaultCommand(normalDriveCommand);


        new GamepadButton(driverPad, GamepadKeys.Button.A).whenPressed(
                new ElevatorIncrementCommand(elevatorSubsystem, 100)
        );
        new GamepadButton(driverPad, GamepadKeys.Button.B).whenPressed(
                new ElevatorIncrementCommand(elevatorSubsystem, 200)
        );
        new GamepadButton(driverPad, GamepadKeys.Button.X).whenPressed(
                new ElevatorIncrementCommand(elevatorSubsystem, 300)
        );
        new GamepadButton(driverPad, GamepadKeys.Button.Y).whenPressed(
                new ElevatorIncrementCommand(elevatorSubsystem, 400)
        );
        new GamepadButton(driverPad, GamepadKeys.Button.RIGHT_BUMPER).whenPressed(
                new ElevatorIncrementCommand(elevatorSubsystem, 500)
        );
        new GamepadButton(driverPad, GamepadKeys.Button.LEFT_BUMPER).whenPressed(
                new ElevatorIncrementCommand(elevatorSubsystem, 600)
        );
        new GamepadTrigger(driverPad, GamepadKeys.Trigger.RIGHT_TRIGGER).whenPressed(
                new ElevatorIncrementCommand(elevatorSubsystem, 700)
        );
        new GamepadTrigger(driverPad, GamepadKeys.Trigger.LEFT_TRIGGER).whenPressed(
                new ElevatorIncrementCommand(elevatorSubsystem, 800)
        );
        new GamepadButton(driverPad, GamepadKeys.Button.DPAD_DOWN).whenPressed(
                new ElevatorIncrementCommand(elevatorSubsystem, 900)
        );
        new GamepadButton(driverPad, GamepadKeys.Button.DPAD_LEFT).whenPressed(
                new ElevatorIncrementCommand(elevatorSubsystem, 1000)
        );
        new GamepadButton(driverPad, GamepadKeys.Button.DPAD_RIGHT).whenPressed(
                new ElevatorIncrementCommand(elevatorSubsystem, 1100)
        );
        new GamepadButton(driverPad, GamepadKeys.Button.DPAD_UP).whenPressed(
                new ElevatorIncrementCommand(elevatorSubsystem, 1200)
        );



        new GamepadButton(operatorPad, GamepadKeys.Button.A).whenPressed(
                new HangIncrementCommand(hangSubsystem, 1000)
        );
        new GamepadButton(operatorPad, GamepadKeys.Button.B).whenPressed(
                new HangIncrementCommand(hangSubsystem, 2000)
        );
        new GamepadButton(operatorPad, GamepadKeys.Button.X).whenPressed(
                new HangIncrementCommand(hangSubsystem, 3000)
        );
        new GamepadButton(operatorPad, GamepadKeys.Button.Y).whenPressed(
                new HangIncrementCommand(hangSubsystem, 4000)
        );
        new GamepadButton(operatorPad, GamepadKeys.Button.RIGHT_BUMPER).whenPressed(
                new HangIncrementCommand(hangSubsystem, 5000)
        );
        new GamepadButton(operatorPad, GamepadKeys.Button.LEFT_BUMPER).whenPressed(
                new HangIncrementCommand(hangSubsystem, 6000)
        );
        new GamepadTrigger(operatorPad, GamepadKeys.Trigger.RIGHT_TRIGGER).whenPressed(
                new HangIncrementCommand(hangSubsystem, 7000)
        );
        new GamepadTrigger(operatorPad, GamepadKeys.Trigger.LEFT_TRIGGER).whenPressed(
                new HangIncrementCommand(hangSubsystem, 8000)
        );
        new GamepadButton(operatorPad, GamepadKeys.Button.DPAD_DOWN).whenPressed(
                new HangIncrementCommand(hangSubsystem, 9000)
        );
        new GamepadButton(operatorPad, GamepadKeys.Button.DPAD_LEFT).whenPressed(
                new HangIncrementCommand(hangSubsystem, 10000)
        );
        new GamepadButton(operatorPad, GamepadKeys.Button.DPAD_RIGHT).whenPressed(
                new HangIncrementCommand(hangSubsystem, 11000)
        );
        new GamepadButton(operatorPad, GamepadKeys.Button.DPAD_UP).whenPressed(
                new HangIncrementCommand(hangSubsystem, 12000)
        );



    }
}