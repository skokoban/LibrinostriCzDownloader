package main;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
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
  private final String           URL_BOOKS_INFO       = "http://librinostri.catholica.cz/rss.php";
/*=================================================================================================
                                                Methods
=================================================================================================*/
  /**
   * Inspect the book subsite for download links to all PDF´s related to the book.
   * @param bookLink URL to information about the book on librinostri.catholica.cz.
   * @return list of Strings with URLs to direct download PDFs.
   */
  public ArrayList<URL> findDownloadLinks(String bookLink) {
    ArrayList<URL> downloadLinks = new ArrayList<>();

    return downloadLinks;
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
