package frc.robot.Logging.LogHandlers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileHandler implements LogHandler {
    private String filename;

    public FileHandler(String filename) {
        this.filename = filename;
    }

    /**
     * Appends a log entry to the specified file.
     * @param key The key of the log entry.
     * @param value The value of the log entry.
     */
    @Override
    public void handleLog(String key, Object value) {
        // Append log to the specified file
        try (FileWriter fw = new FileWriter(filename, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(key + ": " + value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
