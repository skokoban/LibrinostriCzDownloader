package tools;

import main.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DownloadLinksParser {
/*======================================================================================================================
                                                Attributes
======================================================================================================================*/
    private final String DOWNLOAD_ELEMENT_NAME = ".download";
    private final String LINK_ATTRIBUTE_NAME   = "abs:href";
/*======================================================================================================================
                                                Methods
======================================================================================================================*/
    /**
     * Inspect the book subsite for download links to all PDF´s related to the book.
     * @param bookLink URL to information about the book on librinostri.catholica.cz.
     * @return list of Strings with URLs to direct download PDFs.
     * @throws IOException if link to the book is not reachable.
     */
    public ArrayList<Book> parseDownloadLinks(ArrayList<Book> books) throws IOException {
        ArrayList<Book> finalBooks = new ArrayList<>();
        for (Book book: books) {
            Document document         = Jsoup.connect(book.getLINK()).get();
            Elements elementsDownload = document.select(DOWNLOAD_ELEMENT_NAME);
            ArrayList<String> dLinks  = new ArrayList<>();
            for (Element link : elementsDownload) {
                dLinks.add(link.attr(LINK_ATTRIBUTE_NAME));
            }
            finalBooks.add(new Book(book.getTITLE(), book.getLINK(), dLinks));
        }
        return finalBooks;
    }
}
