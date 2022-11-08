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
    public ArrayList<String> saveToHDD() {
        ArrayList<String> downloadedPDFs = new ArrayList<>();
        HttpURLConnection connection;
        for (String fileName: notDownloadedPDFsYet) {       // prechadzam vsetky nestiahnute subory
            for (String link : DOWNLOAD_LINKS) {            // najde link k prisluchajucemu nazvu suboru.
                if (link.contains(fileName)) {
                    try {
                        createBookFolder();
                        File PDFFile = createPDFFilePath(fileName);
                        if (checkFileExistence(PDFFile)) {
                            continue;
                        }
                        connection = (HttpURLConnection) new URL(link).openConnection();
                        connection.connect();
                        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                            continue;
                        }
                        InputStream  input  = connection.getInputStream();                // stiahnutie suboru
                        OutputStream output = new FileOutputStream(PDFFile);
                        byte[]       data   = new byte[4096];
                        int          count;
                        while ((count = input.read(data)) != -1) {
                            output.write(data, 0, count);
                        }
                        output.close();
                        downloadedPDFs.add(fileName);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    DOWNLOAD_LINKS.remove(link);
                }
            }
        }
        return downloadedPDFs;
    }

    public void createBookFolder() {
        File file = new File(BOOKS_MAIN_FOLDER + File.separator + TITLE);
        file.mkdirs();
    }

    public File createPDFFilePath(String fileName) {
        return new File(BOOKS_MAIN_FOLDER + File.separator + TITLE + File.separator + fileName);
    }

    protected boolean checkFileExistence(File fileName) {
        return fileName.exists();
    }

    public String toString() {
        return TITLE;
    }
}