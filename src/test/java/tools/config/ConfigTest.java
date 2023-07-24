package tools.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConfigTest {

  public static final String TMP_DIR_PATH = System.getProperty("java.io.tmpdir");
  private static Path testFilePath;
  private static Path testFileDir;

  @BeforeEach
  public void createTestPath() {
    testFileDir = Path.of(TMP_DIR_PATH + File.separator + "testDir");
    testFilePath = Path.of(testFileDir + File.separator + "testFile");
  }

  @AfterAll
  public static void clean() throws IOException {
    Files.delete(testFilePath);
    Files.delete(testFileDir);
  }
  @Test
  public void passWhenInstancesIsNotNull() {
    Config instance1;
    Config instance2;

    instance1 = Config.getInstance(testFilePath);
    instance2 = Config.getInstance(testFilePath);

    assertNotNull(instance1);
    assertNotNull(instance2);
  }

  @Test
  public void passWhenInstanceIsSame() {
    Config instance1;
    Config instance2;

    instance1 = Config.getInstance(testFilePath);
    instance2 = Config.getInstance(testFilePath);

    assertSame(instance1, instance2);
  }

  @Test
  public void PassWhenConfigDirectorySuccesfullyCreated() throws IOException {
    Config instance = Config.getInstance(testFilePath);
    instance.createConfigFile();

    boolean result = Files.isDirectory(testFileDir);

    assertTrue(result);
  }

  @Test
  public void PassWhenConfigFileSuccesfullyCreated() throws IOException {
    Config instance = Config.getInstance(testFilePath);
    instance.createConfigFile();

    boolean result = Files.isRegularFile(testFilePath);

    assertTrue(result);
  }
}
