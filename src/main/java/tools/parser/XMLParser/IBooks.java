package tools.parser.XMLParser;

import java.util.List;
import javax.xml.xpath.XPathExpressionException;
import main.Book;
import org.xml.sax.InputSource;

public interface IBooks {
  List<Book> parseBooks(InputSource xml) throws XPathExpressionException;
}
