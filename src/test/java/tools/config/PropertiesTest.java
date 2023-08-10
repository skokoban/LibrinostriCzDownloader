package tools.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PropertiesTest {
  private File tempConfigFile;

  @BeforeEach
  void createTempConfigFile() throws IOException {
    tempConfigFile = File.createTempFile("tempConfig", ".txt");
    tempConfigFile.deleteOnExit();
  }

  @Test
  void passWhenPropertyReadSuccesfully() throws IOException {
    createTempConfigFile();
    java.util.Properties properties = new java.util.Properties();
    properties.setProperty("testKey", "testValue");
    try(FileWriter fileWriter = new FileWriter(tempConfigFile)) {
      properties.store(fileWriter, "test");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    Properties propertiesProvider = new Properties(tempConfigFile);

    String testProperty = propertiesProvider.getProperty("testKey");

    assertEquals("testValue", testProperty);
  }

  @Test
  void passWhenTwoPropertiesReadSuccesfully() throws IOException {
    createTempConfigFile();
    java.util.Properties properties = new java.util.Properties();
    properties.setProperty("testKey", "testValue");
    properties.setProperty("testKey2", "testValue2");
    try(FileWriter fileWriter = new FileWriter(tempConfigFile)) {
      properties.store(fileWriter, "test");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    Properties propertiesProvider = new Properties(tempConfigFile);
    String testValue = "testValue";
    String testValue2 = "testValue2";
    String[] testValues = {testValue, testValue2};
    String testProperty = propertiesProvider.getProperty("testKey");
    String testProperty2 = propertiesProvider.getProperty("testKey2");
    String[] testProperties = {testProperty, testProperty2};

    String valuesAsString = Arrays.toString(testValues);
    String propertiesAsString = Arrays.toString(testProperties);

    assertEquals(valuesAsString, propertiesAsString);
  }

  @Test
  void passWhenPropertyWrittenSuccesfully() throws IOException {
    createTempConfigFile();
    Properties properties = new Properties(tempConfigFile);
    properties.setProperty("testKey", "testValue");

    String testProperty = properties.getProperty("testKey");

    assertEquals("testValue", testProperty);
  }

  @Test
  void passWhenAfterAddingNewPropertyOldOnesPersits() throws IOException {
    createTempConfigFile();
    Properties properties = new Properties(tempConfigFile);
    properties.setProperty("testKey", "testValue");
    properties.setProperty("testKey2", "testValue2");
    String firstProperty = properties.getProperty("testKey");
    String secondProperty = properties.getProperty("testKey2");

    boolean isNull = firstProperty.equals("null");

    assertFalse(isNull);
  }

  @Test
  void passWhenNullInputStreamReturnsEmptyString() {
    Properties properties = new Properties(tempConfigFile) {
      @Override
      protected FileInputStream getFileInputStream(File configFile) {
        return null;
      }
    };

    String result = properties.getProperty("testKey");

    assertEquals("", result);
  }

  @Test
  void passWhenIOErrorReturnsEmptyString() {
    Properties properties = new Properties(tempConfigFile) {
      @Override
      protected FileInputStream getFileInputStream(File configFile) throws IOException {
        throw new IOException("Simulated IOException.");
      }
    };

    String result = properties.getProperty("testKey");

    assertEquals("", result);
  }

  @Test
  void passWhenNullLoadPropertiesReturnsEmptyString() {
    Properties properties = new Properties(tempConfigFile) {
      @Override
      protected void loadProperties(java.util.Properties prop, FileInputStream Stream) throws IOException {
        throw new IOException("Simulated loadProperties error.");
      }
    };

    String result = properties.getProperty("testKey");

    assertEquals("", result);
  }

  @Test
  void passWhenPropertyWasSetSuccesfully() {
    Properties properties = new Properties(tempConfigFile);
    properties.setProperty("testKey", "testValue");

    String resultValue = properties.getProperty("testKey");

    assertEquals("testValue", resultValue);
  }

  @Test
  void passWhenPropertyWasUpdatedSuccesfully() {
    Properties properties = new Properties(tempConfigFile);
    properties.setProperty("testKey", "testValue");
    properties.setProperty("testKey2", "testValue2");

    String resultValue = properties.getProperty("testKey2");

    assertEquals("testValue2", resultValue);
  }
}
