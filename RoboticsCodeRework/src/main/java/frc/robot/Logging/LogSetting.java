package frc.robot.Logging;

import java.util.regex.Pattern;
import frc.robot.Logging.LogHandlers.*;

public class LogSetting {
    String glob;
    Pattern pattern;
    LogHandler handler;

    LogSetting(String globPattern, LogHandler handler) {
        this.glob = globPattern;
        this.pattern = Pattern.compile(globPattern);
        this.handler = handler;
    }

    boolean matches(String tag) {
        return pattern.matcher(tag).matches();
    }
}
