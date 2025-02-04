//package org.firstinspires.ftc.teamcode.NextFtcSubsystems.bio;//package org.firstinspires.ftc.teamcode.bio;
////
////import com.qualcomm.robotcore.hardware.HardwareMap;
////import com.qualcomm.robotcore.hardware.VoltageSensor;
////
////import org.firstinspires.ftc.robotcore.external.Telemetry;
////import org.tacobots.centerstage.configs.BaseRobotState;
////import org.tacobots.centerstage.subsystems.core.PeriodicTelemetry;
////import org.tacobots.centerstage.subsystems.core.TacoSubsystem;
////
////import java.util.HashMap;
////import java.util.Map;
////
////public class VoltageFeedbackSubsystem extends TacoSubsystem {
////
////    private final VoltageSensor batteryVoltageSensor;
////
////    public VoltageFeedbackSubsystem(HardwareMap hardwareMap, Telemetry dashboardTelemetry) {
////        super(hardwareMap, dashboardTelemetry);
////        batteryVoltageSensor = hardwareMap.voltageSensor.iterator().next();
////
////    }
////
////    @Override
////    public void periodic() {
////        BaseRobotState.robotVoltage = batteryVoltageSensor.getVoltage();
////    }
////
////    @Override
////    public PeriodicTelemetry.Callback telemetryCallback() {
////
////        return () -> {
////            Map<String, Object> telemetryData = new HashMap<>();
////
////
////            telemetryData.put("Voltage ", batteryVoltageSensor.getVoltage());
////            return telemetryData;
////        };
////
////    }
////}
