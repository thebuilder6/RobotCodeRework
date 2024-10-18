package frc.robot.Subsystems;

import java.util.ArrayList;
import java.util.List;

public class SubsystemManager {
    private static List<Subsystem> subsystems = new ArrayList<>();

    /**
     * Register a subsystem for initialization, updating, and logging.
     *
     * Subsystems must register themselves in their constructor if they are
     * enabled in settings.  SubsystemManager will then call initialize()
     * and update() on the subsystem at the appropriate times.
     *
     * @param subsystem the subsystem to register
     */
    public static void registerSubsystem(Subsystem subsystem) {
        subsystems.add(subsystem);
    }

    public static void updateAllSubsystems() {
        for (Subsystem subsystem : subsystems) {
            subsystem.update();
        }
    }

    public static void initializeAllSubsystems() {
        for (Subsystem subsystem : subsystems) {
            subsystem.initialize();
        }
    }

    public static void logAllSubsystems() {
        for (Subsystem subsystem : subsystems) {
            subsystem.log();
        }
    }

    public static List<Subsystem> getSubsystems() {
        return subsystems;
    }
}
