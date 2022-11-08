package tools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DownloadLinksParser {
    /**
     * Inspect the book subsite for download links to all PDF´s related to the book.
     * @param bookLink URL to information about the book on librinostri.catholica.cz.
     * @return list of Strings with URLs to direct download PDFs.
     * @throws IOException if link to the book is not reachable.
     */
    public ArrayList<String> parseDownloadLinks(String booksInfo) throws IOException {
        Map<String, ArrayList<String>> books = new HashMap<>();
        for (String bookLink: booksInfo.) {

        }
        Document document         = Jsoup.connect(bookLink).get();
        Elements elementsDownload = document.select(".download");
        for (org.jsoup.nodes.Element link : elementsDownload) {
            downloadLinks.add(link.attr("abs:href"));
        }
        return downloadLinks;
    }
}
