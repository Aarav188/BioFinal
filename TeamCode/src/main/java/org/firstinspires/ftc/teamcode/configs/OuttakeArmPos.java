package org.firstinspires.ftc.teamcode.configs;


import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_BUCKET_DROP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_BUCKET_DROP_HIGH;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_RESERVOIR_PICKUP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_RESERVOIR_PICKUP_TELEOP;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_REST;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_SPECIMAN;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_SPECIMAN_DROP_BACK;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ARM_POS_SPECIMAN_DROP_FRONT;

public enum OuttakeArmPos {

    RESET(ARM_POS_REST),
    DEPOPICK(ARM_POS_RESERVOIR_PICKUP),

    DEPOPICKTELE(ARM_POS_RESERVOIR_PICKUP_TELEOP),
    SPECPICK(ARM_POS_SPECIMAN),
    BUCKDROP(ARM_POS_BUCKET_DROP),
    BUCKDROPHIGH(ARM_POS_BUCKET_DROP_HIGH),
    SPECDROPBACK(ARM_POS_SPECIMAN_DROP_BACK),
    SPECDROPFRONT(ARM_POS_SPECIMAN_DROP_FRONT);


    private final double armPos;

    OuttakeArmPos(double armPos) {
        this.armPos = armPos;
    }

    public double getPosition() {
        return this.armPos;
    }
}