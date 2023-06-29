package tools.parser.XMLParser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.InputSource;

public interface BooksProvider {
  LinkedHashMap<String, URL> getBooks(InputSource xml)
      throws XPathExpressionException, MalformedURLException;
}
