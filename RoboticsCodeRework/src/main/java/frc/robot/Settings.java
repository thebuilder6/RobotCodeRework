package frc.robot;

import java.util.HashMap;
import java.util.Map;

public class Settings
{
    //Todo Settings Hashmap
    private static Map<String, Object> settings = new HashMap<>();

    public static void loadSettings()
    {
        // Load settings from a file or any other source
        // and populate the settings map
        settings.put("key1", "value1");
        settings.put("key2", 12345);
        // ...
    }

    public static <T> T getSetting(String key)
    {
        return (T)settings.get(key);
    }

    public static <T> T getSetting(String key, Class<T> type)
    {
        return type.cast(settings.get(key));
    }

    public static <T> void setSetting(String key, T value)
    {
        settings.put(key, value);
    }

    public static <T> void appendBotSettings(Map<String, T> botSettings)
    {
        settings.putAll(botSettings);
    }

}
