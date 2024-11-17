package org.firstinspires.ftc.teamcode.configs;


import static org.firstinspires.ftc.teamcode.configs.RobotConfig.DROPPER_GRAB_POSITION;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.DROPPER_RESET_POSITION;

public enum TiltServoPosition {

    GRAB(DROPPER_GRAB_POSITION),
    RESET(DROPPER_RESET_POSITION);


    private final double targetPosition;

    TiltServoPosition(double targetPosition) {
        this.targetPosition = targetPosition;
    }

    public double getPosition() {
        return this.targetPosition;
    }
}
