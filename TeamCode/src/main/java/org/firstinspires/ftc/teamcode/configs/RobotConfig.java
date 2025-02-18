package org.firstinspires.ftc.teamcode.configs;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

@Config
public class RobotConfig {

    // Intake Servo  Subsystem
    public static String INTAKE_ROTATOR = "intakeRotator";
    public static String INTAKE_STOPPER = "stopper";
    public static double INTAKE_LARGE_ROTATOR_UP_POSITION = 0.83;

    public static double INTAKE_LARGE_ROTATOR_DOWN_POSITION = 0.67; //0
    public static double INTAKE_SMALL_ROTATOR_UP_POSITION = 0.03;
    public static double INTAKE_SMALL_ROTATOR_DOWN_POSITION = 0.79;
    public static double INTAKE_STOPPER_UP_POSITION = 0.9;
    public static double INTAKE_STOPPER_DOWN_POSITION = 0.61;

    //extendo subsystem
    public static double EXTENDO_LEFT_MAX_OUT_POSITION = 0.1;
    public static double EXTENDO_RIGHT_MAX_OUT_POSITION = 0.95;
    public static double EXTENDO_LEFT_IN_POSITION = 0.41;
    public static double EXTENDO_RIGHT_IN_POSITION = 0.35;

    // Intake Rotator Subsystem
    public static  String CAPTURE_COLOR_SENSOR = "captureColor";
    public static  String INTAKE_ROTATION_MOTOR = "intake";
    public static  String CLAW_SERVO = "claw";
    public static  String INTAKE_TILT_SERVO = "intakeTiltServo";
    public static  String BEAM_SERVO = "beamServo";
    public static  double INTAKE_POWER = 1.0;


    //Drivetrain subsystem
    public static  String DT_LEFT_FRONT = "leftFront";
    public static  String DT_RIGHT_FRONT = "rightFront";
    public static  String DT_LEFT_REAR = "leftRear";
    public static  String DT_RIGHT_REAR = "rightRear";
    public static  boolean DT_SQUARE_INPUT = false;

    //Camera subsystem
    public static  String WEBCAM = "Webcam 1";

    //IMU Subsystem - Built-in REV
    public static  String IMU = "imu";

    //Odometry subsystem
    public static  String LEFT_ENCODER = DT_LEFT_REAR;
    public static  String RIGHT_ENCODER = DT_RIGHT_REAR;
    public static  String PERP_ENCODER = DT_LEFT_FRONT;

    public static String HANG = "hangSlide";
    public static int HANGPOS = 1000;
    public static int UNHANGPOS = 0;


    public static  double TRACKWIDTH = 15;

    //Odometry
    public static  double ODO_CENTER_WHEEL_OFFSET = 1.125; // in; offset of the lateral wheel
    public static  double ODO_TICKS_PER_REV = 2000;
    public static  double ODO_WHEEL_RADIUS = 1.37795; // in 48mm odometry POD
    public static  double ODO_GEAR_RATIO = 1; // output (wheel) speed / input (encoder) speed

    public static  double ODO_LATERAL_DISTANCE = 7.29; // in; distance between the left and right wheels

    // if needed, one can add a gearing term here

    public static  double WHEEL_DIAMETER = 3.779528; // 96mm to inches
    //    public static  double  TICKS_PER_REV = 384.5  //https://www.gobilda.com/5203-series-yellow-jacket-planetary-gear-motor-13-7-1-ratio-24mm-length-8mm-rex-shaft-435-rpm-3-3-5v-encoder/

    //End Odometery tuning.


    //Timer subsystem
    public static  String TIMER_BLINKIN = "timerBlinkin";

    // TODO: If the hub containing the IMU you are using is mounted so that the "REV" logo does
    // not face up, remap the IMU axes so that the z-axis points upward (normal to the floor.)
    //
    //             | +Z axis
    //             |
    //             |
    //             |
    //      _______|_____________     +Y axis
    //     /       |_____________/|__________
    //    /   REV / EXPANSION   //
    //   /       / HUB         //
    //  /_______/_____________//
    // |_______/_____________|/
    //        /
    //       / +X axis
    //
    // This diagram is derived from the axes in section 3.4 https://www.bosch-sensortec.com/media/boschsensortec/downloads/datasheets/bst-bno055-ds000.pdf
    // and the placement of the dot/orientation from https://docs.revrobotics.com/rev-control-system/control-system-overview/dimensions#imu-location
    //
    // For example, if +Y in this diagram faces downwards, you would use AxisDirection.NEG_Y.
    // BNO055IMUUtil.remapZAxis(imu, AxisDirection.NEG_Y);
    RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
    RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection = RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD;





