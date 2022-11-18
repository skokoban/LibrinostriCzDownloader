package tools;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RSSDownloaderTest {

    private final String URL = "http://librinostri.catholica.cz/rss.php";
    File rssFile = new File("test" + File.separator + "java" + File.separator + "resources" + File.separator + "rss.php");
    InputStreamReader inputStreamReader = new InputStreamReader();
    @Test
    @DisplayName("Test not empty file.")
    public void passWhenFileLengthIsNotZero() throws IOException {
        RSSDownloader rssDownloader = new RSSDownloader();
        URL mockedURL = mock(java.net.URL.class);
        when(mockedURL.openStream()).thenReturn();



        //Files mockedFiles = mock(Files.class);
        //assertNotEquals(0, rssDownloader.downloadRSS(URL).length());
    }
}
