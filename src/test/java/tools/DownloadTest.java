package tools;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

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

    private InputStream getTestInputStream(int numOfBytes) throws IOException {
        File tempFile = File.createTempFile(numOfBytes + "Bytes", "pdf");
        tempFile.deleteOnExit();
        try (OutputStream outputStream = new FileOutputStream(tempFile)) {
            for (int i = 0; i < numOfBytes; i++) {
                outputStream.write("h".getBytes());
            }
        }
        return new FileInputStream(tempFile);
    }

    private InputStream getTestInputstreamForTxtFile() throws FileNotFoundException {
        return new FileInputStream("src/test/resources/testRSS.php");
    }

    private URL getMockedURL() throws IOException {
        URL mockedURL = Mockito.mock(URL.class);
        Mockito.when(mockedURL.openStream()).thenReturn(getTestInputstreamForTxtFile());
        return mockedURL;
    }

    private File getTempXMLFile() throws IOException {
        File XMLTempFile = File.createTempFile("xmltemp", ".xml");
        XMLTempFile.deleteOnExit();
        return XMLTempFile;
    }

    private HttpURLConnection getMockedConnection(int responsCode, int numOfBytesOfSourceFile) throws IOException {
        HttpURLConnection mockedHttpURLConnection = Mockito.mock(HttpURLConnection.class);
        Mockito.when(mockedHttpURLConnection.getResponseCode()).thenReturn(responsCode);
        Mockito.when(mockedHttpURLConnection.getInputStream()).thenReturn(getTestInputStream(numOfBytesOfSourceFile));
        return mockedHttpURLConnection;
    }
/*======================================================================================================================
                                                    Test Methods
======================================================================================================================*/
/*
    @Test
    @DisplayName("Return false on bad response code")
    public void passWhenErrorResponseCodeRecognized() throws IOException {
        assertFalse(new Downloader().downloadFile(getMockedConnection(400, 0), getTempPDF()));
    }

    @Test
    @DisplayName("File should has length zero")
    public void passWhenTargetFileHasZeroLength() throws IOException {
        File testFile = getTempPDF();
        new Downloader().downloadFile(getMockedConnection(200, 0), testFile);
        assertEquals(0, testFile.length());
    }

    @Test
    @DisplayName("File has length 4096")
    public void passWhenTargetFileHas4096Length() throws IOException {
        File testFile = getTempPDF();
        new Downloader().downloadFile(getMockedConnection(200, 4096), testFile);
        assertEquals(4096, testFile.length());
    }

    @Test
    @DisplayName("File has length 4097")
    public void passWhenTargetFileHas4097Length() throws IOException {
        File testFile = getTempPDF();
        new Downloader().downloadFile(getMockedConnection(200, 4097), testFile);
        assertEquals(4097, testFile.length());
    }

    @Test
    @DisplayName("Equivalency of length of source file and target file")
    public void passWhenTargetFileLengthIsEquivalentToSourceFileLength() throws IOException {
        File testFile = getTempXMLFile();
        new Downloader().downloadTxtFile(getMockedURL(), testFile);
        long sourceFileLength = new File("src/test/resources/testRSS.php").length();
        assertEquals(sourceFileLength, testFile.length()-1); // -1 because in parsed file is new line char at the end
    }
*/
}