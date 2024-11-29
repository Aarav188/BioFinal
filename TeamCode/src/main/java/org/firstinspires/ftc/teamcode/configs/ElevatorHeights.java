package org.firstinspires.ftc.teamcode.configs;


import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_AUTODROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_CYCLEDROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_HIGHDROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_LOWDROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MAXDROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MIDDROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_RESET;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_SPEC;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_SPECDROP;

public enum ElevatorHeights {

    RESET(ELEVATOR_RESET),
    AUTODROP(ELEVATOR_AUTODROP),
    CYCLEDROP(ELEVATOR_CYCLEDROP),
    LOWDROP(ELEVATOR_LOWDROP),
    SPEC(ELEVATOR_SPEC),
    MIDDROP(ELEVATOR_MIDDROP),
    SPECDROP(ELEVATOR_SPECDROP),
    HIGHDROP(ELEVATOR_HIGHDROP),
    MAXDROP(ELEVATOR_MAXDROP);


    private final int motorPosition;

    ElevatorHeights(int motorPosition) {
        this.motorPosition = motorPosition;
    }

    public int getMotorPosition() {
        return this.motorPosition;
    }
}