package frc.robot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class provides methods to load, get and set settings from a properties file.
 * The settings file is named "settings.properties" and is located in the classpath.
 */
public class Settings {
    // The properties object holds the settings
    private static final Properties properties = new Properties();

    // The name of the settings file
    private static final String SETTINGS_FILE = "settings.properties";
    private static final String RESOURCE_FILE_PATH = "src/main/resources/";
    private static String botSettingsFile;

    /**
     * Loads settings from the settings file.
     */
    public static void loadSettings() {
        loadSettingsFromFile(SETTINGS_FILE);
    }

    public static void loadBotSettings(String botSettingsFile) {
        Settings.botSettingsFile = botSettingsFile;
        loadSettingsFromFile(botSettingsFile);
    }

    /**
     * Loads settings from a file.
     * @param fileName Name of the settings file.
     */
    private static void loadSettingsFromFile(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("fileName cannot be null");
        }

        try (InputStream inputStream = getInputStream(fileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load settings from file: " + fileName, e);
        }
    }

    /**
     * Returns an InputStream for the specified file name.
     * @param fileName The name of the file.
     * @return An InputStream for the specified file name.
     * @throws IOException If the file cannot be found.
     */
    private static InputStream getInputStream(String fileName) throws IOException {
        InputStream inputStream = Settings.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            // If the file is not found in the classpath, try to load it from the resources folder
            // this is useful if the settings file is not in the classpath
            // e.g. when running from the IDE or in Simulation
            inputStream = new FileInputStream(RESOURCE_FILE_PATH + fileName);
        }
        return inputStream;
    }

    /**
     * Gets the value of a setting by its key.
     * @param key The key of the setting.
     * @return The value of the setting.
     */
    public static String getSetting(String key) {
        return properties.getProperty(key);
    }

    /**
     * Gets the value of a setting by its key and casts it to the specified type.
     * @param key The key of the setting.
     * @param type The type to cast the value to.
     * @return The value of the setting casted to the specified type.
     */
    public static <T> T getSetting(String key, Class<T> type) {
        String propertyValue = properties.getProperty(key);
        if (propertyValue == null) {
            return null;
        }
        return cast(propertyValue, type);
    }

    /**
     * Gets the value of a setting by its key. If the setting is not found, returns the default value.
     * @param key The key of the setting.
     * @param defaultValue The default value to return if the setting is not found.
     * @return The value of the setting or the default value.
     */
    public static <T> T getSetting(String key, T defaultValue) {
        String propertyValue = properties.getProperty(key);
        Class<T> type = (Class<T>) defaultValue.getClass(); // Get the type 
        return propertyValue != null ? cast(propertyValue, type) : defaultValue;
    }

    /**
     * Sets a property in the settings file.
     * @param propertyName The name of the property.
     * @param propertyValue The value of the property.
     */
    public static void setProperty(String propertyName, String propertyValue) {
        properties.setProperty(propertyName, propertyValue);
        saveProperties(propertyName);
        //warning slow performance
    }

    // Persists the properties to the settings file
    private static void saveProperties(String propertyName) {
        String propertyValue = properties.getProperty(propertyName);
        if (propertyValue != null) {
            File generalSettingsFile = new File(RESOURCE_FILE_PATH + SETTINGS_FILE);
            if (generalSettingsFile.exists()) {
                persistProperty(generalSettingsFile, propertyName, propertyValue);
            }
            if (botSettingsFile != null) {
                File robotSettingsFile = new File(RESOURCE_FILE_PATH + botSettingsFile);
                if (robotSettingsFile.exists()) {
                    persistProperty(robotSettingsFile, propertyName, propertyValue);
                }
            }
        }
    }

    private static void persistProperty(File settingsFile, String propertyName, String propertyValue) {
        Properties temp_properties = new Properties();
        try (FileInputStream fis = new FileInputStream(settingsFile)) {
            temp_properties.load(fis);
            String settingsPropertyValue = temp_properties.getProperty(propertyName);
            if (!propertyValue.equals(settingsPropertyValue)) {
                temp_properties.setProperty(propertyName, propertyValue);
                temp_properties.store(new FileOutputStream(settingsFile), null);
            }
        } catch (IOException ignored) {
        }
    }

    // TODO Implement this

    // Check if the property exists in the genral settings file

    // Check if the property exists in the robot specific settings file

    // Check if the property value is different from the settings file value

    // Save the property value to the settings file

    private static <T> T cast(String value, Class<T> type) {
        if (type == String.class) {
            return type.cast(value);
        } else if (type == Boolean.class) {
            return type.cast(Boolean.parseBoolean(value));
        } else if (type == Integer.class) {
            return type.cast(Integer.parseInt(value));
        } else if (type == Double.class) {
            return type.cast(Double.parseDouble(value));
        } else if (type == Long.class) {
            return type.cast(Long.parseLong(value));
        } else if (type == Float.class) {
            return type.cast(Float.parseFloat(value));
        } else {
            return type.cast(value);
        }
    }

}
