package tools.file;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.CRC32;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import tools.downloader.Downloader;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import tools.config.PropertiesProvider;

/**
 * Represents XML file. This file is consists information about new books added on website
 * librinostri.catholica.cz. Provides methods for operation with this file.
 */
public class XMLFile {
/*=================================================================================================
                                                Attributes
=================================================================================================*/
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
                                                Methods
=================================================================================================*/

  /**
   * Copy remote content to local file.
   * @return true if at least one byte was transfered, false otherwise.
   */
  public boolean download() {
    long result = 0;
    try {
      result = Downloader.download(URL, PATH);
    } catch (FileAlreadyExistsException e) {
      if (deleteXML()) {
        download();
      } else {
        return false;
      }
    }
    return result > 0;
  }

  /**
   * Count checksum for given file. Returns 0 if file is empty.
   * @return value of CRC32 checksum. Returns 0 if checksum cannot be counted.
   */
  public long countChecksum() {
    Path mPath = Path.of(PATH);
    byte[] fileBytes;
    try {
      fileBytes = Files.readAllBytes(mPath);
    } catch (IOException e) {
      return 0;
    }
    CRC32 rssFileCrc32 = new CRC32();
    rssFileCrc32.update(fileBytes);
    checksum = rssFileCrc32.getValue();
    return checksum;
  }

  /**
   * From XML file look for books names and links to these books.
   * @return map consists books name and apropriated URLs.
   */
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
    Map<String, String> books = new HashMap<>(itemsCount);
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

  /**
   * Strore checksum to config file.
   * @param propertiesProvider provider to work with configuration file.
   */
  public void saveChecksum(PropertiesProvider propertiesProvider) {
    String mChecksum = String.valueOf(checksum);
    propertiesProvider.setChecksum(mChecksum);
  }

  /**
   * Remove the XML file.
   * @return true if operation was successfully, false otherwise.
   */
  public boolean deleteXML() {
    Path mPath = Path.of(PATH);
    try {
      Files.deleteIfExists(mPath);
    } catch (IOException e) {
      return false;
    }
    return true;
  }
}
