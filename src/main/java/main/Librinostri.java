package main;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * The <code>Librinostri</code> class represents website librinostri.catholica.cz where are stores e-books
 * with cathlolic interest.
 */
public class Librinostri {

/*======================================================================================================================
                                                    Attributes
======================================================================================================================*/
    private final LinkedList<Book> BOOKS_INFO           = new LinkedList<>();
    private final String           URL_BOOKS_INFO       = "http://librinostri.catholica.cz/rss.php";
    private final String           ELEMENT_ITEM_TO_FIND = "item";

/*======================================================================================================================
                                                Constructors
======================================================================================================================*/

    public Librinostri() {
        parseLatestBooks();
    }

/*======================================================================================================================
                                                Getters
======================================================================================================================*/

    public LinkedList<Book> getBOOKS_INFO() {
        return BOOKS_INFO;
    }
/*======================================================================================================================
                                                Methods
======================================================================================================================*/
    /**
     * Method parse list of object of class Book with information about new added e-books
     * from website librinostri.catholica.cz.
     * Information are parsed from xml document
     * stored on <a href="http://librinostri.catholica.cz/rss.php">librinostri.catholica.cz/rss.php</a>.
     */
    public void parseLatestBooks() {
        SAXBuilder saxBuilder = new SAXBuilder();           // spracovanie XML
        URL        url        = null;
        try {
            url = new URL(URL_BOOKS_INFO);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {                                     // zlozitym sposobom vo vetveni postupne najdem knihy pod znackou item
            org.jdom2.Document document       = saxBuilder.build(url);
            Element            rootElement    = document.getRootElement();
            Element            elementChannel = rootElement.getChild("channel");
            List<Element>      listItems      = elementChannel.getChildren();         // ziskam zoznam vsetkych potomkov
            for (Element listItem : listItems) {
                if (!Objects.equals(listItem.getName(), ELEMENT_ITEM_TO_FIND)) {
                    continue;
                }
                List<Element>     item          = listItem.getChildren();
                String            title         = item.get(0).getValue();
                String            bookLink      = item.get(1).getValue();
                ArrayList<String> downloadLinks = parseDownloadLinks(bookLink);
                Book              book          = new Book(title, downloadLinks);
                BOOKS_INFO.add(book);
            }
        }
        catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Inspect the book subsite for download links to all PDF´s related to the book.
     * @param bookLink URL to information about the book on librinostri.catholica.cz.
     * @return list of Strings with URLs to direct download PDFs.
     * @throws IOException if link to the book is not reachable.
     */
    public ArrayList<String> parseDownloadLinks(String bookLink) throws IOException {
        ArrayList<String> downloadLinks    = new ArrayList<>();
        Document          document         = Jsoup.connect(bookLink).get();
        Elements          elementsDownload = document.select(".download");  // hlada elementy hodnota download
        for (org.jsoup.nodes.Element link : elementsDownload) {
            downloadLinks.add(link.attr("abs:href"));                   // do zoznamu pridava absolutne URL
        }
        return downloadLinks;
    }
}
