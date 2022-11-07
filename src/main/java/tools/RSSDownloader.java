package tools;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RSSDownloader {
/*======================================================================================================================
                                                Attributes
======================================================================================================================*/
    private final String RSSURL = "http://librinostri.catholica.cz/rss.php";
/*======================================================================================================================
                                                Methods
======================================================================================================================*/
    public File downloadRSS() {
        File rssFile = new File("rss.xml");
        if (rssFile.exists()) {
            rssFile.delete();
        }
        try {
            URL url = new URL(RSSURL);
            Files.copy(url.openStream(), Paths.get("rss.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rssFile;
    }
}
