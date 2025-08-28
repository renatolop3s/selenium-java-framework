package br.com.renatolop3s.sjf.utils;

public class MessageReader extends PropertiesReader {

    public static final String FILE_PATH = "src/test/resources/messages.properties";

    public static String getMessage(String key) {
        setFilePath(FILE_PATH);
        return getProperty(key);
    }
}
