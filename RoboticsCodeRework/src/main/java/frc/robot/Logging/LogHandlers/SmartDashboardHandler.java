package frc.robot.Logging.LogHandlers;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardHandler implements LogHandler {
    @Override
    public void handleLog(String key, Object value) {
        SmartDashboard.putData(key, (Sendable) value);
    }

}
