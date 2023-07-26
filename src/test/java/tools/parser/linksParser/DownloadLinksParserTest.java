package tools.parser.linksParser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DownloadLinksParserTest {

  Document htmlDocument;
  @BeforeEach
  void setUp() throws IOException {
    htmlDocument = Jsoup.parse(new File("src/test/resources/testSite.html"));

  }

  @Test
  void passWhenLinkNotFoundExceptionIsThrown() {
    String html = "<html><body><li><a class=\"download\" href=\"download/nasinec-1928-r.pdf\" target=\"_blank\">nasinec-1928-r.pdf</a>, 338.4 MB &nbsp;&nbsp;<a href=\"/nahledy.php?n=1&amp;a=nasinec-1928-r\" target=\"_blank\" title=\"nahled s navigací\"><i class=\"far fa-file\"></i></a></li>\n</body></html>";
    htmlDocument = Jsoup.parse(html);

    Assertions.assertThrows(LinkNotFoundException.class, () -> DownloadLinksParser.getDownloadLinks(htmlDocument));
  }
}