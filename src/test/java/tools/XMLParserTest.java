package tools;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XMLParserTest {
    InputSource testXMLFile = new InputSource("src" + File.separator + "test" + File.separator + "resources" +
                            File.separator + "rss.php");
    @Test
    @DisplayName("Item Count 2")
    public void passWhenCountOfItemIsTwo() throws XPathExpressionException, IOException {
        XMLParser xmlParser = new XMLParser();
        assertEquals(2, xmlParser.pasreBooks(testXMLFile).size());
    }
}
