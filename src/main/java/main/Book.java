package main;

import java.util.List;
import java.util.Objects;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tools.string.StringManipulator;

/**
 * The <code>Book</code> class represents e-books located on websites. Book has basic attributes
 * like title, publication day. For e-book is important download link.
 */
public class Book {
/*=================================================================================================
                                                Attributes
=================================================================================================*/
  private final String TITLE;
  private final String LINK;   // odkaz na info o knihe
  private List<String> downloadLinks;  // odkazy na jednotlive PDF
  private final String PATH;
/*=================================================================================================
                                                Constructor
=================================================================================================*/
  /**
   * Construct <code>Book</code> with given attributes.
   * @param title title of book
   * @param link link to website about infomation of book
   */
  public Book(String title, String link, String path) {
    TITLE = StringManipulator.normalizeBookName(title);
    LINK  = link;
    PATH = path;
  }
/*=================================================================================================
                                                  Setters
=================================================================================================*/
  public void setDownloadLinks(List<String> downloadLinks) {
    this.downloadLinks = downloadLinks;
  }
/*=================================================================================================
                                                  Getters
=================================================================================================*/
  public String getTITLE() {
    return TITLE;
  }

  public String getLINK() {
    return LINK;
  }

  public List<String> parseDownloadLinks() {
    return downloadLinks;
  }
  public String getPATH() {
    return PATH;
  }
/*=================================================================================================
                                                Methods
=================================================================================================*/
  @Override
  public String toString() {
    return TITLE;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Book otherBook = (Book) obj;
    return Objects.equals(this.TITLE, otherBook.TITLE);
  }

  @Override
  public int hashCode() {
    return Objects.hash(TITLE);
  }
}