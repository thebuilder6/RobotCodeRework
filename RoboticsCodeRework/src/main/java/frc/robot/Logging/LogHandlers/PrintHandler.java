package frc.robot.Logging.LogHandlers;

public class PrintHandler implements LogHandler {

    /**
     * Prints the key-value pair to stdout. Useful for debugging.
     * @param key The key of the log entry.
     * @param value The value of the log entry.
     */
    @Override
    public void handleLog(String key, Object value) {
        System.out.println(key + ": " + value);
    }
}
