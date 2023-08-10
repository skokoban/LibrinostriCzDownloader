package tools.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class XMLFileTest {

  private XMLFile xmlFile;

  @BeforeEach
  void setUp() {
    xmlFile = new XMLFile("testURL", "src/test/resources/testRSS.php");
  }

  @Test
  void passWhenXMLParsedSuccesfullyWithValidXML() {
    Map<String, String> testMap = xmlFile.parseXML();

    String result = testMap.get("Testovací titul 1");

    assertEquals("http://test1.link", result);

  }  @Test
  void passWhenXpathErrorWhenInValidXML() {
    XMLFile invalidXML = new XMLFile("test", "src/test/resources/testSite.html");
    Map<String, String> testMap = invalidXML.parseXML();

    boolean result = testMap.isEmpty();

    assertTrue(result);
  }
  @Test
  void passWhenMapSizeAccordingElementsInXML() {
    Map<String, String> testMap = xmlFile.parseXML();

    int result = 0;
    for (String key : testMap.keySet()) {
      if (key!=null) result++;
    }

    assertEquals(20, result);
  }
}