package tools;

import main.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

public class DownloaderTest {

/*======================================================================================================================
                                                Prepare methods
======================================================================================================================*/

    ArrayList<String> getTestNames() {
        ArrayList<String> names = new ArrayList<>();
        names.add("test1");
        names.add("test2");
        return names;
    }

    ArrayList<Book> getTestBook() {
        ArrayList<String> testDownloadLinks = new ArrayList<>();
        testDownloadLinks.add("testLink");
        Book testBook = new Book("Test Book", testDownloadLinks);
        ArrayList<Book> testBooks = new ArrayList<>();
        testBooks.add(testBook);
        return testBooks;
    }

/*======================================================================================================================
                                                Test methods
======================================================================================================================*/

    @Test
    @DisplayName("writing names to file works")
    void passWhenWritingNamesToFileWorks() {
        Downloader downloader = new Downloader();
        assertTrue(downloader.writeNameToFile(getTestNames()));
        deleteTestFile(downloader.getDOWNLOADED_PDFS_FILE_PATH());
    }

    @Test
    @DisplayName("downloading books")
    void passWhenDownloadingBooksWorks() {
        ArrayList<String> downlaodedPDFFilesTest = new ArrayList<>();
        downlaodedPDFFilesTest.add("testName");

        Book bookMock = Mockito.mock(Book.class);
        when(bookMock.saveToHDD()).thenReturn(downlaodedPDFFilesTest);

        Downloader downloader = new Downloader();
        downloader.downloadBooks(getTestBook());
        try {
            assertEquals("testName", Files.readString(Path.of(downloader.getDOWNLOADED_PDFS_FILE_PATH())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        deleteTestFile(downloader.getDOWNLOADED_PDFS_FILE_PATH());
    }

/*======================================================================================================================
                                                After methods
======================================================================================================================*/

    private void deleteTestFile(String downloaded_pdfs_file_path) {
        new File(downloaded_pdfs_file_path).delete();
    }
}
