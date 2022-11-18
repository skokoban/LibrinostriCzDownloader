package tools;

import org.junit.jupiter.api.Test;

import javax.xml.xpath.XPathExpressionException;

public class XMLParserTest {
    @Test
    public void test() throws XPathExpressionException {
        RSSDownloader rssDownloader = new RSSDownloader();
        XMLParser xmlParser = new XMLParser();
        //System.out.println(xmlParser.pasreBooks(rssDownloader.downloadRSS()));
    }
}
