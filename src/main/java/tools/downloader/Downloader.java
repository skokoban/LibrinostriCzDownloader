package tools.downloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tools.config.PropertiesProvider;
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
  /**
   * Copy remote file to local drive.
   * @param link to source file that should be copied
   * @param path to target file
   * @return count of bytes copied
   * @throws FileAlreadyExistsException when file already exists.
   */
  public static long download(String link, String path) throws FileAlreadyExistsException {
  Path mPath = Path.of(path);
  URL url;
  long length;
  try {
    url = new URL(link);
    InputStream inputStream = url.openStream();
    length = Files.copy(inputStream, mPath);
  } catch (FileAlreadyExistsException e) {
    throw new FileAlreadyExistsException("/tmp/rss.php have to be removed");
  }
  catch (IOException e) {
    length = 0;
  }
  return length;
}

  /**
   * From actual remote XML file parses information abou books.
   * @param url to access application properties.
   * @param path to xml file.
   */
  public static Map<String, String> getBooksInfo(String url, String path) {
    XMLFile xml = new XMLFile(url, path);
    if (!xml.download()) {
      return new HashMap<>();
    }
    Map<String, String> booksMap;
    booksMap = xml.parseXML();
    return booksMap;
  }

  /**
   * From given title, link, and path creates object Book.
   * @param booksMap with information about each book. Title and link to book.
   * @param downloadFolder where pdf files related to book will be stored.
   */
  public static List<Book> createBooks(Map<String, String> booksMap, String downloadFolder) {
    List<Book> books = new ArrayList<>();
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
    return books;
  }

  /**
   * Downloads html file and parses it.
   * @param book object with information about book.
   */
  public static Document getHtmlDocument(Book book) {
      String linkToBook = book.getLINK();
    Document html;
    try {
      html = Jsoup.connect(linkToBook).get();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return html;
  }

  /**
   *From given list of links to remote files copies these to book´s path.
   * @param downloadLinks list of links that should be copied
   * @param book object related to links.
   */
  public static void downloadFiles(List<String> downloadLinks, Book book) {
    for (String link : downloadLinks) {
      String name = retrieveName(link);
      Printer.printDownloading(name);
      String filePath = book.getPATH();
      try {
        Files.createDirectories(Path.of(filePath));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      filePath = filePath + File.separator + name;
      long length = 0;
      try {
        length = download(link, filePath);
      } catch (FileAlreadyExistsException e) {
        Printer.printFileAlreadyDownloaded();
      }
      if (length > 0) {
        Printer.printOK();
      }
    }
  }

  /**
   * In html document finds elements download and there attribute link.
   * @param html document for parsing
   * @return list of strings contains links.
   */
  public static List<String> findDownloadLinks(Document html) {
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

  /**
   * Given string entered to input stores to configuration file.
   */
  public static void changeDownloadFolder(PropertiesProvider properties, String givenPath) {
    Printer.printNewDownloadLocAsking();
    Path path = Path.of(givenPath);
    if (Files.notExists(path)) {
      Printer.printPathNotEists();
      return;
    }
    properties.setDownloadFolder(givenPath);
  }
}