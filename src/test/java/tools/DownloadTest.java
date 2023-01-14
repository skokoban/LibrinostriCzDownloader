package tools;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.*;

public class DownloadTest {
/*======================================================================================================================
                                                    Prepare Methods
======================================================================================================================*/
private File getTempPDF() throws IOException {
    File tmpPDF = File.createTempFile("tmp", ".pdf");
    tmpPDF.deleteOnExit();
    return tmpPDF;
}

    private HttpURLConnection getMockedConnection() throws IOException {
        HttpURLConnection mockedHttpURLConnection = Mockito.mock(HttpURLConnection.class);
        Mockito.when(mockedHttpURLConnection.getResponseCode()).thenReturn(400);
        return mockedHttpURLConnection;
    }
/*======================================================================================================================
                                                    Test Methods
======================================================================================================================*/
    @Test
    @DisplayName("False on bad response code")
    public void passWhenErrorResponseCodeRecognized() throws IOException {
        assertFalse(new Downloader().downloadFile(getMockedConnection(), getTempPDF()));
    }

}