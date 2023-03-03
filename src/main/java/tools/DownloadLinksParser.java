package tools;

import main.Book;
import org.jsoup.Connection;
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
     * @param books URL to information about the book on librinostri.catholica.cz.
     * @return list of object <code>Book</code> with additional links to each PDFs
     * @throws IOException if link to the book is not reachable.
     */
    public ArrayList<Book> parseDownloadLinks(ArrayList<Book> books) throws IOException {
        ArrayList<Book> finalBooks = new ArrayList<>();
        for (Book book: books) {
            Connection connection     = Jsoup.connect(book.getLINK());
            Document   document       = connection.get();
            Elements elementsDownload = document.select(DOWNLOAD_ELEMENT_NAME);

            ArrayList<String> dLinks  = new ArrayList<>();
            for (Element link : elementsDownload) {
                String dLink = link.attr(LINK_ATTRIBUTE_NAME);
                if (!dLink.contains("/")) {
                    throw new IOException();
                }
                dLinks.add(dLink);
            }
            finalBooks.add(new Book(book.getTITLE(), book.getLINK(), dLinks));
        }
        return finalBooks;
    }
}
