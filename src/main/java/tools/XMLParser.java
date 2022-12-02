package tools;

import main.Book;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.util.ArrayList;

public class XMLParser {
/*======================================================================================================================
                                                    Attributes
======================================================================================================================*/
    XPath xpath = XPathFactory.newInstance().newXPath();
/*======================================================================================================================
                                                    Methods
======================================================================================================================*/
    public ArrayList<Book> pasreBooks(InputSource xml) throws XPathExpressionException {
        NodeList nList = (NodeList) xpath.evaluate("/rss/channel/item", xml, XPathConstants.NODESET);
        int itemsCount = nList.getLength();
        ArrayList<Book> booksInfo = new ArrayList<>();
        for (int i = 1; i <= itemsCount; i++) {
            String expressionTitle = "/rss/channel/item[" + i + "]/title";
            String expressionLink  = "/rss/channel/item[" + i + "]/link";
            String title        = (String) xpath.evaluate(expressionTitle, xml, XPathConstants.STRING);
            String link         = (String) xpath.evaluate(expressionLink,  xml, XPathConstants.STRING);
            booksInfo.add(new Book(title, link));
        }
        return booksInfo;
    }
}