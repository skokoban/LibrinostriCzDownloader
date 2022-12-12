package tools;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DownloaderTest {

/*======================================================================================================================
                                                Prepare methods
======================================================================================================================*/

/*======================================================================================================================
                                                Test methods
======================================================================================================================*/
    @Test
    @DisplayName("Completed download.")
    public void passWhenFileIsDownloaded() {
        assertTrue(new Downloader().downloadTxtFile(Mockito.any(), Mockito.anyString()));
    }
/*======================================================================================================================
                                                After methods
======================================================================================================================*/
}