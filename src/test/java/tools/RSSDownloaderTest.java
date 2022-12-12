package tools;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.net.URL;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class RSSDownloaderTest {

    private final String URL = "http://librinostri.catholica.cz/rss.php";
    File rssFile = new File("test" + File.separator + "java" + File.separator + "resources" + File.separator + "rss.php");

/*
    @Test
    @DisplayName("Test not empty file.")
    public void passWhenFileLengthIsNotZero() {
        RSSDownloader rssDownloader = new RSSDownloader();
        URL mockedURL = mock(java.net.URL.class);
    }
*/
}
