package tools.parser.linksParser;

import java.util.ArrayList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DownloadLinksParser {

/*=================================================================================================
                                              Attributes
=================================================================================================*/
  private static final String CSS_QUERY_DOWNLOAD      = "a[href*=download]";
  private static final String ATTRIBUTE_LINK_NAME     = "abs:href";
  private static final String LINK_NOT_FOUND_EXCEPTION_ERROR_MESSAGE = "Download link was not found";
/*=================================================================================================
                                                Methods
=================================================================================================*/
  /**
   * In elements try to find download links. Finding is based on attribute "abs:href".
   *
   * @param htmlDocument HTML Document of website where download links are provided
   * @return ArrayList of URLs point to PDF files attached to book.
   * @throws LinkNotFoundException if any of download links was not found in Document.
   */
  public static ArrayList<String> getDownloadLinks(Document htmlDocument) throws LinkNotFoundException {
    Elements elements = htmlDocument.select(CSS_QUERY_DOWNLOAD);
    int linksCount = elements.size();
    ArrayList<String> links = new ArrayList<>(linksCount);
    for (Element element: elements) {
      String link = element.attr(ATTRIBUTE_LINK_NAME);
      links.add(link);
    }
    return links;
  }
}
