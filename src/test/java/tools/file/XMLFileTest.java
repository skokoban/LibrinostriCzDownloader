package tools.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

  @Test
  void passWhenReturnsSomeChecksum() throws IOException {
    byte[] fileBytes = {1, 2, 3, 4, 5};
    Path tempFile = Files.createTempFile("test", ".txt");
    Files.write(tempFile, fileBytes);

    long result = xmlFile.countChecksum();

    assertNotEquals(0, result);

    Files.deleteIfExists(tempFile);
  }
}