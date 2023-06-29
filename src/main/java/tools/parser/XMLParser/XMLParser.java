package tools.parser.XMLParser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
/**
 * Class <code>XMLParser</code> represents method for processing XML files. Gives ability to get information
 * about books located on website Librinostri.
 */
public class XMLParser implements BooksProvider{
/*=================================================================================================
                                               Attributes
=================================================================================================*/
  private final XPath xpath = XPathFactory.newInstance().newXPath();
  private static final String RSS_CHANNEL_ITEM = "/rss/channel/item";
  private static final String TITLE = "title";
  private static final String LINK = "link";
/*=================================================================================================
                                             Methods
=================================================================================================*/
/**
 * Inspect given XML file and provides URLs related to each title of book from given XML file.
 *
 * @param xml XML file wth information about books
 * @return LinkedHashMap with pairs title-url
 * @throws XPathExpressionException if reading XML tags fails.
 */
  public LinkedHashMap<String, URL> getBooks(InputSource xml)
      throws XPathExpressionException, MalformedURLException {
    XPathExpression expressionTitle = xpath.compile(TITLE);
    XPathExpression expressionLink = xpath.compile(LINK);
    NodeList itemNodes = (NodeList) xpath.evaluate(RSS_CHANNEL_ITEM, xml, XPathConstants.NODESET);
    int itemsCount = itemNodes.getLength();
    LinkedHashMap<String, URL> books = new LinkedHashMap<>(itemsCount);

    for (int i = 0; i < itemsCount; i++) {
      Node node = itemNodes.item(i);
      String title = (String) expressionTitle.evaluate(node, XPathConstants.STRING);
      String link = (String) expressionLink.evaluate(node, XPathConstants.STRING);
      URL url = new URL(link);
      books.put(title, url);
    }
    return books;
  }
}
