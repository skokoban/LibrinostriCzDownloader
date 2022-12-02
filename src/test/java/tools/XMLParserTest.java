package tools;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

    @Test
    @DisplayName("Title equivalence")
    public void passWhenTitleIsCorrect() throws XPathExpressionException, IOException {
        XMLParser xmlParser = new XMLParser();
        assertEquals("Testovaci_titul_1", xmlParser.pasreBooks(testXMLFile).get(0).getTITLE());
    }

    @Test
    @DisplayName("Link equivalence")
    public void passWhenLinkIsCorrect() throws XPathExpressionException, IOException {
        XMLParser xmlParser = new XMLParser();
        ArrayList<String> downloadLinks = xmlParser.pasreBooks(testXMLFile).get(1).getDOWNLOAD_LINKS();
        assertEquals("Testovaci_titul_1", downloadLinks.get(0));
    }
}
