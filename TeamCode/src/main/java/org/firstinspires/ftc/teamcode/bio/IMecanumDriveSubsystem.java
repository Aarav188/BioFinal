package org.firstinspires.ftc.teamcode.bio;



public interface IMecanumDriveSubsystem {
    void robotCentricDrive(double strafeSpeed, double forwardSpeed, double turnSpeed);

    void robotCentricDrive(double strafeSpeed, double forwardSpeed, double turnSpeed, boolean squareInputs);
}
