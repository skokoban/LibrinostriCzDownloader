package executor;

import java.nio.file.Path;
import main.Book;
import org.xml.sax.InputSource;
import tools.config.Config;
import tools.config.ConfigLocationProvider;
import tools.config.IProperties;
import tools.config.PropertiesProvider;
import tools.downloader.DownloaderProvider;
import tools.downloader.IDownloader;
import tools.parser.linksParser.DownloadLinksParser;
import ui.Printer;

import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Executor {

  public static final String RSS_URL = "rssURL";

  /*=================================================================================================
                                           Attributes
=================================================================================================*/

  /*=================================================================================================
                                              Methods
  =================================================================================================*/
  public static void downloadNewFiles() {
      // download xml file wih new books info from rss feed
    Path downloadFolder = ConfigLocationProvider.getDefaultDownloadLocation();

    IProperties iProperties = new PropertiesProvider();
    String rssURL = iProperties.getProperty(RSS_URL);

    IDownloader iDownloader = new DownloaderProvider();
    try {
      iDownloader.download(rssURL, downloadFolder);
    } catch (IOException e) {
      Printer.printDownloadingError();
      return;
    }

      // find download links for newest books


  }
}/*
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