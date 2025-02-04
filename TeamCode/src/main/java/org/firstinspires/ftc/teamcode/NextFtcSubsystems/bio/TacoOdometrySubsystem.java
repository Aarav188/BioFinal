package org.firstinspires.ftc.teamcode.NextFtcSubsystems.bio;//package org.firstinspires.ftc.teamcode.bio;
//
//
//import static org.firstinspires.ftc.teamcode.configs.RobotConfig.LEFT_ENCODER;
//import static org.firstinspires.ftc.teamcode.configs.RobotConfig.ODO_CENTER_WHEEL_OFFSET;
//import static org.firstinspires.ftc.teamcode.configs.RobotConfig.PERP_ENCODER;
//import static org.firstinspires.ftc.teamcode.configs.RobotConfig.RIGHT_ENCODER;
//import static org.firstinspires.ftc.teamcode.configs.RobotConfig.TRACKWIDTH;
//
//import com.arcrobotics.ftclib.command.OdometrySubsystem;
//import com.arcrobotics.ftclib.hardware.motors.MotorEx;
//import com.arcrobotics.ftclib.kinematics.HolonomicOdometry;
//import com.arcrobotics.ftclib.kinematics.Odometry;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class TacoOdometrySubsystem extends TacoSubsystem {
//
//    private final MotorEx leftEncoder;
//    private final MotorEx rightEncoder;
//    private final MotorEx perpEncoder;
//    private final OdometrySubsystem oss;
//    private final HolonomicOdometry odometry;
//
//    public TacoOdometrySubsystem(HardwareMap hardwareMap, Telemetry dashboardTelemetry) {
//        super(hardwareMap, dashboardTelemetry);
//
//        leftEncoder = new MotorEx(hardwareMap, LEFT_ENCODER);
//        rightEncoder = new MotorEx(hardwareMap, RIGHT_ENCODER);
//        perpEncoder = new MotorEx(hardwareMap, PERP_ENCODER);
//
//
//        leftEncoder.resetEncoder();
//        rightEncoder.resetEncoder();
//        perpEncoder.resetEncoder();
//
//        leftEncoder.setDistancePerPulse(TICKS_TO_INCHES);
//        rightEncoder.setDistancePerPulse(TICKS_TO_INCHES);
//        perpEncoder.setDistancePerPulse(TICKS_TO_INCHES);
//
//        odometry = new HolonomicOdometry(
//                leftEncoder::getDistance,
//                rightEncoder::getDistance,
//                perpEncoder::getDistance,
//                TRACKWIDTH,
//                ODO_CENTER_WHEEL_OFFSET
//        );
//
//        oss = new OdometrySubsystem(odometry);
//
//    }
//
//    /**
//     * Updates the pose every cycle
//     */
//    /**
//    @Override
//    public void periodic() {
//
//        oss.periodic();
//        BaseRobotState.robotPose = oss.getPose();
//    }
//    **/
//
//    public OdometrySubsystem getOdometrySubsystem() {
//        return oss;
//    }
//
//    public Odometry getOdometry() {
//        return odometry;
//    }
//
//
//}
//
//
