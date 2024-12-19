package org.firstinspires.ftc.teamcode.configs;

import org.openftc.apriltag.AprilTagDetection;

public class RobotState {

    public static boolean extendoOut = false;
    public static boolean intakeDepo = true;
    public static ElevatorHeights targetHeight = ElevatorHeights.RESET;
    public static OuttakeArmPos armPos = OuttakeArmPos.RESET;

    public static boolean outtakeClawOpen = true;
    public static OuttakeWristPos wristPos = OuttakeWristPos.RESET;

}