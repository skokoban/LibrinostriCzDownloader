package tools.parser.XMLParser;

import java.util.LinkedHashMap;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.InputSource;

public interface IBooks {
  LinkedHashMap<String, String> getBooks(InputSource xml) throws XPathExpressionException;
}
