package main;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Normalizer;
import java.util.ArrayList;

/**
 * The <code>Book</code> class represents e-books located on websites. Book has basic attributes like title,
 * publication day. For e-book is important download link.
 */
public class Book {

/*======================================================================================================================
                                                Attributes
======================================================================================================================*/

    private final String            TITLE;
    private final ArrayList<String> DOWNLOAD_LINKS;             // kniha moze byt rozdelena na viac casti

/*======================================================================================================================
                                                Constructor
======================================================================================================================*/

    /**
     * Construct <code>Book</code> with given attributes. Avoid title of special characters for purposes of
     * creating directory from that title.
     *
     * @param title         title of book
     * @param downloadLinks links from where single pdf files can be downloaded
     */
    public Book(String title, ArrayList<String> downloadLinks) {
        TITLE          = replaceSpecChars(title);
        DOWNLOAD_LINKS = downloadLinks;
    }

/*======================================================================================================================
                                                Getters
======================================================================================================================*/

    public String getTITLE() {
        return TITLE;
    }

    public ArrayList<String> getDOWNLOAD_LINKS() {
        return DOWNLOAD_LINKS;
    }

/*======================================================================================================================
                                                Methods
======================================================================================================================*/

    /**
     * Converts string to system friendly form. Avoids string of "bad" chars.
     * <p>Example:</p>
     * <p>From <code>íťýáä</code> returns <code>ityaa</code>.</p>
     * @param badTitle string to be converted
     * @return clear string without special chars
     */
    public String replaceSpecChars(String badTitle) {
        String replacedSpaces = Normalizer.normalize(badTitle, Normalizer.Form.NFD).replaceAll(" ", "_");
        return Normalizer.normalize(replacedSpaces, Normalizer.Form.NFD).replaceAll("\\W", "");
    }

    /**
     * Save book to hard drive. Saves only files which are not already downloaded.
     * @return ArrayList of downloaded PDF files.
     */
    public String toString() {
        return TITLE;
    }
}