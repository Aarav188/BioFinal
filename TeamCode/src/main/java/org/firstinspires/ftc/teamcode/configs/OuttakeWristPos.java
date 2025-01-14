package org.firstinspires.ftc.teamcode.configs;


import static org.firstinspires.ftc.teamcode.configs.RobotConfig.WRIST_POS_REST;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.WRIST_POS_SPECIMAN_BACK;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.WRIST_POS_SPECIMAN_FRONT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.WRIST_POS_SPECIMAN_PICK;

public enum OuttakeWristPos {

    RESET(WRIST_POS_REST),
    SPECDROPBACK(WRIST_POS_SPECIMAN_BACK),
    SPECDROPFRONT(WRIST_POS_SPECIMAN_FRONT),
    SPECDROPPICK(WRIST_POS_SPECIMAN_PICK);


    private final double wristPos;

    OuttakeWristPos(double wristPos) {
        this.wristPos = wristPos;
    }

    public double getPosition() {
        return this.wristPos;
    }
}