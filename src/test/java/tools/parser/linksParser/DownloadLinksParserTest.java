package tools.parser.linksParser;

import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;

class DownloadLinksParserTest {

  Document htmlDocument;
  @BeforeEach
  void setUp() throws IOException {
    htmlDocument = Jsoup.parse(new File("src/test/resources/testSite.html"));

  }
}