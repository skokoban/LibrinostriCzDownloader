package tools.parser;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public interface IDocument {
  Elements get(Document document, String elementName);
}
