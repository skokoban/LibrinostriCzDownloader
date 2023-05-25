package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.zip.CRC32;

/**
 * The <code>Librinostri</code> class represents website librinostri.catholica.cz where are stores e-books
 * with cathlolic interest.
 */
public class Librinostri {

/*==================================================================================================
                                                    Attributes
==================================================================================================*/
    private final LinkedList<Book> BOOKS_INFO           = new LinkedList<>();
    private final String           URL_BOOKS_INFO       = "http://librinostri.catholica.cz/rss.php";
    private final String           ELEMENT_ITEM_TO_FIND = "item";

/*==================================================================================================
                                                Constructors
==================================================================================================*/


/*==================================================================================================
                                                Getters
==================================================================================================*/

    public LinkedList<Book> getBOOKS_INFO() {
        return BOOKS_INFO;
    }
/*==================================================================================================
                                                Methods
==================================================================================================*/
    /**
     * Inspect the book subsite for download links to all PDF´s related to the book.
     * @param bookLink URL to information about the book on librinostri.catholica.cz.
     * @return list of Strings with URLs to direct download PDFs.
     * @throws IOException if link to the book is not reachable.
     */
    public ArrayList<String> findDownloadLinks(String bookLink) throws IOException {
        ArrayList<String> downloadLinks    = new ArrayList<>();
        Document          document         = Jsoup.connect(bookLink).get();
        Elements          elementsDownload = document.select(".download");  // hlada elementy hodnota download
        for (org.jsoup.nodes.Element link : elementsDownload) {
            downloadLinks.add(link.attr("abs:href"));                   // do zoznamu pridava absolutne URL
        }
        return downloadLinks;
    }

    protected long countChecksum(File rssFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(rssFile);
        StringBuilder fileSB = new StringBuilder();
        while (scanner.hasNextLine()) {
            fileSB.append(scanner.nextLine());
        }
        String fileString = fileSB.toString();
        CRC32 rssFileCrc32 = new CRC32();
        rssFileCrc32.update(fileString.getBytes());
        return rssFileCrc32.getValue();
    }
}
