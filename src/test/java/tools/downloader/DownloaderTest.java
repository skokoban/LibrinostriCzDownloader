package tools.downloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DownloaderTest {
/*=================================================================================================
                                                Attributes
=================================================================================================*/
  private static final Path TMP_FILE = Path.of(System.getProperty("java.io.tmpdir") +
                                       File.separator +
                                       "testRss.php");
/*=================================================================================================
                                                  Constructor
=================================================================================================*/
/*=================================================================================================
                                                Methods
=================================================================================================*/
/*
  @Test
  void passWhenFileFromUrlSuccesfullyCopied() throws IOException {
                                                              // has 6387 bytes
    InputStream fakeInputStream = new FileInputStream("src/test/resources/testRSS.php");
    URL urlMock = Mockito.mock(URL.class);
    Mockito.when(urlMock.openStream()).thenReturn(fakeInputStream);
    DownloaderProvider provider = new DownloaderProvider();

    long length = provider.download(urlMock, TMP_FILE);

    assertEquals(6387, length);
  }

  @Test
  void passWhenTargetFileIsHasZeroLength() throws IOException {
    Path emptyFile = Files.createTempFile("empty", ".php");
    InputStream inputStream = new FileInputStream(emptyFile.toFile());
    URL urlMock = Mockito.mock(URL.class);
    Mockito.when(urlMock.openStream()).thenReturn(inputStream);
    DownloaderProvider provider = new DownloaderProvider();

    long length = provider.download(urlMock, TMP_FILE);

    assertEquals(0, length);
  }
*/

  @AfterAll
  public static void deleteTestFile() throws IOException {
    Files.deleteIfExists(TMP_FILE);
  }
}
