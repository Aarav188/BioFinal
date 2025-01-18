package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepBucketSide {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-12,-63,Math.toRadians(0)))
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-60,-55, Math.toRadians(65)), Math.toRadians(180))
                .setTangent(Math.toRadians(50))
                .splineToLinearHeading(new Pose2d(-53, -45, Math.toRadians(70)), Math.toRadians(60))
                .setTangent(Math.toRadians(-130))
                .splineToLinearHeading(new Pose2d(-60,-55, Math.toRadians(65)), Math.toRadians(180))
                .setTangent(45)
                .splineToLinearHeading(new Pose2d(-57, -45, Math.toRadians(90)), Math.toRadians(45))
                .setTangent(-90)
                .splineToLinearHeading(new Pose2d(-60,-55, Math.toRadians(65)), Math.toRadians(180))
                .setTangent(30)
                .splineToLinearHeading(new Pose2d(-61, -45, Math.toRadians(120)), Math.toRadians(30))
                .setTangent(-130)
                .splineToLinearHeading(new Pose2d(-60,-55, Math.toRadians(65)), Math.toRadians(180))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}