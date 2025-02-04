package org.firstinspires.ftc.teamcode.AutoSubsystems;//package org.firstinspires.ftc.teamcode.bio;
//
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.tacobots.centerstage.configs.RobotConfig;
//import org.tacobots.centerstage.configs.RobotState;
//import org.tacobots.centerstage.subsystems.core.OpenCVCamera;
//import org.tacobots.centerstage.subsystems.core.PeriodicTelemetry;
//import org.tacobots.centerstage.subsystems.core.TacoSubsystem;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class CameraSubsystem extends TacoSubsystem {
//
//    private final WebcamName webCam;
//    private final SpikeMarkDetectionPipeline spikeMarkDetectionPipeline;
//    private OpenCVCamera camera;
//
//    public CameraSubsystem(HardwareMap hardwareMap, Telemetry dashboardTelemetry) {
//        super(hardwareMap, dashboardTelemetry);
//        webCam = hardwareMap.get(WebcamName.class, RobotConfig.WEBCAM);
//        spikeMarkDetectionPipeline = new SpikeMarkDetectionPipeline(dashboardTelemetry);
//        camera = new OpenCVCamera(webCam, spikeMarkDetectionPipeline);
//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//
//    }
//
//    public void enableCamera() {
//        camera.enableCamera(webCam);
//    }
//
//    public void disableCamera() {
//        camera.disableCamera();
//    }
//
//    public PeriodicTelemetry.Callback telemetryCallback() {
//
//        return () -> {
//            Map<String, Object> telemetryData = new HashMap<>();
//
//            telemetryData.put("Spikemark position ", RobotState.spikeMarkPosition);
//            return telemetryData;
//        };
//
//    }
//
//}
