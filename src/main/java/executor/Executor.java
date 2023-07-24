package executor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import javax.xml.xpath.XPathExpressionException;
import main.Book;
import main.Librinostri;
import org.xml.sax.InputSource;
import tools.config.IProperties;
import tools.config.PropertiesProvider;
import tools.file.File;
import tools.parser.XMLParser.BooksProvider;
import tools.parser.XMLParser.IBooks;
import ui.Printer;

public class Executor {

  public static final String RSS_URL = "rssURL";
  public static final String DOWNLOAD_FOLDER = "download folder";
  private static IProperties iProperties = new PropertiesProvider();

/*=================================================================================================
                                           Attributes
=================================================================================================*/

/*=================================================================================================
                                              Methods
=================================================================================================*/
  public static void downloadNewFiles() {
      // initialize class for download operations
    Librinostri librinostri = new Librinostri();

      // download xml file wih new books info from rss feed
    Path rssFilePath = File.getRSSFilePath();

    String rssURL = iProperties.getProperty(RSS_URL);

    try {
      librinostri.downloadRSS(rssURL, rssFilePath);
    } catch (IOException e) {
      Printer.printDownloadingError();
      return;
    }

      // find links to the newest books
    LinkedHashMap<String, String> rawBooks = new LinkedHashMap<>();
    String rssFile = rssFilePath.toString();
    InputSource isXML = new InputSource(rssFile);
    IBooks iBooks = new BooksProvider();

    try {
      rawBooks = iBooks.getBooks(isXML);
    } catch (XPathExpressionException e) {
      Printer.printXMLParsingError();
    }

      // delete xml file not necessarry anymore
    try {
      Files.deleteIfExists(rssFilePath);
    } catch (IOException ignore) {}

    // create Book objects with download links
    ArrayList<Book> books = new ArrayList<>();
    for (Entry<String, String> entry : rawBooks.entrySet()) {
      String link = entry.getValue();
      String title = entry.getKey();
      ArrayList<String> downloadLinks;
      try {
         downloadLinks = librinostri.findDownloadLinks(link);
      } catch (IOException e) {
        Printer.printCannotFindDownloadLinksForBook();
        System.out.println(entry.getKey());
        continue;
      }
      Book book = new Book(title, link, downloadLinks);
      books.add(book);
    }

      // download PDF files for each book
    for (Book book : books) {
      ArrayList<String> downloadLinks = book.getDOWNLOAD_LINKS();
      downloadFiles(downloadLinks);
      }
    }

  private static void downloadFiles(ArrayList<String> links) {
    Librinostri librinostri = new Librinostri();
    for (String link : links) {
      String name = librinostri.retrieveName(link);
      String downloadFolder = iProperties.getProperty(DOWNLOAD_FOLDER);
      String pathToFile = downloadFolder + java.io.File.separator + name;
      Path path = Path.of(pathToFile);
      Printer.printDownloading(name);
      try {
        librinostri.downloadFile(link, path);
      } catch (IOException e) {
        Printer.printDownloading(name);
      }
      Printer.printOK();
    }
  }

  public static void changeDownloadFolder() {
    String downloadFolder = handleString();
    iProperties.setProperty(DOWNLOAD_FOLDER, downloadFolder);
  }

  protected static String handleString() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  public static void showDownloadFolder() {
    String downloadFolder = iProperties.getProperty(DOWNLOAD_FOLDER);
    System.out.println(downloadFolder);
  }
}