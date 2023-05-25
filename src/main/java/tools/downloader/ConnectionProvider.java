package tools.downloader;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class ConnectionProvider implements IConnection {
/*=================================================================================================
                                        Methods
=================================================================================================*/
  @Override
  public Connection getConnection(String link) {
    return Jsoup.connect(link);
  }
}
