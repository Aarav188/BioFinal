package org.firstinspires.ftc.teamcode.configs;


import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_HIGHDROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MAXDROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MIDDROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_RESET;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_SPEC;

public enum OuttakeRotatorPosition {

    RESET(ELEVATOR_RESET),
    LOWDROP(ELEVATOR_SPEC),
    MIDDROP(ELEVATOR_MIDDROP),
    HIGHDROP(ELEVATOR_HIGHDROP),
    MAXDROP(ELEVATOR_MAXDROP);


    private final int targetPosition;

    OuttakeRotatorPosition(int targetPosition) {
        this.targetPosition = targetPosition;
    }

    public int getPosition() {
        return this.targetPosition;
    }
}
