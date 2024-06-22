package frc.robot.RobotDefinitions;

import java.util.HashMap;

import frc.robot.Settings;
import frc.robot.Subsystems.SmartDashBoardIO;

public class SwerveBot implements RobotDefinition
{
    HashMap<String, String> settings = new HashMap<String, String>();

    public enum BotSettings
    {
        NUMBEROFCONTROLLERS("2");

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

    public boolean initializeSubsystems()
    {
        for (BotSettings botSetting : BotSettings.values())
        {
            settings.put(botSetting.name(), botSetting.value);
        }
        Settings.appendBotSettings(settings);

        SmartDashBoardIO.getInstance();

        return true;
    }

}
