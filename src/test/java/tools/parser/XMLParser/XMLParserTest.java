package tools.parser.XMLParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.junit.jupiter.api.Test;
import org.xml.sax.InputSource;

class XMLParserTest {
  private final XPath xpath = XPathFactory.newInstance().newXPath();

  @Test
  void passWhenBooksAreSuccesfullyAdded() throws XPathExpressionException, MalformedURLException {
    XMLParser xmlParser = new XMLParser();
    LinkedHashMap<String, URL> books = xmlParser.getBooks(
        new InputSource("src/test/resources/testRSS.php"));

    boolean hasNull = false;
    for (String title : books.keySet()) {
      if (title == null) {
        hasNull = true;
        break;
      }
    }

    assertFalse(hasNull);
  }

  @Test
  void passWhenTitleIsSuccesfullyAdded() throws XPathExpressionException, MalformedURLException {
    XMLParser xmlParser = new XMLParser();
    LinkedHashMap<String, URL> books = xmlParser.getBooks(
        new InputSource("src/test/resources/testRSS.php"));

    boolean hasTitle = books.containsKey("Test title 2");

    assertTrue(hasTitle);
  }

  @Test
  void passWhenURLIsSuccesfullyAdded() throws XPathExpressionException, MalformedURLException {
    XMLParser xmlParser = new XMLParser();
    LinkedHashMap<String, URL> books = xmlParser.getBooks(
        new InputSource("src/test/resources/testRSS.php"));
    URL url = books.get("Test title 2");

    String link = url.toString();

    assertEquals("http://test2.link", link);
  }
}