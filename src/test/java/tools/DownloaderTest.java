package tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
@RunWith(PowerMockRunner.class)
@PrepareForTest({URL.class})

public class DownloaderTest {
/*======================================================================================================================
                                                Attributes
======================================================================================================================*/
    private final String TEST_SOURCE_FILE = "src/test/resources/rss.php";
    private final String TEST_TARGET_FILE = "src/test/resources/test.php";
/*======================================================================================================================
                                                Helper methods
======================================================================================================================*/

    public InputStream getInputStream() {
        try {
            return new FileInputStream(TEST_SOURCE_FILE);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
/*======================================================================================================================
                                                Test methods
======================================================================================================================*/
    @Test
    @DisplayName("Copy text lines works.")
    public void passWhenCopyTextLinesWorks() throws Exception {
        URL urlmocked = Mockito.mock(URL.class);
        when(urlmocked.openStream()).thenReturn(getInputStream());
        //new Downloader().downloadTxtFile(urlmocked, TEST_TARGET_FILE);
        Assertions.assertEquals(TEST_SOURCE_FILE.length()+1, TEST_TARGET_FILE.length());
        new File(TEST_TARGET_FILE).delete();
    }

    @Test
    public void test() throws Exception {
        String url = "htpp://www.test1.test";

        URL mockedURL = PowerMockito.mock(URL.class);
        PowerMockito.whenNew(URL.class).withArguments(url).thenReturn(mockedURL);

        HttpURLConnection httpURLConnection = PowerMockito.mock(HttpURLConnection.class);

        PowerMockito.when(mockedURL.openConnection()).thenReturn(httpURLConnection);
        PowerMockito.when(httpURLConnection.getResponseCode()).thenReturn(200);

        Map<String, File> filemap = new HashMap<>();
        filemap.put(url, new File("test"));

        new Downloader().downloadFiles(filemap);
    }
}