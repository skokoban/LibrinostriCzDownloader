package tools;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

import static org.mockito.Mockito.when;
@RunWith(PowerMockRunner.class)
@PrepareForTest({URL.class})

public class DownloaderTest {
/*======================================================================================================================
                                                Attributes
======================================================================================================================*/
    private static final File TEST_SOURCE_FILE = new File("src/test/resources/testRSS.php");
    private static final File TEST_TARGET_FILE = new File("src/test/resources/target.php");
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
        new Downloader().downloadTxtFile(urlmocked, TEST_TARGET_FILE);
        Assertions.assertEquals(TEST_SOURCE_FILE.length(), TEST_TARGET_FILE.length());
    }

    @AfterAll
    public static void deleteTargetFile() {
        TEST_TARGET_FILE.delete();
    }
}