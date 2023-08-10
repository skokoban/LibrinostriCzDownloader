package tools.downloader;

import java.util.List;
import java.util.Map;
import main.Book;
import org.jsoup.nodes.Document;
import tools.config.PropertiesProvider;

public class DownloadBooksFacade {
/*=================================================================================================
                                                Constructor
=================================================================================================*/
  private DownloadBooksFacade() {
    throw new IllegalStateException("Utility class");
  }
/*=================================================================================================
                                                Methods
=================================================================================================*/
  public static void download(PropertiesProvider properties) {
    String url = properties.getRSSURL();
    String path = properties.getRSSLocation();
    String downloadFolder = properties.getDownloadFolder();
    Map<String, String> booksMap = Downloader.getBooksInfo(url, path);
    List<Book> books = Downloader.createBooks(booksMap, downloadFolder);
    for (Book book : books) {
      Document html = Downloader.getHtmlDocument(book);
      List<String> downloadLinks = Downloader.findDownloadLinks(html);
      Downloader.downloadFiles(downloadLinks, book);
    }
  }
}
