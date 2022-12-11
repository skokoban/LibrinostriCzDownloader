package tools;

import main.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PDFFileTest {
    ArrayList<Book> makeBooks() {
        ArrayList<String> dlLinks = new ArrayList<>();
        dlLinks.add("testFile1/testFile1");
        dlLinks.add("testFile2/testFile2");
        Book book = new Book("testTitle", "testLink", dlLinks);
        ArrayList<Book> books = new ArrayList<>();
        books.add(book);
        return books;
    }

    Path getDlDestination() {
        return Path.of("src" + File.separator + "test" + File.separator + "resources");
    }
    @Test
    @DisplayName("Add two elements works")
    void passWhenAdditionOfLinksAndFilesTOArrayWorks() {
        PDFFile fileCreator = new PDFFile();
        assertEquals(2, fileCreator.createFiles(makeBooks(), getDlDestination()).size());
    }

    @Test
    @DisplayName("Creating Filename from hypertext link")
    void passWhenCreatingFileNameWorks() {
        PDFFile fileCreator = new PDFFile();
        assertEquals("src/test/resources/testTitle/testFile1", fileCreator.createFiles(makeBooks(), getDlDestination()).get("testFile1/testFile1").toString());
    }
}
