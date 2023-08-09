package tools.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
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
    Properties properties = new Properties();
    properties.setProperty("testKey", "testValue");
    try(FileWriter fileWriter = new FileWriter(tempConfigFile)) {
      properties.store(fileWriter, "test");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    PropertiesProvider propertiesProvider = new PropertiesProvider(tempConfigFile);

    String testProperty = propertiesProvider.getProperty("testKey");

    assertEquals("testValue", testProperty);
  }

  @Test
  void passWhenTwoPropertiesReadSuccesfully() throws IOException {
    createTempConfigFile();
    Properties properties = new Properties();
    properties.setProperty("testKey", "testValue");
    properties.setProperty("testKey2", "testValue2");
    try(FileWriter fileWriter = new FileWriter(tempConfigFile)) {
      properties.store(fileWriter, "test");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    PropertiesProvider propertiesProvider = new PropertiesProvider(tempConfigFile);
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
    PropertiesProvider propertiesProvider = new PropertiesProvider(tempConfigFile);
    propertiesProvider.setProperty("testKey", "testValue");

    String testProperty = propertiesProvider.getProperty("testKey");

    assertEquals("testValue", testProperty);
  }

  @Test
  void passWhenAfterAddingNewPropertyOldOnesPersits() throws IOException {
    createTempConfigFile();
    PropertiesProvider propertiesProvider = new PropertiesProvider(tempConfigFile);
    propertiesProvider.setProperty("testKey", "testValue");
    propertiesProvider.setProperty("testKey2", "testValue2");
    String firstProperty = propertiesProvider.getProperty("testKey");
    String secondProperty = propertiesProvider.getProperty("testKey2");

    boolean isNull = firstProperty.equals("null");

    assertFalse(isNull);
  }

  @Test
  void passWhenNullInputStreamReturnsEmptyString() {
    PropertiesProvider propertiesProvider = new PropertiesProvider(tempConfigFile) {
      @Override
      protected FileInputStream getFileInputStream(File configFile) {
        return null;
      }
    };

    String result = propertiesProvider.getProperty("testKey");

    assertEquals("", result);
  }

  @Test
  void passWhenIOErrorReturnsEmptyString() {
    PropertiesProvider propertiesProvider = new PropertiesProvider(tempConfigFile) {
      @Override
      protected FileInputStream getFileInputStream(File configFile) throws IOException {
        throw new IOException("Simulated IOException.");
      }
    };

    String result = propertiesProvider.getProperty("testKey");

    assertEquals("", result);
  }

  @Test
  void passWhenNullLoadPropertiesReturnsEmptyString() {
    PropertiesProvider propertiesProvider = new PropertiesProvider(tempConfigFile) {
      @Override
      protected void loadProperties(Properties prop, FileInputStream Stream) throws IOException {
        throw new IOException("Simulated loadProperties error.");
      }
    };

    String result = propertiesProvider.getProperty("testKey");

    assertEquals("", result);
  }

  @Test
  void passWhenPropertyWasSetSuccesfully() {
    PropertiesProvider propertiesProvider = new PropertiesProvider(tempConfigFile);
    propertiesProvider.setProperty("testKey", "testValue");

    String resultValue = propertiesProvider.getProperty("testKey");

    assertEquals("testValue", resultValue);
  }

  @Test
  void passWhenPropertyWasUpdatedSuccesfully() {
    PropertiesProvider propertiesProvider = new PropertiesProvider(tempConfigFile);
    propertiesProvider.setProperty("testKey", "testValue");
    propertiesProvider.setProperty("testKey2", "testValue2");

    String resultValue = propertiesProvider.getProperty("testKey2");

    assertEquals("testValue2", resultValue);
  }
}
