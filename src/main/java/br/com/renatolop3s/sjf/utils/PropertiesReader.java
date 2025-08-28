package br.com.renatolop3s.sjf.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
public abstract class PropertiesReader {

    private static final Map<String, Properties> propertiesMap = new HashMap<>();
    private static String filePath;

    public static void setFilePath(String filePath) {
        PropertiesReader.filePath = filePath;
    }

    public static boolean fileExists() {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("Please use the setFilePath method to set the file location first.");
        }
        File file = new File(filePath);
        return file.exists();
    }

    public static synchronized Properties getProperties() {
        return propertiesMap.computeIfAbsent(filePath, f -> {
            log.info("Loading properties file: {}", filePath);
            try (FileInputStream fileInputStream = new FileInputStream(f)) {
                Properties props = new Properties();
                props.load(fileInputStream);
                return props;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    public static String getProperty(String key) {
        Properties properties = getProperties();
        return properties == null ? null : properties.getProperty(key);
    }
}
