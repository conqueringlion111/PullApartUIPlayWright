package com.pullapart.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    public static void loadProperties() {
        if (properties == null) {
            properties = new Properties();

            // get environment from system property, default to "qa"
            String env = System.getProperty("env", "prod").toLowerCase();
            // 2. Build file path
            String filePath = "src/test/resources/config/" + env + ".properties";

            try (FileInputStream input = new FileInputStream(filePath)) {
                properties.load(input);
                System.out.println("Loaded environment: " + env);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load environment properties file: " + filePath, e);
            }
        }
    }

    public static String get(String key) {
        if (properties == null) {
            loadProperties();
        }
        // system property > env properties file
        return System.getProperty(key, properties.getProperty(key));
    }
}
