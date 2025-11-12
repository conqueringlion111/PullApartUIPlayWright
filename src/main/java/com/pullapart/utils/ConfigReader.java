package com.pullapart.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    public static void loadProperties() {
        if (properties == null) {
            properties = new Properties();
            try (FileInputStream input = new FileInputStream("src/test/resources/config/testconfig.properties")) {
                properties.load(input);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load testconfig.properties file", e);
            }
        }
    }

    public static String get(String key) {
        if (properties == null) {
            loadProperties();
        }
        return properties.getProperty(key);
    }
}
