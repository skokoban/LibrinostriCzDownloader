package tools;

import main.Book;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XMLParser {
/*======================================================================================================================
                                                    Attributes
======================================================================================================================*/
    XPath xpath = XPathFactory.newInstance().newXPath();
    InputSource inputSource = new InputSource("rss.xml");

/*======================================================================================================================
                                                    Methods
======================================================================================================================*/
    public ArrayList<Book> pasreBooks(File xml) throws XPathExpressionException {
        NodeList nList = (NodeList) xpath.evaluate("/rss/channel/item", inputSource, XPathConstants.NODESET);
        int itemsCount = nList.getLength();
        ArrayList<Book> booksInfo = new ArrayList<>();
        DownloadLinksParser dlParser = new DownloadLinksParser();
        for (int i = 1; i <= itemsCount; i++) {
            String expressionTitle = "/rss/channel/item[" + i + "]/title";
            String expressionLink  = "/rss/channel/item[" + i + "]/link";
            String title = (String) xpath.evaluate(expressionTitle, inputSource, XPathConstants.STRING);
            String link  = (String) xpath.evaluate(expressionLink,  inputSource, XPathConstants.STRING);
            ArrayList<String> downloadLinks = dlParser.parseDownloadLinks(link);
            Book book = new Book(title, downloadLinks);
            booksInfo.add(book);
        }
        return booksInfo;
    }
}