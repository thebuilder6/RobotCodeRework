package frc.robot.RobotDefinitions;

import java.util.HashMap;
import java.util.Map;

import frc.robot.Settings;
import frc.robot.Subsystems.SmartDashBoardIO;

public class SimulatorBot implements RobotDefinition
{
    private static Map<String, Object> settings = new HashMap<>();

    public static void loadSettings()
    {
        // Load settings from a file or any other source
        // and populate the settings map
        settings.put("NUMBEROFCONTROLLERS", 2);
        settings.put("DRIVEBASE", "DISABLE");

        for (PortMap portMap : PortMap.values())
        {
            settings.put("PORTMAP." + portMap.name(), Integer.toString(portMap.value));
        }

        // ...
    }

    public static boolean transferSettings()
    {
        loadSettings();
        Settings.appendBotSettings(settings);
        return true;
    }

    public enum PortMap
    {
        XBOX_DRIVER_CONTROLLER(0);

        int value;

        PortMap(int value)
        {
            this.value = value;
        }
    }

    public boolean initializeSubsystems()
    {
        transferSettings();

        SmartDashBoardIO.getInstance();
        return true;
    }

}
