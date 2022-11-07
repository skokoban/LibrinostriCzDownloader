package tools;

import ui.Printer;
import main.Book;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Downloader {

/*======================================================================================================================
                                                Attributes
======================================================================================================================*/

    private String DOWNLOADED_PDFS_FILE_PATH;

/*======================================================================================================================
                                                Getter
======================================================================================================================*/

    public String getDOWNLOADED_PDFS_FILE_PATH() {
        return DOWNLOADED_PDFS_FILE_PATH;
    }

/*======================================================================================================================
                                                Constructors
======================================================================================================================*/

    /**
     * Default constructor.
     */
    public Downloader() {
        this("downloadedFiles.txt");
    }

    /**
     * Construct a <code>Downloader</code> and store location of downloadedbooks.txt file. File is not created when
     * constructor is invoked but when first name should be stored.
     * @param downloadedPdfsFilePath String Path where file with list of downloaded PDF files will be located.
     */
    public Downloader(String downloadedPdfsFilePath) {
        this.DOWNLOADED_PDFS_FILE_PATH = downloadedPdfsFilePath;
    }

/*======================================================================================================================
                                                Methods
======================================================================================================================*/

    /**
     *  Save all books stored in List to HardDrive. Takes each pdf file from Book object and one by one download them.
     *  After downloading of all pdf files from each Book writes names of downloaded pdf´s to textfile.
     *  If downloaded file names cannot be writen then stops downloading and exits the metod.
     * @param updatedBooks List of object <code>Book</code> consists of file that should be downloaded.
     */
    public void downloadBooks(List<Book> updatedBooks) {
        for (Book book : updatedBooks) {
            ArrayList<String> nameOfDownloadedPDFs = book.saveToHDD();
            if (nameOfDownloadedPDFs.isEmpty()) {
                continue;                                // ziadna kniha nebola stiahnuta. pokracovat dalsiu iteraciu
            }
            if (!writeNameToFile(nameOfDownloadedPDFs)) {
                return;
            }
        }
    }

    /**
     * Writes names of downloaded PDF files to textfile. Textfile is located in root directory of application.
     * Each name writes to new line.
     * @param namesOfDownloadedPDFs ArrayList with name of downloaded files.
     * @return <code>True</code> if names was writen succesfully. In case name was not writen returns <code>False</code>.
     */
    public boolean writeNameToFile(ArrayList<String> namesOfDownloadedPDFs) {
        try (FileWriter writer = new FileWriter(DOWNLOADED_PDFS_FILE_PATH)) {
            for (String nameOfDownloadedPDF : namesOfDownloadedPDFs) {
                writer.write(nameOfDownloadedPDF + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            Printer.printCannotWriteToFile();
            return false;
        }
        return true;
    }
}
