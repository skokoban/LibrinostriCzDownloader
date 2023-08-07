package main;

import java.io.IOException;

/**
 * The <code>Librinostri</code> class represents website librinostri.catholica.cz
 * where are stores e-books with cathlolic interest.
 */
public class Librinostri {

  public static final String CHECKSUM = "checksum";
  public static final String RSS_LINK = "rssURL";

/*=================================================================================================
                                                Methods
=================================================================================================*/
  /**
   * Inspect the book subsite for download links to all PDF´s related to the book.
   *
   * @param bookURL URL to information about the book on librinostri.catholica.cz.
   * @return list of Strings with URLs to direct download PDFs.
   * @throws IOException           if website with download links is not reachable
   */
  /*public ArrayList<String> findDownloadLinks(String bookURL) throws IOException {
    IHTMLDocument ihtmlDocument = new HTMLDocument();
    Document htmlDocument = ihtmlDocument.get(bookURL);
    return DownloadLinksParser.getDownloadLinks(htmlDocument);
  }*/

  public String retrieveName(String url) {
    int lastSlash = url.lastIndexOf("/");
    return url.substring(lastSlash + 1);
  }
}
