package tools.downloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import main.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tools.config.ConfigProvider;
import tools.file.XMLFile;
import ui.Printer;

public class Downloader {
/*=================================================================================================
                                                Attributes
=================================================================================================*/
  private static final String CSS_QUERY_DOWNLOAD      = "a[href*=download]";
  private static final String ATTRIBUTE_LINK_NAME     = "abs:href";
/*=================================================================================================
                                                Constructor
=================================================================================================*/
  private Downloader() {
    throw new IllegalStateException("Utility class");
  }
/*=================================================================================================
                                                Methods
=================================================================================================*/
public static long download(String link, String path) {
  Path mPath = Path.of(path);
  URL url;
  long length;
  try {
    url = new URL(link);
    InputStream inputStream = url.openStream();
    length = Files.copy(inputStream, mPath);
  } catch (IOException e) {
    length = 0;
  }
  return length;
}

  public static void downloadNewFiles() {
      // stiahnut xml
    ConfigProvider config = new ConfigProvider();
    String url = config.getRSSURL();
    String path = config.getRSSLocation();
    XMLFile xml = new XMLFile(url, path);
    if (!xml.download()) {
      Printer.printDownloadingError();
      return;
    }
      // najst linky na knihy z xml
    Map<String, String> booksMap;
    booksMap = xml.parseXML();
      // vytvoriť objekty knih
    ArrayList<Book> books = new ArrayList<>();
    String downloadFolder = config.getDownloadFolder();
    int size = downloadFolder.length();
    String lastChar = downloadFolder.substring(size-1);
    if (!"/".equals(lastChar)) {
      downloadFolder = downloadFolder + File.separator;
    }
    for (Map.Entry<String, String> set: booksMap.entrySet()) {
      String name = set.getKey();
      String link = set.getValue();
      String folderPath = downloadFolder + name;
      Book book = new Book(name, link, folderPath);
      books.add(book);
    }
      // z linkov nájsť downloadlinky

    for (Book book : books) {
      String linkToBook = book.getLINK();
      Document html = getHTMLDocument(linkToBook);
      List<String> downloadLinks = findDownloadLinks(html);
        // vytvorit cesty z linkov
      for (String link : downloadLinks) {
        String name = retrieveName(link);
        Printer.printDownloading(name);
        String filePath = book.getPATH() + File.separator + name;
        if (download(link, filePath) > 0) {
          Printer.printOK();
        } else {
          Printer.printFileAlreadyDownloaded();
        }
      }

    }
  }

  public static Document getHTMLDocument(String link) {
    Document html;
    try {
      html = Jsoup.connect(link).get();
    } catch (IOException e) {
      return null;
    }
    return html;
  }

  protected static List<String> findDownloadLinks(Document html) {
    List<String> downloadLinks = new ArrayList<>();
    Elements elements = html.select(CSS_QUERY_DOWNLOAD);
    for (Element element: elements) {
      String link = element.attr(ATTRIBUTE_LINK_NAME);
      downloadLinks.add(link);
    }
    return downloadLinks;
  }

  protected static String retrieveName(String link) {
    int lastSlash = link.lastIndexOf("/");
    return link.substring(lastSlash + 1);
  }
}