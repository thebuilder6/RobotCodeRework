package frc.robot;

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

    private Settings() {
    }

    /**
     * Loads settings from the settings file.
     */
    public static void loadSettings() {
        loadSettingsFromFile(SETTINGS_FILE);
    }

    // Loads settings from a file
    public static void loadSettingsFromFile(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("fileName cannot be null");
        }
        if (Settings.class.getClassLoader().getResource(fileName) != null) {
            try (InputStream inputStream = Settings.class.getClassLoader()
                    .getResourceAsStream(fileName)) {
                if (inputStream == null) {
                    throw new IOException("Resource not found: " + fileName);
                }
                System.out.println("Loading settings from file: " + fileName + " - " + inputStream.available());
                properties.load(inputStream);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load settings from file: " + fileName);
            }
        } else {
            System.out.println("Loading settings from file: " + RESOURCE_FILE_PATH + fileName + " - "
                    + System.getProperty("user.dir"));
            try (FileInputStream fileInputStream = new FileInputStream(RESOURCE_FILE_PATH + fileName)) {
                properties.load(fileInputStream);
                System.out.println("Settings loaded successfully from file: " + fileName);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load settings from file: " + fileName, e);
            }
        }
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
        persistProperties();
    }

    // Persists the properties to the settings file
    private static void persistProperties() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(SETTINGS_FILE)) {
            properties.store(fileOutputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
