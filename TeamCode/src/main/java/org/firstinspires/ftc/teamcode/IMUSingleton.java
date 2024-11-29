package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.configs.RobotConfig;

public class IMUSingleton {
    public static IMU imu;

    public IMUSingleton(HardwareMap hardwareMap, Telemetry telemetry) {
        if (imu == null) {
            imu = hardwareMap.get(IMU.class, RobotConfig.IMU);

            RevHubOrientationOnRobot orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD);

            IMU.Parameters parameters = new IMU.Parameters(orientation);

//            parameters.mode = BNO055IMU.SensorMode.IMU;
//            parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;

           /*
               TODO try this on bot, SHOULD improve gyro accuracy
               and save battery by not using accelerometer
           */

            //parameters.accelPowerMode = BNO055IMU.AccelPowerMode.SUSPEND;
            //parameters.gyroPowerMode = BNO055IMU.GyroPowerMode.FAST;

            imu.initialize(parameters);
            // while (!imu.isGyroCalibrated()) ;

        }
    }

}




