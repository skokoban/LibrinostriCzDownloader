package tools.downloader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

public class DownloaderTest {
/*=================================================================================================
                                                Attributes
=================================================================================================*/
  private static final Path TMP_FILE = Path.of(System.getProperty("java.io.tmpdir") +
                                       File.separator +
                                       "testRss.php");
/*=================================================================================================
                                                Methods
=================================================================================================*/

  @Test
  void test() throws IOException {
    IDownloader iDownloader = new DownloaderProvider();
    long length = iDownloader.download("https://librinostri.catholica.cz/rss.php", TMP_FILE);
    System.out.println(length);
  }

  @AfterAll
  public static void deleteTestFile() throws IOException {
    Files.deleteIfExists(TMP_FILE);
  }
}
