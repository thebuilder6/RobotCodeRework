package frc.robot.Logging.LogHandlers;

public class ErrorHandler implements LogHandler {
    @Override
    public void handleLogMessage() {
        // Implement your error handling logic here
        System.err.println("Error: " + message.getMessage());

    }
}
