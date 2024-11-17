//package org.firstinspires.ftc.teamcode.configs;
//
//
//import static org.tacobots.centerstage.configs.RobotConfig.ELEVATOR_AUTODROP;
//import static org.tacobots.centerstage.configs.RobotConfig.ELEVATOR_CYCLEDROP;
//import static org.tacobots.centerstage.configs.RobotConfig.ELEVATOR_HANG_FINAL;
//import static org.tacobots.centerstage.configs.RobotConfig.ELEVATOR_HANG_INIT;
//import static org.tacobots.centerstage.configs.RobotConfig.ELEVATOR_HIGHDROP;
//import static org.tacobots.centerstage.configs.RobotConfig.ELEVATOR_MAXDROP;
//import static org.tacobots.centerstage.configs.RobotConfig.ELEVATOR_MIDDROP;
//import static org.tacobots.centerstage.configs.RobotConfig.ELEVATOR_PIXELSTACKFIFTH;
//import static org.tacobots.centerstage.configs.RobotConfig.ELEVATOR_PIXELSTACKFOURTH;
//import static org.tacobots.centerstage.configs.RobotConfig.ELEVATOR_PIXELSTACKSECOND;
//import static org.tacobots.centerstage.configs.RobotConfig.ELEVATOR_PIXELSTACKTHIRD;
//import static org.tacobots.centerstage.configs.RobotConfig.ELEVATOR_RESET;
//import static org.tacobots.centerstage.configs.RobotConfig.ELEVATOR_SPEC;
//
//public enum BoardHeight {
//    HANGINIT(ELEVATOR_HANG_INIT),
//    HANGFINAL(ELEVATOR_HANG_FINAL),
//
//
//    RESET(ELEVATOR_RESET),
//    AUTODROP(ELEVATOR_AUTODROP),
//    CYCLEDROP(ELEVATOR_CYCLEDROP),
//    LOWDROP(ELEVATOR_SPEC),
//    MIDDROP(ELEVATOR_MIDDROP),
//    HIGHDROP(ELEVATOR_HIGHDROP),
//    MAXDROP(ELEVATOR_MAXDROP),
//    FIFTHPIXEL(ELEVATOR_PIXELSTACKFIFTH),
//    FOURTHPIXEL(ELEVATOR_PIXELSTACKFOURTH),
//    THIRDPIXEL(ELEVATOR_PIXELSTACKTHIRD),
//    SECONDPIXEL(ELEVATOR_PIXELSTACKSECOND);
//
//
//    private final int motorPosition;
//
//    BoardHeight(int motorPosition) {
//        this.motorPosition = motorPosition;
//    }
//
//    public int getMotorPosition() {
//        return this.motorPosition;
//    }
//}