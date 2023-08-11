package tools.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Provides manipulation with key-value pairs. These pairs are stored in text file. Gives ability
 * to set pairs and to search for value for given key.
 */
@SuppressWarnings("UnusedReturnValue")
public class Properties {
/*=================================================================================================
                                                Attributess
=================================================================================================*/
  private final File configFile;
  private static final String PROPERTIES_COMMENT = """
      This file is automatically generated configuration file.
      Do not modify keys in case it may corrupt appplication.""";
/*=================================================================================================
                                                Constructors
=================================================================================================*/
  public Properties(File configFile) {
    this.configFile = configFile;
  }
/*=================================================================================================
                                                Methods
=================================================================================================*/
  /**
   * Returns value from properties file for given key, if the key exists. In case key not exists
   * then returns null.
   * @param key key for which value should be returned
   * @return String with value apropriate for given key, if there is no such key returns null.
   * Returns empty string if other error occurs.
   */
  public String getProperty(String key) {
    java.util.Properties properties = new java.util.Properties();
    try (FileInputStream fileInputStream = getFileInputStream(configFile)) {
      loadProperties(properties, fileInputStream);
    } catch (NullPointerException e) {
      return "";
    } catch (IOException e) {
      return "";
    }
    return properties.getProperty(key);
  }

  protected FileInputStream getFileInputStream(File configFile) throws IOException {
    return new FileInputStream(configFile);
  }

  /**
   * Create or update key-value pair. Create new pair if given key not exists. Update value
   * for already existing key.
   * @param key key
   * @param value value for the key.
   * @return true if property was set succesfully, otherwise false.
   */
  public boolean setProperty(String key, String value) {
    java.util.Properties properties = new java.util.Properties();
    try (FileInputStream inputStream = getFileInputStream(configFile)) {
      loadProperties(properties, inputStream);
    } catch (IOException e) {
      return false;
    }
    try (OutputStream outputStream = getOutputStream()) {
      properties.setProperty(key, value);
      properties.store(outputStream, PROPERTIES_COMMENT);
    } catch (IOException e) {
      return false;
    }
    return true;
  }

  protected void loadProperties(java.util.Properties prop, FileInputStream inputStream) throws IOException {
    prop.load(inputStream);
  }

  protected FileOutputStream getOutputStream() throws IOException {
    return new FileOutputStream(configFile);
  }
}