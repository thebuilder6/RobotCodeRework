package frc.robot.Logging.LogHandlers;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ErrorHandler implements LogHandler {
    private List<String> errors = new ArrayList<>();

    @Override
    public void handleLog(String key, Object value) {
        if (value instanceof Throwable) {
            errors.add(key + ": " + value);
        } else {
            errors.add(key + ": " + String.valueOf(value));
        }
        SmartDashboard.putString("Errors", String.join("\n", errors));
    }
}
