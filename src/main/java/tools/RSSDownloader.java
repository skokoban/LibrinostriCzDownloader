package tools;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RSSDownloader {
/*======================================================================================================================
                                                Attributes
======================================================================================================================*/
    private final String FILE_NAME = "rss.xml";
/*======================================================================================================================
                                                Methods
======================================================================================================================*/
    public File downloadRSS(URL RSSURL) {
        File rssFile = new File(FILE_NAME);
        if (rssFile.exists()) {
            rssFile.delete();
        }
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(RSSURL.openStream());
            BufferedReader    reader            = new BufferedReader(inputStreamReader);
            FileWriter        writer            = new FileWriter(FILE_NAME);

            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                writer.write(inputLine);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rssFile;
    }
}
