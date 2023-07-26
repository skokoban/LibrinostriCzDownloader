package tools.string;

import java.text.Normalizer;

/**
 * This class provide methods for string modification.
 */
public class StringManipulator {
/*=================================================================================================
                                                Attributes
=================================================================================================*/
  private static final int CHARS_LIMIT_TITLE = 255;
  private static final int BIGIN_INDEX = 0;
/*=================================================================================================
                                                Constructor
=================================================================================================*/
  private StringManipulator() {
    throw new IllegalStateException("Utility class");
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
  public static String normalizeBookName(String rawString) {
    String replaceSpaces = Normalizer.normalize(rawString, Normalizer.Form.NFD).replace(" ", "_");
    String goodName = Normalizer.normalize(replaceSpaces, Normalizer.Form.NFD).replaceAll("\\W","");
    String finalName;
    if(goodName.length() > CHARS_LIMIT_TITLE) {
      finalName = goodName.substring(BIGIN_INDEX, CHARS_LIMIT_TITLE);
    } else {
      finalName = goodName;
    }
    return finalName;
  }
}
