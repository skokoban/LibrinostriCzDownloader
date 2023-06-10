package tools.parser;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DocumentProvider implements IDocument {

  @Override
  public Elements get(Document document, String elementName) {
    return document.select(elementName);
  }
}
