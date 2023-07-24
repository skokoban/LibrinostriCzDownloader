package executor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
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

    IProperties iProperties = new PropertiesProvider();
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
    IProperties properties = new PropertiesProvider();
    for (String link : links) {
      String name = librinostri.retrieveName(link);
      String downloadFolder = properties.getProperty("download folder");
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

}
/*
 Execute all necessarry processes to download a file.


  public void download() {
    // create URL
    URL xmlURL;
    try {
      xmlURL = new URL(xmlNewBooks);
    } catch (MalformedURLException e) {
      parseDownloadableError(e);
      return;
    }
    // create tmp file for php document
    File xmlTempFile;
    try {
      xmlTempFile = File.createTempFile(XML_NAME, XML_SUFFIX);
      xmlTempFile.deleteOnExit();
    } catch (IOException e) {
      parseDownloadableError(e);
      return;
    }
    // download remote php file
    Downloader downloader = new Downloader();
    downloader.downloadTxtFile(xmlURL, xmlTempFile);

    InputSource isXML     = new InputSource(String.valueOf(xmlTempFile));
    XMLParser xmlParser   = new XMLParser();
    ArrayList<Book> books;
    try {
      books = xmlParser.pasreBooks(isXML);
    } catch (XPathExpressionException e) {
      parseDownloadableError(e);
      return;
    }
    // delete file with info about books
    if (xmlTempFile.exists()) {
      xmlTempFile.delete();
    }
    // get download links
    DownloadLinksParser downloadLinksParser = new DownloadLinksParser();
    try {
      downloadLinksParser.getDownloadLinks(books,
          iDocument.get(document, downloadLinksParser.ELEMENT_DOWNLOAD_NAME));
    } catch (IOException e) {
      parseDownloadableError(e);
      return;
    }


    // create apropriate files for all pdfs
    Map<String, File> fileMap;
    PDFFile pdfFile = new PDFFile();
    fileMap = pdfFile.createFiles(books);
    fileMap = pdfFile.deleteEntriesOfExistingFiles(fileMap);

    // no new files
    if (fileMap.size() == 0) {
      Printer.printNoNewFiles();
    }
    // download all new files
    else {
      try {
        for (Map.Entry<String, File> pdf: fileMap.entrySet()) {
          Printer.printDownloading(pdf.getValue());
          // make connection
          ConnectionHelper connection = new ConnectionHelper();
          HttpURLConnection httpURLConnection = connection.makeConnection(pdf.getKey());
          // download
          if (downloader.downloadFile(httpURLConnection, pdf.getValue())) {
            Printer.printOK();
          } else {
            Printer.printDownloadingError();
          }
        }
      } catch (IOException e) {   // catch all IO Ex. and parse it as downloading error.
        parseDownloadableError(e);
      }
    }
  }

*
   * Made to deal with downloading errors. Handle given exception and print it to StackTrace. Print text of error to command line to inform user.
   * @param e exception that should be printed to stackTrace.


  protected void parseDownloadableError(Exception e) {
    e.printStackTrace();
    Printer.printDownloadingError();
  }

*
   * Set property <code>downloadFolder</code> to given path from command line prompt. Creates all neccessary
   * directories if not exists.


  public void changeDownloadLocation() {
    Printer.printNewDownloadLocAsking();
    Scanner scanner = new Scanner(System.in);
    String downloadLocPath = scanner.nextLine();
    File booksPath  = new File(downloadLocPath);
    booksPath.mkdirs();
    new Config().setProperty(PROPERTY_DOWNLOAD_FOLDER, booksPath.getAbsolutePath());
  }

  public void showDownloadLocation() {
    Config config = new Config();
    String downloadFolder = config.getProperty(PROPERTY_DOWNLOAD_FOLDER);
    Printer.printText(downloadFolder);
  }
}
*/