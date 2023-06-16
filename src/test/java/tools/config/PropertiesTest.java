package tools.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Properties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PropertiesTest {
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @AfterEach
  public void tearDown() {
    System.setOut(standardOut);
  }

  File createTempConfigFile() throws IOException {
    File tempConfigFile = File.createTempFile("tmpConfig", ".txt");
    tempConfigFile.deleteOnExit();
    return tempConfigFile;
  }

  @Test
  void passWhenPropertyReadSuccesfully() throws IOException {
    createTempConfigFile();
    Properties properties = new Properties();
    properties.setProperty("testKey", "testValue");
    try(FileWriter fileWriter = new FileWriter(createTempConfigFile())) {
      properties.store(fileWriter, "test");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    IProperties propertiesProvider = new PropertiesProvider(createTempConfigFile());

    String testProperty = propertiesProvider.getProperty("testKey");

    assertEquals("testValue", testProperty);
  }
  @Test
  void passWhenTwoPropertiesReadSuccesfully() throws IOException {
    createTempConfigFile();
    Properties properties = new Properties();
    properties.setProperty("testKey", "testValue");
    properties.setProperty("testKey2", "testValue2");
    try(FileWriter fileWriter = new FileWriter(createTempConfigFile())) {
      properties.store(fileWriter, "test");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    IProperties propertiesProvider = new PropertiesProvider(createTempConfigFile());
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
  void passWhenEmptyStringReturnedAfterException() throws IOException {
    IProperties propertiesProvider = new PropertiesProvider(createTempConfigFile());

    String testProperty = propertiesProvider.getProperty("testKey");

    assertEquals("", testProperty);
  }

  @Test
  void passWhenPropertyWrittenSuccesfully() throws IOException {
    createTempConfigFile();
    IProperties propertiesProvider = new PropertiesProvider(createTempConfigFile());
    propertiesProvider.setProperty("testKey", "testValue");

    String testProperty = propertiesProvider.getProperty("testKey");

    assertEquals("testValue", testProperty);
  }

  @Test
  void passWhenAfterAddingNewPropertyOldOnesPersits() throws IOException {
    createTempConfigFile();
    IProperties propertiesProvider = new PropertiesProvider(createTempConfigFile());
    propertiesProvider.setProperty("testKey", "testValue");
    propertiesProvider.setProperty("testKey2", "testValue2");
    String firstProperty = propertiesProvider.getProperty("testKey");
    String secondProperty = propertiesProvider.getProperty("testKey2");

    boolean isNull = firstProperty.equals("null");

    assertFalse(isNull);
  }

  @Test
  void passWhenNullInputStreamReturnsEmptyString() throws IOException {
    PropertiesProvider propertiesProvider = new PropertiesProvider(createTempConfigFile()) {
      @Override
      protected FileInputStream getFileInputStream(File configFile) {
        return null;
      }
    };

    String result = propertiesProvider.getProperty("testKey");

    assertEquals("", result);
  }

  @Test
  void passWhenIOErrorReturnsEmptyString() throws IOException {
    PropertiesProvider propertiesProvider = new PropertiesProvider(createTempConfigFile()) {
      @Override
      protected FileInputStream getFileInputStream(File configFile) throws IOException {
        throw new IOException("Simulated IOException.");
      }
    };

    String result = propertiesProvider.getProperty("testKey");

    assertEquals("", result);
  }

  @Test
  void passWhenNullLoadPropertiesReturnsEmptyString() throws IOException {
    PropertiesProvider propertiesProvider = new PropertiesProvider(createTempConfigFile()) {
      @Override
      protected void loadProperties(Properties prop, FileInputStream Stream) throws IOException {
        throw new IOException("Simulated loadProperties error.");
      }
    };

    String result = propertiesProvider.getProperty("testKey");

    assertEquals("", result);
  }

  @Test
  void passWhenPropertyWasSetSuccesfully() throws IOException {
    PropertiesProvider propertiesProvider = new PropertiesProvider(createTempConfigFile());
    propertiesProvider.setProperty("testKey", "testValue");

    String resultValue = propertiesProvider.getProperty("testKey");

    assertEquals("testValue", resultValue);
  }

  @Test
  void passWhenPropertyWasUpdatedSuccesfully() throws IOException {
    PropertiesProvider propertiesProvider = new PropertiesProvider(createTempConfigFile());
    propertiesProvider.setProperty("testKey", "testValue");
    propertiesProvider.setProperty("testKey2", "testValue2");

    String resultValue = propertiesProvider.getProperty("testKey2");

    assertEquals("testValue2", resultValue);
  }

  @Test
  void passWhenNullInputStreamThrowsNullPointerException() {
    PropertiesProvider propertiesProvider = new PropertiesProvider(null);
    propertiesProvider.setProperty("testKey", "testValue");

    String resultValue = outputStreamCaptor.toString().trim();

    assertEquals("Property impossible to set in case of config file not found", resultValue);
  }

  @Test
  void passWhenOutoutStreamThrowsIOException() throws IOException {
    PropertiesProvider propertiesProvider = new PropertiesProvider(createTempConfigFile()) {
      @Override
      protected FileOutputStream getOutputStream() throws IOException {
        throw new IOException("Simulated IOException");
      }
    };
    propertiesProvider.setProperty("testKey", "testValue");

    String resultValue = outputStreamCaptor.toString().trim();

    assertEquals("Property impossible to set in case of config file not found", resultValue);
  }
}
