//package org.firstinspires.ftc.teamcode.bio;
//
//import static org.tacobots.centerstage.configs.RobotConfig.TIMER_BLINKIN;
//
//import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//import org.tacobots.centerstage.subsystems.core.PeriodicTelemetry;
//import org.tacobots.centerstage.subsystems.core.TacoSubsystem;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//public class TimeKeeperSubsystem extends TacoSubsystem {
//
//    private static final RevBlinkinLedDriver.BlinkinPattern PATTERN_MAIN = RevBlinkinLedDriver.BlinkinPattern.GREEN;
//    private static final RevBlinkinLedDriver.BlinkinPattern PATTERN_START_ENDGAME = RevBlinkinLedDriver.BlinkinPattern.YELLOW;
//    private static final RevBlinkinLedDriver.BlinkinPattern PATTERN_FINAL_25 = RevBlinkinLedDriver.BlinkinPattern.RED;
//    private static final RevBlinkinLedDriver.BlinkinPattern PATTERN_FINAL_5 = RevBlinkinLedDriver.BlinkinPattern.SHOT_RED;
//
//    private final RevBlinkinLedDriver timerBlinkin;
//    private final ElapsedTime timer;
//    private PLAY_STATE play_state;
//
//    public TimeKeeperSubsystem(HardwareMap hardwareMap, Telemetry dashboardTelemetry) {
//        super(hardwareMap, dashboardTelemetry);
//
//        timerBlinkin = hardwareMap.get(RevBlinkinLedDriver.class, TIMER_BLINKIN);
//        timerBlinkin.setPattern(PATTERN_MAIN);
//        timer = new ElapsedTime();
//
//    }
//
//    @Override
//    public void periodic() {
//
//        if (timer.seconds() <= 75) {
//            play_state = PLAY_STATE.MAIN;
//        } else if (timer.seconds() >= 75 && timer.seconds() <= 90) {
//            play_state = PLAY_STATE.START_ENDGAME;
//        } else if (timer.seconds() >= 90 && timer.seconds() <= 115) {
//            play_state = PLAY_STATE.FINAL_25;
//        } else if (timer.seconds() >= 115 && timer.seconds() <= 120) {
//            play_state = PLAY_STATE.FINAL_5;
//        }
//        timerBlinkin.setPattern(play_state.getPattern());
//
//    }
//
//    @Override
//    public PeriodicTelemetry.Callback telemetryCallback() {
//
//        return () -> {
//            Map<String, Object> telemetryData = new HashMap<>();
//
//
//            telemetryData.put("Play State ", play_state);
//            return telemetryData;
//        };
//
//    }
//
//
//    private enum PLAY_STATE {
//        MAIN(PATTERN_MAIN),
//        START_ENDGAME(PATTERN_START_ENDGAME),
//        FINAL_25(PATTERN_FINAL_25),
//        FINAL_5(PATTERN_FINAL_5);
//
//        private final RevBlinkinLedDriver.BlinkinPattern pattern;
//
//        PLAY_STATE(RevBlinkinLedDriver.BlinkinPattern pattern) {
//            this.pattern = pattern;
//        }
//
//        public RevBlinkinLedDriver.BlinkinPattern getPattern() {
//            return pattern;
//        }
//    }
//
//
//}
