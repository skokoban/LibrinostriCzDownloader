package main;

import java.util.ArrayList;
import java.util.Objects;
import tools.string.StringManipulator;

/**
 * The <code>Book</code> class represents e-books located on websites. Book has basic attributes
 * like title, publication day. For e-book is important download link.
 */
public class Book {
/*=================================================================================================
                                                Attributes
=================================================================================================*/
  private final String            TITLE;
  private final String            LINK;   // odkaz na info o knihe
  private ArrayList<String>       downloadLinks;  // odkazy na jednotlive PDF
/*=================================================================================================
                                                Constructor
=================================================================================================*/
  /**
   * Construct <code>Book</code> with given attributes.
   * @param title title of book
   * @param link link to website about infomation of book
   * @param downloadLinks list of links from where each PDF is available to download.
   */
  public Book(String title, String link, ArrayList<String> downloadLinks) {
    TITLE = StringManipulator.normalizeBookName(title);
    LINK  = link;
    this.downloadLinks = downloadLinks;
  }
/*=================================================================================================
                                                  Setters
=================================================================================================*/
  public void setDownloadLinks(ArrayList<String> downloadLinks) {
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

  public ArrayList<String> getDownloadLinks() {
    return downloadLinks;
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