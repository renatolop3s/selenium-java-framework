package br.com.renatolop3s.sjf.util;

public class MessageReader extends PropertiesReader {

    public static final String FILE_PATH = "src/test/resources/messages.properties";

    private MessageReader() {}

    public static String getMessage(String key) {
        setFilePath(FILE_PATH);
        return getProperty(key);
    }
}
