package org.firstinspires.ftc.teamcode.configs;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.localization.Pose;
@Config
public class FieldConstants {

    public enum RobotStart {
        BLUE_BUCKET,
        BLUE_OBSERVATION,
        RED_BUCKET,
        RED_OBSERVATION
    }

    public static final Pose blueBucketStartPose = new Pose(8.9, 87.6, Math.toRadians(-90));
    public static final Pose blueObservationStartPose = new Pose(7.5, 65.25, Math.toRadians(180));
    public static final Pose redBucketStartPose = new Pose(144-blueBucketStartPose.getX(), blueBucketStartPose.getY(), 0);
    public static final Pose redObservationStartPose = new Pose(144-blueObservationStartPose.getX(), blueObservationStartPose.getY(), 0);

    // Preload Poses
    public static final Pose blueBucketPreloadPose = new Pose(12, 120 , Math.toRadians(-70));
    public static final Pose blueObservationPreloadPose = new Pose(38.5, 66, Math.toRadians(180));
    public static final Pose redBucketPreloadPose = new Pose(144-blueBucketPreloadPose.getX(), blueBucketPreloadPose.getY(), 0);
    public static final Pose redObservationPreloadPose = new Pose(144-blueObservationPreloadPose.getX(), blueObservationPreloadPose.getY(), 0);

    // Blue Bucket Sample Poses
    public static final Pose blueBucketLeftSamplePose = new Pose(30, 125, -120);
    public static final Pose blueBucketLeftSampleControlPose = new Pose(23, 133);
    public static final Pose blueBucketMidSamplePose = new Pose(31, 131, 0);
    public static final Pose blueBucketMidSampleControlPose = new Pose(21, 137);
    public static final Pose blueBucketRightSamplePose = new Pose(32, 135, 10);
    public static final Pose blueBucketRightSampleControlPose = new Pose(23, 135);
    public static final Pose blueBucketScorePose = new Pose(19, 130, Math.toRadians(-45));

    // Blue Observation Specimen Poses
    public static final Pose blueObservationSpecimenSetPose = new Pose(12, 35, Math.toRadians(180));
    public static final Pose blueObservationSpecimenPickupPose = new Pose(6.5, 10, Math.toRadians(180));
    public static final Pose blueObservationSpecimenPickup2Pose = new Pose(6.5, 35, Math.toRadians(180));
    public static final Pose blueObservationSpecimenPickup3Pose = new Pose(6.5, 35, Math.toRadians(180));
    public static final Pose blueObservationSpecimenPickup4Pose = new Pose(6.5, 35, Math.toRadians(180));
    public static final Pose blueObservationSpecimen1Pose = new Pose(38, 74.25, Math.toRadians(180));
    public static final Pose blueObservationSpecimen2Pose = new Pose(38, 77.75, Math.toRadians(180));
    public static final Pose blueObservationSpecimen3Pose = new Pose(38, 80.25, Math.toRadians(180));
    public static final Pose blueObservationSpecimen4Pose = new Pose(38, 84.25, Math.toRadians(180));


    public static final Pose blueBucketParkPose = new Pose(62, 97.75, Math.toRadians(90));
    public static final Pose blueBucketParkControlPose = new Pose(60.25, 123.5);
    public static final Pose blueObservationParkPose = new Pose(12, 32, Math.toRadians(180));


}
