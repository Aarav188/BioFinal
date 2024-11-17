package org.firstinspires.ftc.teamcode.bio;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class TacoSubsystem extends SubsystemBase {

    protected Telemetry dashboardTelemetry;
    protected HardwareMap hardwareMap;

    public TacoSubsystem(HardwareMap hardwareMap, Telemetry dashboardTelemetry) {
        this.dashboardTelemetry = dashboardTelemetry;
        this.hardwareMap = hardwareMap;
    }
}