    //Rotator subsystem
    public static  String LEFT_ROTATOR_SERVO = "leftOuttakeRotator";
    public static  String RIGHT_ROTATOR_SERVO = "rightOuttakeRotator";
    public static  String CLAW = "claw";
    public static  String WRIST = "wrist";
    public static  String LEFT_CLAW_ROTATE = "leftClawRotator";
    public static  String RIGHT_CLAW_ROTATE = "rightClawRotator";
    public static double WRIST_POS_SPECIMAN_BACK = 0.4;
    public static double WRIST_POS_SPECIMAN_PICK = 0.25;
    public static double WRIST_POS_SPECIMAN_FRONT = 0.6;
    public static double WRIST_POS_BUCKET_DROP = 0.1;
    public static double WRIST_POS_DEPO_RESET = 0.7;
    public static double ARM_POS_SPECIMAN = 0.1;
    public static double ARM_POS_BUCKET_DROP = 1;
    public static double ARM_POS_BUCKET_DROP_HIGH = 0;
    public static double WRIST_POS_REST = 0.73;
    public static double ARM_POS_SPECIMAN_DROP_BACK = 0.1;
    public static double ARM_POS_SPECIMAN_DROP_FRONT = 0.7;
    public static double  WRIST_POS_DEPO_RESET_TELEOP = 0.58;
    public static double ARM_POS_RESERVOIR_PICKUP_TELEOP = 0.3;

    public static double CLAW_OPEN = 0.15;
    public static double CLAW_CLOSE = 0.40;
    public static double ARM_POS_REST = 0.82;
    public static double ARM_POS_RESERVOIR_PICKUP = 0.75;
    public static String OUTTAKE_STOPPER = "outtakeStopper";
    public static double OUTTAKE_STOPPER_LOCK = 0.18;
    public static double OUTTAKE_STOPPER_UNLOCK = 0.5;

    public static String LEFT_LINKAGE = "leftLinkage";
    public static String RIGHT_LINKAGE = "rightLinkage";
    public static  int ROTATOR_POSITION_CONEGRABAUTO = -541;
    public static  int ROTATOR_POSITION_FULL_RESET = -2000;
    public static  int ROTATOR_POSITION_CONEGRAB = 0;
    public static  int ROTATOR_POSITION_UP = -200; //-330
    public static  int ROTATOR_POSITION_DROP = -310; // -680
    public static  double ROTATOR_MOTOR_POWER = 0.1;
    public static  double ROTATOR_MOTOR_TOLERANCE = 5;
    public static  double ROTATOR_MOTOR_KP = 0.05;

    // Outtake Rotator Subsystem
    public static double BOX_DOWN_POSITION = 0.54;
    public static double BOX_UP_POSITION = 0.267;

    // Outtake Pivot Subsystem
    public static String PIVOT_SERVO = "outtakePivot";
    public static double PIVOT_RESET = 0.48;
    public static double PIVOT_HORIZONTAL = 0.06; //0.746



    //Dropper Subsystem
    public static  String OUTTAKE_DROPPER_SERVO = "outtakeGrabber";

    public static  String OUTTAKE_PIVOT_SERVO = "outtakePivot";
    public static  double DROPPER_GRAB_POSITION = 0.22; // 0
    public static  double DROPPER_RESET_POSITION = 0.56; // 0.8875

    //Elevator subsystem
    public static  String ELEVATOR_MOTOR_RIGHT = "rightSlideUp";
    public static  String ELEVATOR_MOTOR_LEFT = "leftSlideUp";

    public static  double ELEVATOR_MOTOR_KP = 0.01;
    public static  double ELEVATOR_MOTOR_TOLERANCE = 5;
    public static  double ELEVATOR_MOTOR_POWER = 1;
    public static double ELEVATOR_MOTOR_POWER_DOWN=0.00005;


    public static  int ELEVATOR_RESET = 0;
    public static  int ELEVATOR_AUTODROP = -675; //80
    public static  int ELEVATOR_CYCLEDROP = -900; //80
    public static int ELEVATOR_LOWDROP = -400;
    public static  int ELEVATOR_SPEC = -700; //80
    public static  int ELEVATOR_MIDDROP = -900;

    public static  int ELEVATOR_SPECDROP = -850; //80
    public static  int ELEVATOR_HIGHDROP = -1200; // -725
    public static  int ELEVATOR_MAXDROP = -1100;
    public static  int ELEVATOR_PIXELSTACKFIFTH = 250; // -40
    public static  int ELEVATOR_PIXELSTACKFOURTH = 210;
    public static  int ELEVATOR_PIXELSTACKTHIRD = 170;
    public static  int ELEVATOR_PIXELSTACKSECOND = 140;
    public static  int ELEVATOR_PIXELSTACKFIRST = 110;

    public static int ELEVATOR_HANG_INIT = -1600;
    public static int ELEVATOR_HANG_FINAL = -400;

    public static int ELEVATOR_INCREMENT_POSITION = -250;
    public static int ELEVATOR_DECREMENT_POSITION = 250;
    public static int ELEVATOR_SMALL_INCREMENT = -120;
    public static int ELEVATOR_SMALL_DECREMENT = 120;



    public static  int LIFT_maxVel = 3500;
    public static  int LIFT_maxAccel = 7500;
    public static  int LIFT_THIS_CLOSE = 2;
    public static  double LIFT_P = 0.0375;
    public static  double LIFT_I = 0;
    public static  double LIFT_D = 0;
    public static  double LIFT_MOTOR_POWER = 0.5;

    // back intake subsystem
    public static  String BACK_INTAKE_SERVO = "backIntakeServo";
    public static  String BACK_ROTATION_SERVO = "backRotationServo";

}