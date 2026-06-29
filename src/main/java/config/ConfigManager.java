package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//LOADS THE APP CONFIG FROM THE PROPERTIESFILE
public final class ConfigManager {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //OBATINS THE VALUE
    public static String get(String key) {
        return properties.getProperty(key);
    }
}