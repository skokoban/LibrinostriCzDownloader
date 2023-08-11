package main;

import java.text.Normalizer;
import java.util.Objects;

/**
 * The <code>Book</code> class represents e-books located on websites. Book has basic attributes
 * like title, publication day. For e-book is important download link.
 */
public class Book {
/*=================================================================================================
                                                Attributes
=================================================================================================*/
  private final String TITLE;
  private final String LINK;
  private final String PATH;
  private static final int CHARS_LIMIT_TITLE = 255;
  private static final int BIGIN_INDEX = 0;
/*=================================================================================================
                                                Constructor
=================================================================================================*/
  /**
   * Construct <code>Book</code> with given attributes.
   * @param title title of book
   * @param link link to website about infomation of book
   */
  public Book(String title, String link, String path) {
    TITLE = normalizeString(title);
    LINK  = link;
    PATH = path;
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

  public String getPATH() {
    return PATH;
  }
/*=================================================================================================
                                                Methods
=================================================================================================*/
  /**
   * Converts string to system friendly form. Avoids string of bad chars.
   * Spaces replace with undercurrent.
   * <p>Example:</p>
   * <p>From <code>"íťý áä"</code> returns <code>"ity_aa"</code>.</p>
   * @param rawString string to be converted
   * @return clear string without special chars
   */
  public String normalizeString(String rawString) {
    String replaceSpaces = Normalizer.normalize(rawString, Normalizer.Form.NFD)
        .replace(" ", "_");
    String goodName = Normalizer.normalize(replaceSpaces, Normalizer.Form.NFD)
        .replaceAll("\\W","");
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