package tools;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Downloader {
/*======================================================================================================================
                                                Attributes
======================================================================================================================*/
    private final int BYTE_ARRAY_SIZE = 4096;
    private final int END_STREAM_VALUE = -1;
    private final int OFFSET_VALUE = 0;
    private final String NEW_LINE_CHAR = "\n";
/*======================================================================================================================
                                                Methods
======================================================================================================================*/

    /**
     * Download file from given URL connection to given File.
     *
     * @param connection connection to URL
     * @param pdf        File object where downlaoded file will be located
     * @return true if PDF file is successfully downlaoded, otherwire returns false
     * @throws IOException when downloading fails.
     */
    public boolean downloadFile(HttpURLConnection connection, File pdf) throws IOException {
            connection.connect();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return false;
            }                // stiahnutie suboru
            InputStream input   = connection.getInputStream();
            OutputStream output = new FileOutputStream(pdf);
            byte[]       data   = new byte[BYTE_ARRAY_SIZE];
            int          count;
            while ((count = input.read(data)) != END_STREAM_VALUE) {
                output.write(data, OFFSET_VALUE, count);
            }
            output.close();
            return true;
    }

    /**
     * Copy text content from remote file to local file. Copying is proceeded by single line systematically.
     * @param url  link to remote text file
     * @param xmlTempFile File where local file should be located
     */
    public void downloadTxtFile(URL url, File xmlTempFile) {
        try {
            InputStreamReader    inputStreamReader = new InputStreamReader(url.openStream());
            BufferedReader       reader            = new BufferedReader(inputStreamReader);
            FileOutputStream     fileOutputStream  = new FileOutputStream(xmlTempFile);
            BufferedOutputStream bos               = new BufferedOutputStream(fileOutputStream);

            String line;
            while ((line = reader.readLine()) != null) {
                byte[] buffer;
                buffer = line.getBytes(StandardCharsets.UTF_8);
                bos.write(buffer);
                bos.write(NEW_LINE_CHAR.getBytes());
            }
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}