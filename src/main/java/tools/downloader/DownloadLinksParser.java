package tools.downloader;

import main.Book;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DownloadLinksParser {
/*=================================================================================================
                                                  Attributes
=================================================================================================*/
  private final String ELEMENT_DOWNLOAD_NAME = ".download";
  private final String ATTRIBUTE_LINK_NAME = "abs:href";
/*=================================================================================================
                                                Methods
=================================================================================================*/
  /**
   * Inspect the book subsite for download links to all PDF´s related to the book.
   * @param rawBooks URL to information about the book on librinostri.catholica.cz.
   * @return list of object <code>Book</code> with additional links to each PDFs
   * @throws IOException if link to the book is not reachable.
   */
  public void parseDownloadLinks(ArrayList<Book> rawBooks) throws IOException {
    IConnection iConnection = new ConnectionProvider();
    for (Book book: rawBooks) {
      Connection connection   = iConnection.getConnection(book.getLINK());
      Document   document     = connection.get();
      Elements elementsDownload = document.select(ELEMENT_DOWNLOAD_NAME);

      ArrayList<String> dLinks  = new ArrayList<>();
      for (Element link : elementsDownload) {
        String dLink = link.attr(ATTRIBUTE_LINK_NAME);
        if (!dLink.contains("/")) {
          throw new IOException();
        }
        dLinks.add(dLink);
      }
      book.setDOWNLOAD_LINKS(dLinks);
    }
  }
}
