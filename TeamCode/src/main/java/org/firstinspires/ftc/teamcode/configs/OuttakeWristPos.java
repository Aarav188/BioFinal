package org.firstinspires.ftc.teamcode.configs;


import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_BUCKET_DROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_BUCKET_DROP_HIGH;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_RESERVOIR_PICKUP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_REST;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_SPECIMAN;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_SPECIMAN_DROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_AUTODROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_CYCLEDROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_HIGHDROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_LOWDROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MAXDROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_MIDDROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_RESET;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_SPEC;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ELEVATOR_SPECDROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.WRIST_POS_REST;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.WRIST_POS_SPECIMAN;

public enum OuttakeWristPos {

    RESET(WRIST_POS_REST),
    SPECPICK(WRIST_POS_SPECIMAN);


    private final double wristPos;

    OuttakeWristPos(double wristPos) {
        this.wristPos = wristPos;
    }

    public double getPosition() {
        return this.wristPos;
    }
}