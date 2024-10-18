package frc.robot.Logging;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import frc.robot.Logging.LogHandlers.*;
import frc.robot.Logging.Interfaces.*;

class Logger {
    // Mapping from glob patterns to output handlers
    private static List<LogSetting> settingsList = Arrays.asList(
            new LogSetting(".*driver.*", new SmartDashboardHandler()),
            new LogSetting(".*programmer.*", new FileHandler("programmer.log"))
    // Add more settings as needed
    );

    /**
     * Logs a value with the given key and tags. The value is provided by the given
     * supplier, which is only invoked if there are matching log handlers.
     * 
     * @param key      The key of the value to log.
     * @param valueSupplier
     *                   The supplier of the value to log.
     * @param tags     The tags to match for logging.
     */
    public static void log(String key, ValueSupplier<?> valueSupplier, String tags) {
        Set<String> tagSet = new HashSet<>(Arrays.asList(tags.split(",\\s*")));
        List<LogHandler> handlersToInvoke = new ArrayList<>();

        for (LogSetting setting : settingsList) {
            for (String tag : tagSet) {
                if (setting.matches(tag)) {
                    handlersToInvoke.add(setting.handler);
                    break; // Avoid duplicate logging for the same handler
                }
            }
        }

        if (!handlersToInvoke.isEmpty()) {
            Object value = valueSupplier.get(); // Invoke supplier only if needed
            for (LogHandler handler : handlersToInvoke) {
                handler.handleLog(key, value);
            }
        }
    }
}