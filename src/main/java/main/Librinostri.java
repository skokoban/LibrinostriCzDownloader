package main;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.zip.CRC32;
import tools.downloader.DownloaderProvider;
import tools.downloader.IDownloader;

/**
 * The <code>Librinostri</code> class represents website librinostri.catholica.cz
 * where are stores e-books with cathlolic interest.
 */
public class Librinostri {

/*=================================================================================================
                                                    Attributes
=================================================================================================*/
  private final LinkedList<Book> BOOKS_INFO           = new LinkedList<>();
  private final String           URL_BOOKS_INFO       = "http://librinostri.catholica.cz/rss.php";
  private final String           DOWNLOAD_CSS_QUERY   = ".download";
  private final String           ATTRIBUTE_KEY_URL    = "abs:href";
  private final String           ELEMENT_ITEM_TO_FIND = "item";
  private final String           NEW_LINE_CHAR        = "\n";
  private final int              BYTE_ARRAY_SIZE      = 4096;
  private final int              END_STREAM_VALUE     = -1;
  private final int              OFFSET_VALUE         = 0;
/*=================================================================================================
                                                Getters
=================================================================================================*/
  public LinkedList<Book> getBOOKS_INFO() {
    return BOOKS_INFO;
  }
/*=================================================================================================
                                                Methods
=================================================================================================*/
  /**
   * Inspect the book subsite for download links to all PDF´s related to the book.
   * @param bookLink URL to information about the book on librinostri.catholica.cz.
   * @return list of Strings with URLs to direct download PDFs.
   * @throws IOException if link to the book is not reachable.
   */
  public ArrayList<String> findDownloadLinks(String bookLink) throws IOException {
    ArrayList<String> downloadLinks    = new ArrayList<>();
    Document          document         = Jsoup.connect(bookLink).get();
    Elements          elementsDownload = document.select(DOWNLOAD_CSS_QUERY);
    for (org.jsoup.nodes.Element link : elementsDownload) {
      downloadLinks.add(link.attr(ATTRIBUTE_KEY_URL));      // do zoznamu pridava absolutne URL
    }
    return downloadLinks;
  }

  /**
   * Count checksum for given file. Returns 0 if file is empty.
   * @param path the path fle for which checksum be counted
   * @return value of CRC32 checksum
   * @throws IOException if checksum cannot be counted.
   */
  public static long countChecksum(Path path) throws IOException {
    byte[] fileBytes = Files.readAllBytes(path);
    CRC32 rssFileCrc32 = new CRC32();
    rssFileCrc32.update(fileBytes);
    return rssFileCrc32.getValue();
  }

  /**
   * Copy text content from remote file to local file.
   * @param url  link to remote text file
   * @param rss File where local file should be located
   * @return true if file is downloaded succesfully, false otherwise
   * @throws IOException if download fails.
   */
  public boolean downloadRSS(URL url, Path rss) throws IOException {
    IDownloader iDownloader = new DownloaderProvider();
    iDownloader.download(url, rss);
    return true;
  }

  /**
   * Download file from given URL to given File.
   * @param url remote location from which file should be downloaded
   * @param path path to location where downlaoded file will be located
   * @return count of downloaded bytes
   * @throws IOException when download fails.
   */
  public long downloadFile(URL url, Path path) throws IOException {
    IDownloader iDownloader = new DownloaderProvider();
    return iDownloader.download(url, path);
  }

}
