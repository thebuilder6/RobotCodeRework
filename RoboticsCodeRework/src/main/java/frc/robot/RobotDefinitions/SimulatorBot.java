package frc.robot.RobotDefinitions;

import java.util.HashMap;
import java.util.Map;

import frc.robot.Settings;
import frc.robot.Subsystems.SmartDashBoardIO;

public class SimulatorBot implements RobotDefinition
{
    HashMap<String, String> settings = new HashMap<String, String>();
    HashMap<String, Integer> portList = new HashMap<String, Integer>();

    public enum BotSettings
    {
        NUMBEROFCONTROLLERS("1");

        String value;

        BotSettings(String value)
        {
            this.value = value;
        }
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

    private boolean transferSettings()
    {
        for (BotSettings botSetting : BotSettings.values())
        {
            settings.put(botSetting.name(), botSetting.value);
        }
        Settings.appendBotSettings(settings);
        for (PortMap portMap : PortMap.values())
        {
            portList.put(portMap.name(), portMap.value);
        }
        Settings.appendPortMap(portList);
        return true;
    }

    public boolean initalizeSubsystems()
    {
        transferSettings();

        SmartDashBoardIO.getInstance();
        return true;
    }

}