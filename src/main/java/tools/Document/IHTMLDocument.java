package tools.Document;

import java.io.IOException;
import org.jsoup.nodes.Document;

public interface IHTMLDocument {
  Document get(String link) throws IOException;

}
