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
import tools.config.PropertiesFactory;
import tools.file.File;
import tools.parser.XMLParser.BooksProvider;
import tools.parser.XMLParser.IBooks;
import ui.Printer;

public class Executor {

  public static final String RSS_URL = "rssURL";
  public static final String DOWNLOAD_FOLDER = "downloadFolder";
  private static IProperties iProperties;

  private static Librinostri librinostri = new Librinostri();
  private static Path rssFilePath = File.getRSSFilePath();

/*=================================================================================================
                                              Methods
=================================================================================================*/
  public static void downloadNewFiles() {
      // download xml file wih new books info from rss feed
    downloadXML();
    saveChecksum();
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
      ArrayList<String> downloadLinks = book.getDownloadLinks();
      String title = book.getTITLE();
      downloadFiles(title, downloadLinks);
      }
    }

  private static void saveChecksum() {
    long mchecksum = 0;
    try {
      mchecksum = File.checksum(rssFilePath);
    } catch (IOException e) {
      System.out.println(e);
      Printer.printText("Checksum cannot be couonted. "
          + "Please send this error report to mdorusak@gmail.com");
    }
    String checksum = String.valueOf(mchecksum);
    iProperties = PropertiesFactory.getPropertiesProvider();
    iProperties.setProperty("checksum", checksum);
  }

  private static void downloadFiles(String title, ArrayList<String> links) {
    Librinostri librinostri = new Librinostri();
    iProperties = PropertiesFactory.getPropertiesProvider();
    String downloadFolder = iProperties.getProperty(DOWNLOAD_FOLDER);
    String filesFolder = downloadFolder + java.io.File.separator + title;
    Path downloadDirectory = Path.of(filesFolder);
    try {
      createDirectories(downloadDirectory);
    } catch (IOException e) {
      e.printStackTrace();
      Printer.printDownloadingError();
    }
    for (String link : links) {
      String name = librinostri.retrieveName(link);
      String pathToFile = filesFolder + java.io.File.separator + name;
      Path path = Path.of(pathToFile);
      Printer.printDownloading(name);
      try {
        librinostri.downloadFile(link, path);
      } catch (IOException e) {
        Printer.printDownloadingError();
        continue;
      }
      Printer.printOK();
    }
  }

  public static void changeDownloadFolder() {
    String downloadFolder = handleString();
    iProperties = PropertiesFactory.getPropertiesProvider();
    iProperties.setProperty(DOWNLOAD_FOLDER, downloadFolder);
  }

  protected static String handleString() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  public static void showDownloadFolder() {
    iProperties = PropertiesFactory.getPropertiesProvider();
    String downloadFolder = iProperties.getProperty(DOWNLOAD_FOLDER);
    System.out.println(downloadFolder);
  }

  public static void checkForUpdate() {
    iProperties = PropertiesFactory.getPropertiesProvider();
    String mOldHash = iProperties.getProperty("checksum");
    long oldHash = Long.parseLong(mOldHash);
    long newHash = downloadXML();
    if (oldHash == newHash) {
      Printer.printText("Nothing new on website.");
    }
    else Printer.printText("There are new files to download on website.");
  }

  protected static long downloadXML() {
    iProperties = PropertiesFactory.getPropertiesProvider();
    String rssURL = iProperties.getProperty(RSS_URL);
    try {
      librinostri.downloadRSS(rssURL, rssFilePath);
    } catch (IOException e) {
      System.out.println(e);
      Printer.printDownloadingError();
    }
    long checksum = 0;
    try {
      checksum = File.checksum(rssFilePath);
    } catch (IOException e) {
      Printer.printDownloadingError();
    }
    return checksum;
  }

  public static void createDirectories(Path path) throws IOException {
    Files.createDirectories(path);
  }
}