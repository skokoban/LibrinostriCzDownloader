package tools.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConfigTest {

  public static final String TMP_DIR_PATH = System.getProperty("java.io.tmpdir");
  private Path testFilePath;

  @BeforeEach
  public void createTestPath() {
    testFilePath = Path.of(TMP_DIR_PATH + File.separator + "testDir" + File.separator +
        "testFile");
/*
    testDir = Files.createTempDirectory("test");
    testFile = new File(testDir.toString() + File.separator + "test.properties");
    testFile.createNewFile();
*/
  }

  @AfterAll
  public static void clean() {
/*
    Files.delete(testFile.toPath());
    Files.delete(testDir);
*/
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
  public void PassWhenConfigDirectorySuccesfullyCreated() {

  }
}
