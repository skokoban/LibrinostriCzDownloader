package main;

import exceptions.LinkNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import org.jsoup.nodes.Document;
import tools.document.HTMLDocumentProvider;
import tools.document.IHTMLDocument;
import tools.downloader.DownloaderProvider;
import tools.downloader.IDownloader;
import tools.parser.linksParser.DownloadLinksParser;

/**
 * The <code>Librinostri</code> class represents website librinostri.catholica.cz
 * where are stores e-books with cathlolic interest.
 */
public class Librinostri {

  public static final String CHECKSUM = "checksum";
  public static final String RSS_LINK = "rssURL";

/*=================================================================================================
                                                Methods
=================================================================================================*/
  /**
   * Inspect the book subsite for download links to all PDF´s related to the book.
   *
   * @param bookURL URL to information about the book on librinostri.catholica.cz.
   * @return list of Strings with URLs to direct download PDFs.
   * @throws IOException           if website with download links is not reachable
   * @throws LinkNotFoundException if none of link is found on website
   */
  public ArrayList<String> findDownloadLinks(String bookURL) throws IOException, LinkNotFoundException {
    IHTMLDocument ihtmlDocument = new HTMLDocumentProvider();
    Document htmlDocument = ihtmlDocument.get(bookURL);
    return DownloadLinksParser.getDownloadLinks(htmlDocument);
  }

  /**
   * Copy text content from remote file to local file.
   * @param link  link to remote text file
   * @param rss File where local file should be located
   * @return true if file is downloaded succesfully, false otherwise
   * @throws IOException if download fails.
   */
  public boolean downloadRSS(String link, Path rss) throws IOException {
    IDownloader iDownloader = new DownloaderProvider();
    iDownloader.download(link, rss);
    return true;
  }

  /**
   * Download file from given URL to given File.
   * @param link remote location from which file should be downloaded
   * @param path path to location where downlaoded file will be located
   * @return count of downloaded bytes
   * @throws IOException when download fails.
   */
  public long downloadFile(String link, Path path) throws IOException {
    IDownloader iDownloader = new DownloaderProvider();
    return iDownloader.download(link, path);
  }

  public String retrieveName(String url) {
    int lastSlash = url.lastIndexOf("/");
    return url.substring(lastSlash + 1);
  }
}
