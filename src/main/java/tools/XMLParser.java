package tools;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
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
    public Map<String, String> pasreBooks(File xml) throws XPathExpressionException {
        Map<String, String> books = new HashMap<>();
        int itemsCount = getItemsCount();
        for (int i = 1; i <= itemsCount; i++) {
            String expressionTitle = "/rss/channel/item[" + i + "]/title";
            String expressionLink  = "/rss/channel/item[" + i + "]/link";
            books.put((String) xpath.evaluate(expressionTitle, inputSource, XPathConstants.STRING),
                      (String) xpath.evaluate(expressionLink , inputSource, XPathConstants.STRING));
        }
        return books;
    }

    protected int getItemsCount() throws XPathExpressionException {
        NodeList nodeList = (NodeList) xpath.evaluate("/rss/channel/item", inputSource, XPathConstants.NODESET);
        return nodeList.getLength();
    }
}
