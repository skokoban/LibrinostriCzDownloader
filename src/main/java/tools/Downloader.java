package tools;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class Downloader {
/*======================================================================================================================
                                                Attributes
======================================================================================================================*/
/*======================================================================================================================
                                                Methods
======================================================================================================================*/
    public void download(Map<String, File> fileMap) throws IOException {
        for (Map.Entry<String, File> fileEntry: fileMap.entrySet()) {
            HttpURLConnection connection = (HttpURLConnection) new URL(fileEntry.getValue().getAbsolutePath()).openConnection();
            connection.connect();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                // nepodarilo sa spojenie
                throw new IOException("HTTP error code " + connection.getResponseCode());
            }
                // stiahnutie suboru
            InputStream input  = connection.getInputStream();
            OutputStream output = new FileOutputStream(fileEntry.getValue());
            byte[]       data   = new byte[4096];
            int          count;
            while ((count = input.read(data)) != -1) {
                output.write(data, 0, count);
            }
            output.close();
        }
    }
}
