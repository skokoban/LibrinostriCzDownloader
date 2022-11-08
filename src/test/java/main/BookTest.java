package main;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookTest {

/*======================================================================================================================
                                         Attributes
======================================================================================================================*/

    static ArrayList<String> downloadLinks = new ArrayList<>();
    static ArrayList<String> notDownloadedFiles = new ArrayList<>();

/*======================================================================================================================
                                         Preparation methods
======================================================================================================================*/

    @BeforeAll
    static void fillList() {
        downloadLinks.add("http://test.test.test/download/test.pdf");
    }

    static Book createTestBook() {
        Book testBook = new Book("Test", link, downloadLinks);
        testBook.setNotDownloadedPDFsYet(notDownloadedFiles);
        return testBook;
    }
    @BeforeAll
    static void insertFileToDownload() {notDownloadedFiles.add("test.pdf");}

/*======================================================================================================================
                                             Methods for testing replaceSpecChars
======================================================================================================================*/
    @Test
    @DisplayName("parse empty string as empty string")
    void PassWhenParseEmptyStringAsString() {
        Book book = new Book("", link, downloadLinks);
        assertEquals("", book.getTITLE());
    }

    @Test
    @DisplayName("parse space as _")
    void PassWhenParseSpaceAsSpace() {
        Book book = new Book(" ", link, downloadLinks);
        assertEquals("_", book.getTITLE());
    }
    @Test
    @DisplayName("parse spec chars to normal")
    void PassWhenParseSpecCharsToNormal() {
        Book book = new Book(";+ľščťžýáíé=´äú?ň§ô,.-1+-*/&@#!_)/(PÓ",
                link, downloadLinks);
        assertEquals("lsctzyaieauno1_PO", book.getTITLE());
    }
/*======================================================================================================================
                                             Methods for creating filepath
======================================================================================================================*/
    @Test
    @DisplayName("Book folder created correctly")
    void PassWhenBookFolderCreatedCorrectly() {
        Book book = createTestBook();
        book.createBookFolder();
        File file = new File("Book" + File.separator + "Test");
        new File("Book").deleteOnExit();
        file.deleteOnExit();
        assertTrue(file.exists());
    }

/*======================================================================================================================
                                         Methods for check save to hdd
======================================================================================================================*/

    @Test
    @DisplayName("save to hdd works")
    void PassWhenSaveToHddWorks() {
        Book book = createTestBook();
        new File("Book").deleteOnExit();
        new File("Book" + File.separator + "test.pdf").deleteOnExit();

        assertEquals(1, book.saveToHDD().size());
    }

}