package tools.connection;

import java.io.IOException;
import org.jsoup.nodes.Document;

public interface IConnection {
  Document get(String link) throws IOException;

}