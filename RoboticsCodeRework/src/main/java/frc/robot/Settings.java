package frc.robot;

import java.util.HashMap;
import java.util.Map;

public class Settings
{
    //Todo Settings Hashmap

    public static HashMap<String, String> settingsList = new HashMap<String, String>();
    public static HashMap<String, Integer> portList = new HashMap<String, Integer>();

    public static void startUp()
    {
        for (GeneralSettings generalSetting : GeneralSettings.values())
        {
            settingsList.put(generalSetting.name(), generalSetting.value);
        }
    }

    public enum GeneralSettings
    {
        DEBUG("false");

        String value;

        GeneralSettings(String value)
        {
            this.value = value;
        }
    }

    public static boolean appendBotSettings(HashMap<String, String> botSettings)
    {

        settingsList.putAll(botSettings);
        return true;
    }

    public static boolean appendPortMap(HashMap<String, Integer> portMap)
    {
        portList.putAll(portMap);
        return true;
    }

}
