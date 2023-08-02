package executor;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.xml.xpath.XPathExpressionException;
import main.Book;
import main.Librinostri;
import main.Main;
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
    downloadXML();
    saveChecksum();
    List<Book> books = findLinks();
    books = findDownloadLinks(books);
    try {
      Files.deleteIfExists(rssFilePath);
    } catch (IOException ignore) {}
    downloadPDFS(books);
  }

  private static void downloadPDFS(List<Book> books) {
    for (Book book : books) {
      ArrayList<String> downloadLinks = book.getDownloadLinks();
      String title = book.getTITLE();
      downloadFiles(title, downloadLinks);
      }
  }

  private static List<Book> findLinks() {
    List<Book> books = new ArrayList<>();
    String rssFile = rssFilePath.toString();
    InputSource isXML = new InputSource(rssFile);
    IBooks iBooks = new BooksProvider();
    try {
      books = iBooks.parseBooks(isXML);
    } catch (XPathExpressionException e) {
      Printer.printXMLParsingError();
    }
    return books;
  }

  private static List<Book> findDownloadLinks(List<Book> books) {
    for (Book book : books) {
      String link = book.getLINK();
      ArrayList<String> downloadLinks;
      try {
         downloadLinks = librinostri.findDownloadLinks(link);
      } catch (IOException e) {
        Printer.printCannotFindDownloadLinksForBook();
        System.out.println(book.getTITLE());
        continue;
      }
      book.setDownloadLinks(downloadLinks);
    }
    return books;
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
      System.out.println(e);
      Printer.printDownloadingError();
    }
    for (String link : links) {
      String name = librinostri.retrieveName(link);
      String pathToFile = filesFolder + java.io.File.separator + name;
      Path path = Path.of(pathToFile);
      Printer.printDownloading(name);
      try {
        librinostri.downloadFile(link, path);
      } catch (FileAlreadyExistsException e) {
        Printer.printFileAlreadyDownloaded();
        continue;
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
    long oldHash;
    try {
      oldHash = Long.parseLong(mOldHash);
    } catch (NumberFormatException e) {
      Printer.printText("There are new files to download on website.");
      return;
    }
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
      Files.deleteIfExists(rssFilePath);
      librinostri.downloadRSS(rssURL, rssFilePath);
    } catch (IOException e) {
      System.out.println(e);
      Printer.printDownloadingError();
      Main.main(new String[]{});
    }
    long checksum = 0;
    try {
      checksum = File.checksum(rssFilePath);
    } catch (IOException e) {
      System.out.println(e);
      Printer.printDownloadingError();
    }
    return checksum;
  }

  public static void createDirectories(Path path) throws IOException {
    Files.createDirectories(path);
  }
}