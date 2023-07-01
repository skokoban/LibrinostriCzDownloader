package main;

import java.net.URL;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The <code>Book</code> class represents e-books located on websites. Book has basic attributes
 * like title, publication day. For e-book is important download link.
 */
public class Book {

/*=================================================================================================
                                                Attributes
=================================================================================================*/

  private final String            TITLE;
  private final String            LINK;
  private final ArrayList<String> DOWNLOAD_LINKS; // kniha moze byt rozdelena na viac casti
  private final int               CHARS_LIMIT_TITLE = 255;
  private final int               BIGIN_INDEX = 0;

/*=================================================================================================
                                                Constructor
=================================================================================================*/
  /**
   * Construct <code>Book</code> with given attributes. Avoid title of special characters for purposes of
   * creating directory from that title.
   *
   * @param title title of book
   */
  public Book(String title, String url, ArrayList<String> downloadLinks) {
    TITLE = normalizeBookName(title);
    LINK  = url;
    DOWNLOAD_LINKS = downloadLinks;
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

  public ArrayList<String> getDOWNLOAD_LINKS() {
    return DOWNLOAD_LINKS;
  }

/*=================================================================================================
                                                Methods
=================================================================================================*/

  /**
   * Converts string to system friendly form. Avoids string of bad chars.
   * <p>Example:</p>
   * <p>From <code>íťýáä</code> returns <code>ityaa</code>.</p>
   * @param rawTitle string to be converted
   * @return clear string without special chars
   */
  protected String normalizeBookName(String rawTitle) {
    String goodName = Normalizer.normalize(rawTitle, Normalizer.Form.NFD).replaceAll("\\W","");
    String finalName;
    if(goodName.length() > CHARS_LIMIT_TITLE) {
      finalName = goodName.substring(BIGIN_INDEX, CHARS_LIMIT_TITLE);
    } else {
      finalName = goodName;
    }
    return finalName;
  }

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