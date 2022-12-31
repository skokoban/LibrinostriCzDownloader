package tools;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
/*======================================================================================================================
                                                    Attributes
======================================================================================================================*/
    private static final String PATH_TO_PROPERTIES_FILE = "src/main/resources/config.properties";
/*======================================================================================================================
                                                    Methods
======================================================================================================================*/
    public static String getProperty(String key) {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(PATH_TO_PROPERTIES_FILE)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return properties.getProperty(key);
    }

    public static boolean setProperty(String key, String value) {
        Properties properties = new Properties();
        try (OutputStream outputStream = new FileOutputStream(PATH_TO_PROPERTIES_FILE)) {
            properties.setProperty(key, value);
            properties.store(outputStream, "");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
