package tools.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ConfigTest {

  Path testDir;
  File testFile;

  @Before
  public void createTestFile() throws IOException {
    testDir = Files.createTempDirectory("test");
    testFile = new File(testDir.toString() + File.separator + "test.properties");
    testFile.createNewFile();
  }

  @After
  public void clean() throws IOException {
    Files.delete(testFile.toPath());
    Files.delete(testDir);
  }
  @Test
  public void passWhenInstancesIsNotNull() {
    Config instance1;
    Config instance2;

    instance1 = Config.getInstance(testFile);
    instance2 = Config.getInstance(testFile);

    assertNotNull(instance1);
    assertNotNull(instance2);
  }

  @Test
  public void passWhenInstanceIsSame() {
    Config instance1;
    Config instance2;

    instance1 = Config.getInstance(testFile);
    instance2 = Config.getInstance(testFile);

    assertSame(instance1, instance2);
  }

  @Test
  public void passWhenFileArgumentIsNull() throws IOException {

  }
}
