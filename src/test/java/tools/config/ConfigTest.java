package tools.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConfigTest {

  public static final String TMP_DIR_PATH = System.getProperty("java.io.tmpdir");
  private static File testFile;
  private static File testFileDir;

  @BeforeEach
  public void createTestFile() {
    testFileDir = new File(TMP_DIR_PATH + File.separator + "testDir");
    testFile = new File(testFileDir + File.separator + "testFile");
  }

  @AfterAll
  public static void clean() {
    testFile.delete();
    testFileDir.delete();
  }
  @Test
  void passWhenInstancesIsNotNull() {
    Config instance1;
    Config instance2;

    instance1 = Config.getInstance(testFile);
    instance2 = Config.getInstance(testFile);

    assertNotNull(instance1);
    assertNotNull(instance2);
  }

  @Test
  void passWhenInstanceIsSame() {
    Config instance1;
    Config instance2;

    instance1 = Config.getInstance(testFile);
    instance2 = Config.getInstance(testFile);

    assertSame(instance1, instance2);
  }

  @Test
  void PassWhenConfigDirectorySuccesfullyCreated() {
    Config instance = Config.getInstance(testFile);
    instance.exists();

    boolean result = testFileDir.isDirectory();

    assertTrue(result);
  }

  @Test
  void PassWhenConfigFileSuccesfullyCreated() {
    Config instance = Config.getInstance(testFile);
    instance.exists();

    boolean result = testFile.isFile();

    assertTrue(result);
  }

  @Test
  void PassWhenPropertyDownloadFolderInsertedSuccesfully() throws IOException {
    testFileDir.mkdirs();
    testFile.createNewFile();
    Config instance = Config.getInstance(testFile);
    Properties mockProperties = new Properties(testFile);
    instance.fillDefaultValues(mockProperties);

    String result = mockProperties.getProperty("downloadFolder");

    assertEquals("/home/marceld/librinostri-downloader", result);
  }

  @Test
  void PassWhenPropertyRSSLocationInsertedSuccesfully() throws IOException {
    testFileDir.mkdirs();
    testFile.createNewFile();
    Config instance = Config.getInstance(testFile);
    Properties mockProperties = new Properties(testFile);
    instance.fillDefaultValues(mockProperties);

    String result = mockProperties.getProperty("rssLocation");

    assertEquals("/tmp/rss.php", result);
  }
}
