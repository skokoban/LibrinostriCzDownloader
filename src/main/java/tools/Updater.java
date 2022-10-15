package tools;

import main.Book;
import main.Librinostri;
import ui.Printer;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class <code>Updater</code> represents function which is able to inspect which books from available
 * on website is already stored in local drive and which are not stored. Names of stored books are
 * writen in textfile. Books from website are given from actual version of website. These two lists are compared
 * and new books returned.
 */
public class Updater {

/*======================================================================================================================
                                                Attributes
======================================================================================================================*/

    private       Librinostri      librinostri               = new Librinostri();
    private final Path             DOWNLOADED_PDFS_FILE_PATH = Path.of("downloadedfiles.txt");
    private final LinkedList<Book> NEW_BOOKS = new LinkedList<>();
    private final int              LIBRINOSTRI_PREFIX_LENGTH = 41;  // from this char starts name of file in url

/*======================================================================================================================
                                                Getter
======================================================================================================================*/

    public LinkedList<Book> getNEW_BOOKS() {
        return NEW_BOOKS;
    }

/*======================================================================================================================
                                                Constructors
======================================================================================================================*/

    /**
     * Construct <code>Updater</code>.
     */
    public Updater() {
        createDownloadedListFile();
        Printer.printUpdateIntro();
        findNewBooks(librinostri.getBOOKS_INFO());
    }

/*======================================================================================================================
                                                Methodes
======================================================================================================================*/

    /**
     * Creates text file where names of all dowwnloaded pdf files will be stored.
     */
    protected void createDownloadedListFile() {
        try {
            Files.createFile(DOWNLOADED_PDFS_FILE_PATH);
        }
        catch (FileAlreadyExistsException ignored) {}
        catch (IOException e) {
            e.printStackTrace();
            Printer.printFileCannotBeCreated();
        }
    }

    /**
     * Find list of files that are not downloaded yet. It compares all files from Book with downloadedbooks.txt.
     */
    public void findNewBooks(List<Book> allBooks) {
        List<String> downloadedBooks = new ArrayList<>();                        // nacitam stiahnute pdf zo suboru
        try {
            downloadedBooks = Files.readAllLines(DOWNLOADED_PDFS_FILE_PATH);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        for (Book book : allBooks) {
            ArrayList<String> notDownloadedPDFsYet = new ArrayList<>();
            for (String downloadLink : book.getDOWNLOAD_LINKS()) {
                String PDFName = downloadLink.substring(LIBRINOSTRI_PREFIX_LENGTH);
                if (!downloadedBooks.contains(PDFName)) {
                    notDownloadedPDFsYet.add(PDFName);
                    if (!NEW_BOOKS.contains(book)) {
                        NEW_BOOKS.add(book);
                    }
                }
            }
            book.setNotDownloadedPDFsYet(notDownloadedPDFsYet);
        }
    }

    public void printNewBooks() {
        Printer.printNewBooks(NEW_BOOKS);
    }
}
