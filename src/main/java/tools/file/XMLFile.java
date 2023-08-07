package tools.file;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import tools.Downloader;
import tools.config.ConfigProvider;

public class XMLFile extends File {
/*=================================================================================================
                                                Attributes
=================================================================================================*/
  private Map<String, String> books;
  private long checksum;
  private final String URL;
  private final String PATH;
  private final XPath xpath = XPathFactory.newInstance().newXPath();
  private static final String RSS_CHANNEL_ITEM = "/rss/channel/item";
  private static final String TITLE = "title";
  private static final String LINK = "link";
/*=================================================================================================
                                                Constructor
=================================================================================================*/
  public XMLFile(String url, String path) {
    URL = url;
    PATH = path;
  }
/*=================================================================================================
                                                Getters
=================================================================================================*/
  public Map<String, String> getBooks() {
    return books;
  }

  public long getChecksum() {
    return checksum;
  }
/*=================================================================================================
                                                Methods
=================================================================================================*/
  public boolean download() {
    long result = Downloader.download(URL, PATH);
    return result > 0;
  }

  public long countChecksum() {
    Path mPath = Path.of(PATH);
    checksum = checksum(mPath);
    return checksum;
  }

  public Map<String, String> parseXML() {
    InputSource xml = new InputSource(PATH);
    NodeList nodeList;
    XPathExpression expressionTitle;
    XPathExpression expressionLink;
    try {
      expressionTitle = xpath.compile(TITLE);
      expressionLink = xpath.compile(LINK);
      nodeList = (NodeList) xpath.evaluate(RSS_CHANNEL_ITEM, xml, XPathConstants.NODESET);
    } catch (XPathExpressionException e) {
      return Collections.emptyMap();
    }
    int itemsCount = nodeList.getLength();
    books = new HashMap<>(itemsCount);
    for (int i = 0; i < itemsCount; i++) {
      Node node = nodeList.item(i);
      String title;
      String link;
      try {
        title = (String) expressionTitle.evaluate(node, XPathConstants.STRING);
        link  = (String) expressionLink.evaluate(node, XPathConstants.STRING);
      } catch (XPathExpressionException e) {
        continue;
      }
      books.put(title, link);
    }
    return books;
  }

  public void saveChecksum(ConfigProvider configProvider) {
    String mChecksum = String.valueOf(checksum);
    configProvider.setChecksum(mChecksum);
  }

  public void deleteXML() {
    try {
      deleteFile(PATH);
    } catch (IOException ignore) {
    }
  }
}
