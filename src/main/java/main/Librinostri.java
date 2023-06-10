package main;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.zip.CRC32;

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

  public long countChecksum(File rssFile) throws FileNotFoundException {
    Scanner scanner = new Scanner(rssFile);
    StringBuilder fileSB = new StringBuilder();
    while (scanner.hasNextLine()) {
      fileSB.append(scanner.nextLine());
    }
    String fileString = fileSB.toString();
    CRC32 rssFileCrc32 = new CRC32();
    rssFileCrc32.update(fileString.getBytes());
    return rssFileCrc32.getValue();
  }

  /**
   * Copy text content from remote file to local file. Copying is proceeded by single line
   * systematically.
   * @param url  link to remote text file
   * @param xmlFile File where local file should be located
   */
  public void downloadXml(URL url, File xmlFile) {
    try {
      InputStreamReader    inputStreamReader = new InputStreamReader(url.openStream());
      BufferedReader       reader            = new BufferedReader(inputStreamReader);
      FileOutputStream     fileOutputStream  = new FileOutputStream(xmlFile);
      BufferedOutputStream bos               = new BufferedOutputStream(fileOutputStream);

      String line;
      while ((line = reader.readLine()) != null) {
        byte[] buffer;
        buffer = line.getBytes(StandardCharsets.UTF_8);
        bos.write(buffer);
        bos.write(NEW_LINE_CHAR.getBytes());
      }
      bos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Download file from given URL connection to given File.
   * @param connection connection to URL
   * @param file        File object where downlaoded file will be located
   * @return true if PDF file is successfully downlaoded, otherwire returns false
   * @throws IOException when downloading fails.
   */
  public boolean downloadFile(HttpURLConnection connection, File file) throws IOException {
    connection.connect();
    if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
      return false;
    }                // stiahnutie suboru
    InputStream input   = connection.getInputStream();
    OutputStream output = new FileOutputStream(file);
    byte[]       data   = new byte[BYTE_ARRAY_SIZE];
    int          count;
    while ((count = input.read(data)) != END_STREAM_VALUE) {
      output.write(data, OFFSET_VALUE, count);
    }
    output.close();
    return true;
  }

}
