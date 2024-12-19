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

public enum OuttakeArmPos {

    RESET(ARM_POS_REST),
    DEPOPICK(ARM_POS_RESERVOIR_PICKUP),
    SPECPICK(ARM_POS_SPECIMAN),
    BUCKDROP(ARM_POS_BUCKET_DROP),
    BUCKDROPHIGH(ARM_POS_BUCKET_DROP_HIGH),
    SPECDROP(ARM_POS_SPECIMAN_DROP);


    private final double armPos;

    OuttakeArmPos(double armPos) {
        this.armPos = armPos;
    }

    public double getPosition() {
        return this.armPos;
    }
}