package tools;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tools.config.ConfigProvider;
import tools.config.PropertiesFactory;
import tools.config.PropertiesProvider;
import tools.file.XMLFile;

class DownloaderTest {

  @Test
  void download() {
    XMLFile xmlFile = new XMLFile("test", "rest");
    xmlFile.saveChecksum(new ConfigProvider());
  }

  @Test
  void test() {
    XMLFile xml = new XMLFile("test",
        "/home/marceld/Dokumenty/programovanie/IdeaProjects/LibrinostriCzDownloader/src/test/resources/testRSS.php");
  }
}