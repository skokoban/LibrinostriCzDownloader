package tools.downloader;

import org.jsoup.Connection;

public interface IConnection {
  Connection getConnection(String link);
}
