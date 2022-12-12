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
    public void downloadFile(Map<String, File> fileMap) throws IOException {
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

    /**
     * Copy text content from remote file to local file. Copying is proceeded by single line systematically.
     * @param url link to remote text file
     * @param path string where local file should be located
     * @return true if whole content was copied, false otherwise.
     */
    public boolean downloadTxtFile(URL url, String path) {
/*
        File txtFile = new File(path);
        if (txtFile.exists()) {
            txtFile.delete();
        }
*/
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
            BufferedReader    reader            = new BufferedReader(inputStreamReader);
            FileWriter        writer            = new FileWriter(path);

            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                writer.write(inputLine);
            }
            reader.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
