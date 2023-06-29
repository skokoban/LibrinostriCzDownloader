package tools.Document;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTMLDocumentProvider implements IHTMLDocument {
/*=================================================================================================
                                        Methods
=================================================================================================*/
  @Override
  public Document get(String link) throws IOException {
    return Jsoup.connect(link).get();
  }
}
