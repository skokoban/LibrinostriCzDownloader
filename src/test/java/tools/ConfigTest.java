/*
package tools;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import org.junit.Test;
import tools.config.Config;

public class ConfigTest {

  @Test
  public void passWhenMethodWorks() throws IOException {
    Path configPath = Files.createTempFile("testConfig", ".properties");
    File configFile = configPath.toFile();
    Config config = new Config();
    config.createDefaultConfig(configFile);
    Properties properties = new Properties();
    OutputStream outputStream = new FileOutputStream(configFile);
    properties.setProperty("downloadFolder", "test");
    properties.store(outputStream, "test");
    InputStream inputStream = new FileInputStream(configFile);
    properties.load(inputStream);
    assertArrayEquals("test".getBytes(), properties.getProperty("downloadFolder").getBytes());
    Files.deleteIfExists(configPath);
  }

  @Test
  public void passWhenFileArgumentIsNull() throws IOException {
    Config config = new Config();
    config.createDefaultConfig(null);
  }
}*/
