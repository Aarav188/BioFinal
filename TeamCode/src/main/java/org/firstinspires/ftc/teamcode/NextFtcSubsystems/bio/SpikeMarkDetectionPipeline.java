package org.firstinspires.ftc.teamcode.NextFtcSubsystems.bio;//package org.firstinspires.ftc.teamcode.bio;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.core.Point;
//import org.opencv.core.Rect;
//import org.opencv.core.Scalar;
//import org.opencv.imgproc.Imgproc;
//import org.openftc.easyopencv.OpenCvPipeline;
//import org.tacobots.centerstage.configs.RobotState;
//import org.tacobots.centerstage.configs.SpikeMarkPosition;
//
//public class SpikeMarkDetectionPipeline extends OpenCvPipeline {
//
//    static double PERCENT_COLOR_THRESHOLD = 0.4;
//    Telemetry telemetry;
//    Mat mat = new Mat();
//
//    public SpikeMarkDetectionPipeline(Telemetry t) {
//        telemetry = t;
//    }
//
//    @Override
//    public Mat processFrame(Mat input) {
//        Rect RIGHT_ROI = new Rect(
//                    new Point(20, 280),
//                    new Point(70, 330));
//        Rect CENTER_ROI = new Rect(
//                    new Point(480, 350),
//                    new Point(530, 400));
//
//        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
//        Scalar colorStone = new Scalar(255, 0, 0);
//        Imgproc.rectangle(mat, CENTER_ROI, new Scalar(0,0,0));
//        Imgproc.rectangle(mat, RIGHT_ROI, new Scalar(0,0,0));
//        Scalar lowHSV = new Scalar(40, 160, 140);
//        Scalar highHSV = new Scalar(70, 220, 180);
//
//        Mat center = mat.submat(CENTER_ROI);
//        Mat right = mat.submat(RIGHT_ROI);
//
//        double[] centerROIHSV = Core.mean(center).val;
//        double[] rightROIHSV = Core.mean(right).val;
//
//
//        boolean isRight = rightROIHSV[1] >= 60;
//        boolean isCenter = centerROIHSV[1] >=60;
//        center.release();
//        right.release();
//
//        telemetry.addData("IS RIGHT", isRight);
//        telemetry.addData("IS CENTER", isCenter);
//
//
//        if (isRight) {
//            RobotState.spikeMarkPosition = SpikeMarkPosition.RIGHT;
//        } else if (isCenter) {
//            RobotState.spikeMarkPosition = SpikeMarkPosition.CENTER;
//        } else {
//            RobotState.spikeMarkPosition = SpikeMarkPosition.LEFT;
//        }
//        telemetry.addData("position", RobotState.spikeMarkPosition);
//        telemetry.update();
//
//
//
//
//        return mat;
//    }
//
//}
