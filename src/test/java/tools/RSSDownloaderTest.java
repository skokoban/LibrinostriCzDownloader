package tools;

import org.junit.jupiter.api.Test;

public class RSSDownloaderTest {
    @Test
    public void test() {
        RSSDownloader rssDownloader = new RSSDownloader();
        System.out.println(rssDownloader.downloadRSS());
    }
}
