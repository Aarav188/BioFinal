//package org.firstinspires.ftc.teamcode.autoActions.commands.backIntake;
//
//
//import androidx.annotation.NonNull;
//
//import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
//import com.acmerobotics.roadrunner.Action;
//
//public class SpecimanToReservoir implements Action {
//    private final BackIntakeSubsystem backIntakeSubsystem;
//
//    public SpecimanToReservoir(BackIntakeSubsystem backIntakeSubsystem) {
//        this.backIntakeSubsystem = backIntakeSubsystem;
//
//    }
//
//
//    @Override
//    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
//        backIntakeSubsystem.rotateToReservoir();
//        return false;
//    }
//}
