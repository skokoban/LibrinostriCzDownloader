package tools.parser.XMLParser;

import javax.xml.xpath.XPathExpressionException;
import main.Book;
import org.xml.sax.InputSource;

public interface BooksProvider {
  Book[] getBooks(InputSource xml) throws XPathExpressionException;
}
