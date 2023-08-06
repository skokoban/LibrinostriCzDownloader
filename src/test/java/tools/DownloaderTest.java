package tools;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import tools.config.PropertiesFactory;
import tools.config.PropertiesProvider;
import tools.file.XMLFile;

class DownloaderTest {

  @Test
  void download() {
    PropertiesProvider propertiesProvider = PropertiesFactory.getPropertiesProvider();
    XMLFile xmlFile = new XMLFile("test", "rest");
    xmlFile.saveChecksum(propertiesProvider);
  }

  @Test
  void test() {
    XMLFile xml = new XMLFile("test", "/home/marceld/Dokumenty/programovanie/IdeaProjects/LibrinostriCzDownloader/src/test/resources/testRSS.php");
  }
}