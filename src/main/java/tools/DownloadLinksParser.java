package tools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DownloadLinksParser {
    /**
     * Inspect the book subsite for download links to all PDF´s related to the book.
     * @param bookLink URL to information about the book on librinostri.catholica.cz.
     * @return list of Strings with URLs to direct download PDFs.
     * @throws IOException if link to the book is not reachable.
     */
    public ArrayList<String> parseDownloadLinks(String bookLink) throws IOException {
        ArrayList<String> downloadLinks    = new ArrayList<>();
        Document document         = Jsoup.connect(bookLink).get();
        Elements elementsDownload = document.select(".download");
        for (org.jsoup.nodes.Element link : elementsDownload) {
            downloadLinks.add(link.attr("abs:href"));
        }
        return downloadLinks;
    }
}
