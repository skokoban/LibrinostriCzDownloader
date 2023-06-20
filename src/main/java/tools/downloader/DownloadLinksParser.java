package tools.downloader;

import exceptions.LinkNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tools.connection.ConnectionProvider;
import tools.connection.IConnection;
import tools.parser.DocumentProvider;
import tools.parser.IDocument;

public class DownloadLinksParser {
/*=================================================================================================
                                               Attributes
=================================================================================================*/
  private final String ELEMENT_DOWNLOAD_NAME   = ".download";
  private final String ATTRIBUTE_LINK_NAME     = "abs:href";
  private final String SLASH_CHAR = "/";
  private final String EXCEPTION_ERROR_MESSAGE = "Download link was not found";
/*=================================================================================================
                                                Methods
=================================================================================================*/
  /**
   * In elements try to find download links. Finding is based on attribute "abs:href".
   * @param elements elements of website where download link should be found
   * @throws LinkNotFoundException if download link was not found in document
   */
  // da sa otestovat tym z statickej hardcoded html stranky ziskam elementy a poslem sem
  public ArrayList<String> getDownloadLinks(Elements elements) throws LinkNotFoundException {
    ArrayList<String> downloadLinks  = new ArrayList<>();
    for (Element link : elements) {
      String dLink = link.attr(ATTRIBUTE_LINK_NAME);
      if (!dLink.contains(SLASH_CHAR)) {
        throw new LinkNotFoundException(EXCEPTION_ERROR_MESSAGE);
      }
      downloadLinks.add(dLink);
    }
    return downloadLinks;
  } //todo volajucu metodu treba upravit aby reflektovala tuto zmenenu triedu.

  /**
   * From given link to website download this website as Document. In Document find all
   * elements with name .download.
   * @param link link to website
   * @return elements founded in website
   * @throws IOException if connection to website fails.
   */
  public Elements getElements(String link) throws IOException {
    IConnection iConnection = new ConnectionProvider();
    IDocument   iDocument   = new DocumentProvider();
    Document    document    = iConnection.get(link);
    Elements    elements    = iDocument.get(document, ELEMENT_DOWNLOAD_NAME);
    return elements;
  }
}
