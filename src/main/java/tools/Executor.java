package tools;

import main.Book;
import org.xml.sax.InputSource;
import ui.Printer;

import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Executor {
/*======================================================================================================================
                                                    Attributes
======================================================================================================================*/
    private final String XML_URL    = "https://librinostri.catholica.cz/rss.php";
    private final String XML_NAME   = "rss";
    private final String XML_SUFFIX = ".php";
/*======================================================================================================================
                                                    Constructors
======================================================================================================================*/
/*======================================================================================================================
                                                    Methods
======================================================================================================================*/
    public void download() {
        // create URL
        URL xmlURL;
        try {
            xmlURL = new URL(XML_URL);
        } catch (MalformedURLException e) {
            parseDownloadableError(e);
            return;
        }
        // create tmp file for php document
        File xmlTempFile;
        try {
            xmlTempFile = File.createTempFile(XML_NAME, XML_SUFFIX);
        } catch (IOException e) {
            parseDownloadableError(e);
            return;
        }
        // download remote php file
        Downloader downloader = new Downloader();
        downloader.downloadTxtFile(xmlURL, xmlTempFile);
        //
        InputSource isXML     = new InputSource(String.valueOf(xmlTempFile));
        XMLParser xmlParser   = new XMLParser();
        ArrayList<Book> books;
        try {
            books = xmlParser.pasreBooks(isXML);
        } catch (XPathExpressionException e) {
            parseDownloadableError(e);
            return;
        }

        if (xmlTempFile.exists()) {
            xmlTempFile.delete();
        }

            DownloadLinksParser downloadLinksParser = new DownloadLinksParser();
        try {
            books = downloadLinksParser.parseDownloadLinks(books);
        } catch (IOException e) {
            parseDownloadableError(e);
            return;
        }

        //read downloadloc


        Map<String, File> fileMap;
        PDFFile pdfFile = new PDFFile();
        fileMap = pdfFile.createFiles(books);
        fileMap = pdfFile.deleteEntriesOfExistingFiles(fileMap);

        try {
            downloader.downloadFiles(fileMap);
        } catch (IOException e) {
            parseDownloadableError(e);
        }
    }

    protected void parseDownloadableError(Exception e) {
        e.printStackTrace();
        Printer.printText("New PDF files cannot be downloaded. Please send error report to mdorusak@gmail.com");
    }

    public void changeDownloadLocation() {
        Printer.printNewDownloadLocAsking();
        Scanner scanner = new Scanner(System.in);
        File booksPath  = new File(scanner.nextLine());
        booksPath.mkdirs();
        Config.setProperty("downloadFolder", booksPath.getAbsolutePath());
    }
}
