package org.firstinspires.ftc.teamcode.pedroPathing.constants;

import static org.firstinspires.ftc.teamcode.configs.RobotConfig.DT_LEFT_FRONT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.DT_LEFT_REAR;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.DT_RIGHT_FRONT;
import static org.firstinspires.ftc.teamcode.configs.RobotConfig.DT_RIGHT_REAR;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.localization.Localizers;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@Config
public class FConstants {
    static {
        FollowerConstants.localizers = Localizers.THREE_WHEEL;

        FollowerConstants.leftFrontMotorName = DT_LEFT_FRONT;
        FollowerConstants.leftRearMotorName = DT_LEFT_REAR;
        FollowerConstants.rightFrontMotorName = DT_RIGHT_FRONT;
        FollowerConstants.rightRearMotorName = DT_RIGHT_REAR;

        FollowerConstants.leftFrontMotorDirection = DcMotorSimple.Direction.FORWARD;
        FollowerConstants.leftRearMotorDirection = DcMotorSimple.Direction.REVERSE;
        FollowerConstants.rightFrontMotorDirection = DcMotorSimple.Direction.FORWARD;
        FollowerConstants.rightRearMotorDirection = DcMotorSimple.Direction.FORWARD;

        FollowerConstants.mass = 14.7;

        FollowerConstants.xMovement = 75.3917;
        FollowerConstants.yMovement = 53.7691;

        FollowerConstants.forwardZeroPowerAcceleration = -45.9084;
        FollowerConstants.lateralZeroPowerAcceleration = -89.9394;

        FollowerConstants.translationalPIDFCoefficients.setCoefficients(0.25,0,0.02,0);
        FollowerConstants.useSecondaryTranslationalPID = false;
        FollowerConstants.secondaryTranslationalPIDFCoefficients.setCoefficients(0.1,0,0.01,0); // Not being used, @see useSecondaryTranslationalPID

        FollowerConstants.headingPIDFCoefficients.setCoefficients(3,0,0.3,0);
        FollowerConstants.useSecondaryHeadingPID = false;
        FollowerConstants.secondaryHeadingPIDFCoefficients.setCoefficients(2,0,0.1,0); // Not being used, @see useSecondaryHeadingPID

        FollowerConstants.drivePIDFCoefficients.setCoefficients(0.02,0,0.00001,0.6,0);
        FollowerConstants.useSecondaryDrivePID = false;
        FollowerConstants.secondaryDrivePIDFCoefficients.setCoefficients(0.1,0,0,0.6,0); // Not being used, @see useSecondaryDrivePID

        FollowerConstants.zeroPowerAccelerationMultiplier = 2;
        FollowerConstants.centripetalScaling = 0.0006;

        FollowerConstants.pathEndTimeoutConstraint = 500;
        FollowerConstants.pathEndTValueConstraint = 0.995;
        FollowerConstants.pathEndVelocityConstraint = 0.1;
        FollowerConstants.pathEndTranslationalConstraint = 0.1;
        FollowerConstants.pathEndHeadingConstraint = 0.007;
    }
}
