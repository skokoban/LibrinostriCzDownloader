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

/**
 * Class <code>XMLParser</code> represents method for processing XML files. Gives ability to get information
 * about books located on website Librinostri.
 */
public class XMLParser {
/*======================================================================================================================
                                                    Attributes
======================================================================================================================*/
    private XPath xpath = XPathFactory.newInstance().newXPath();
/*======================================================================================================================
                                                    Methods
======================================================================================================================*/

    /**
     * Inspect given XML file and provides URLs related to each book from given XML file.
     * @param xml XML file wth information about books
     * @return arrayList of objects book
     * @throws XPathExpressionException if reading XML tags fails.
     */
    public ArrayList<Book> pasreBooks(InputSource xml) throws XPathExpressionException {
        NodeList nList = (NodeList) xpath.evaluate("/rss/channel/item", xml, XPathConstants.NODESET);
        int itemsCount = nList.getLength();
        ArrayList<Book> booksInfo = new ArrayList<>();
        for (int i = 1; i <= itemsCount; i++) {
            String expressionTitle = "/rss/channel/item[" + i + "]/title";
            String expressionLink  = "/rss/channel/item[" + i + "]/link";
            String title           = (String) xpath.evaluate(expressionTitle, xml, XPathConstants.STRING);
            String link            = (String) xpath.evaluate(expressionLink,  xml, XPathConstants.STRING);
            booksInfo.add(new Book(title, link));
        }
        return booksInfo;
    }
}