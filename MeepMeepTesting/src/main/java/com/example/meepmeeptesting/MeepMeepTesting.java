package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(12,-63,Math.toRadians(-90)))
                .lineToYLinearHeading(-30, Math.toRadians(-90))
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(35, -35, Math.toRadians(40)), Math.toRadians(50))
                .splineToLinearHeading(new Pose2d(53, -50, Math.toRadians(70)), Math.toRadians(70))
                .splineToLinearHeading(new Pose2d(56, -40, Math.toRadians(75)), Math.toRadians(80))
                .splineToLinearHeading(new Pose2d(62, -50, Math.toRadians(70)), Math.toRadians(70))
                .splineToConstantHeading(new Vector2d(65, -40), Math.toRadians(80))
                .splineToLinearHeading(new Pose2d(40, -55, Math.toRadians(100)), Math.toRadians(-100))
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(5, -30, Math.toRadians(90)), Math.toRadians(90))
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(40, -55, Math.toRadians(90)), Math.toRadians(-90))
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(2, -30, Math.toRadians(90)), Math.toRadians(90))
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(40, -55, Math.toRadians(90)), Math.toRadians(-90))
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-1, -30, Math.toRadians(90)), Math.toRadians(90))
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(40, -55, Math.toRadians(90)), Math.toRadians(-90))
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-4, -30, Math.toRadians(90)), Math.toRadians(90))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}