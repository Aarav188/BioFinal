package org.firstinspires.ftc.teamcode.pedroPathing.constants;

import static org.firstinspires.ftc.teamcode.configs.RobotConfig.DT_LEFT_FRONT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.DT_LEFT_REAR;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.DT_RIGHT_FRONT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.DT_RIGHT_REAR;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.localization.Encoder;
import com.pedropathing.localization.constants.ThreeWheelConstants;
@Config
public class LConstants {
    static {
        ThreeWheelConstants.forwardTicksToInches = 0.002;
        ThreeWheelConstants.strafeTicksToInches = 0.002;
        ThreeWheelConstants.turnTicksToInches = 0.003;
        ThreeWheelConstants.leftY = 5.5;
        ThreeWheelConstants.rightY = -5.5;
        ThreeWheelConstants.strafeX = -4;
        ThreeWheelConstants.leftEncoder_HardwareMapName = DT_LEFT_FRONT;
        ThreeWheelConstants.rightEncoder_HardwareMapName = DT_LEFT_REAR;
        ThreeWheelConstants.strafeEncoder_HardwareMapName = DT_RIGHT_FRONT;
        ThreeWheelConstants.leftEncoderDirection = Encoder.REVERSE;
        ThreeWheelConstants.rightEncoderDirection = Encoder.REVERSE;
        ThreeWheelConstants.strafeEncoderDirection = Encoder.FORWARD;
    }
}




