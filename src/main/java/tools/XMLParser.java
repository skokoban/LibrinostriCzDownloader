package tools;

import main.Book;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XMLParser {
/*======================================================================================================================
                                                    Attributes
======================================================================================================================*/
    XPath xpath = XPathFactory.newInstance().newXPath();
/*======================================================================================================================
                                                    Methods
======================================================================================================================*/
    public ArrayList<Book> pasreBooks(InputSource xml) throws XPathExpressionException, IOException {
        NodeList nList = (NodeList) xpath.evaluate("/rss/channel/item", xml, XPathConstants.NODESET);
        int itemsCount = nList.getLength();
        ArrayList<Book> booksInfo = new ArrayList<>();
        DownloadLinksParser dlParser = new DownloadLinksParser();
        for (int i = 1; i <= itemsCount; i++) {
            String expressionTitle = "/rss/channel/item[" + i + "]/title";
            String expressionLink  = "/rss/channel/item[" + i + "]/link";
            String title = (String) xpath.evaluate(expressionTitle, xml, XPathConstants.STRING);
            String link  = (String) xpath.evaluate(expressionLink,  xml, XPathConstants.STRING);
            ArrayList<String> downloadLinks = dlParser.parseDownloadLinks(link);
            booksInfo.add(new Book(title, downloadLinks));
        }
        return booksInfo;
    }
    public String replaceSpecChars(String badTitle) {
        String replacedSpaces = Normalizer.normalize(badTitle, Normalizer.Form.NFD).replaceAll(" ", "_");
        return Normalizer.normalize(replacedSpaces, Normalizer.Form.NFD).replaceAll("\\W", "");
    }

}