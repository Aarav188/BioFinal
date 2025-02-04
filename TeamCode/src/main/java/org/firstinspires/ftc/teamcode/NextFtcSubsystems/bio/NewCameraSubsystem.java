package org.firstinspires.ftc.teamcode.NextFtcSubsystems.bio;//package org.firstinspires.ftc.teamcode.bio;
//
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.tacobots.centerstage.configs.RobotConfig;
//import org.tacobots.centerstage.configs.RobotState;
//import org.tacobots.centerstage.subsystems.core.NewCamera;
//import org.tacobots.centerstage.subsystems.core.PeriodicTelemetry;
//import org.tacobots.centerstage.subsystems.core.TacoSubsystem;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class NewCameraSubsystem extends TacoSubsystem {
//
//    private final WebcamName webCam;
//    private final SpikeMarkDetectionPipeline spikeMarkDetectionPipeline;
//    private NewCamera camera;
//
//    public NewCameraSubsystem(HardwareMap hardwareMap, Telemetry dashboardTelemetry) {
//        super(hardwareMap, dashboardTelemetry);
//        webCam = hardwareMap.get(WebcamName.class, RobotConfig.WEBCAM);
//        spikeMarkDetectionPipeline = new SpikeMarkDetectionPipeline(dashboardTelemetry);
//        camera = new NewCamera(hardwareMap,RobotConfig.WEBCAM, spikeMarkDetectionPipeline);
//
//    }
//
//    public void enableCamera() {
//        camera.init();
//    }
//
//    public void disableCamera() {
//        camera.close();
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
