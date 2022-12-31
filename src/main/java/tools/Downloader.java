package tools;

import ui.Printer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Downloader {
/*======================================================================================================================
                                                Methods
======================================================================================================================*/

    /**
     * Downloads files from given URL to given File.
     * @param fileMap with key of URL in String format. Value should be File object where file will be stored.
     * @throws IOException if when downloading fails.
     */
    public void downloadFiles(Map<String, File> fileMap) throws IOException {
        for (Map.Entry<String, File> fileEntry: fileMap.entrySet()) {
            Printer.printDownloading(fileEntry.getValue());

            URL url = new URL(fileEntry.getKey());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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
            Printer.printOK();
        }
    }

    /**
     * Copy text content from remote file to local file. Copying is proceeded by single line systematically.
     * @param url  link to remote text file
     * @param path string where local file should be located
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
                bos.write("\n".getBytes());
            }
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
