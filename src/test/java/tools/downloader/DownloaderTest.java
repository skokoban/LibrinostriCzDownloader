package tools.downloader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tools.config.PropertiesProvider;

class DownloaderTest {

  @Test
  void passWhenBookTitleCreatedCorrectly() {
    Map<String, String> booksMap = new HashMap<>();
    booksMap.put("testTitle", "http://testURL.com/test");
    String downloadFodler = "testFolder";
    List<Book> bookList = Downloader.createBooks(booksMap, downloadFodler);
    Book book = bookList.get(0);

    String result = book.getTITLE();

    assertEquals("testTitle", result);
  }

  @Test
  void passWhenBookLinkCreatedCorrectly() {
    Map<String, String> booksMap = new HashMap<>();
    booksMap.put("testTitle", "http://testURL.com/test");
    String downloadFodler = "testFolder";
    List<Book> bookList = Downloader.createBooks(booksMap, downloadFodler);
    Book book = bookList.get(0);

    String result = book.getLINK();

    assertEquals("http://testURL.com/test", result);
  }

  @Test
  void passWhenBookPathCreatedCorrectly() {
    Map<String, String> booksMap = new HashMap<>();
    booksMap.put("testTitle", "http://testURL.com/test");
    String downloadFodler = "testFolder";
    List<Book> bookList = Downloader.createBooks(booksMap, downloadFodler);
    Book book = bookList.get(0);

    String result = book.getPATH();

    assertEquals("testFolder/testTitle", result);
  }

  @Test
  void passWhenBookPathWithSlashCreatedCorrectly() {
    Map<String, String> booksMap = new HashMap<>();
    booksMap.put("testTitle", "http://testURL.com/test");
    String downloadFodler = "testFolder/";
    List<Book> bookList = Downloader.createBooks(booksMap, downloadFodler);
    Book book = bookList.get(0);

    String result = book.getPATH();

    assertEquals("testFolder/testTitle", result);
  }

  @Test
  void passWhenCountOfLinksIsOk() throws IOException {
    File file = new File("src/test/resources/testSite.html");
    Document document = Jsoup.parse(file);
    List<String> links = Downloader.findDownloadLinks(document);

    int result = links.size();

    assertEquals(12, result);
  }

  @Test
  void passWhenLinkIsSuccessfullyParsed() throws IOException {
    File file = new File("src/test/resources/testSite.html");
    Document document = Jsoup.parse(file);
    List<String> links = Downloader.findDownloadLinks(document);

    String result = links.get(3);

    assertEquals("https://librinostri.catholica.cz/download/nasinec-1921-r.pdf", result);
  }

  @Test
  void passWhenDownloadFolderSuccessfullyWithValidPath() {
    PropertiesProvider mockPropertiesProvider = Mockito.mock(PropertiesProvider.class);
    String testPath = "/tmp";

    Downloader.changeDownloadFolder(mockPropertiesProvider, testPath);

    Mockito.verify(mockPropertiesProvider).setDownloadFolder(testPath);
  }

  @Test
  void passWhenDownloadFolderNotChangedWithInvalidPath() {
    PropertiesProvider mockPropertiesProvider = Mockito.mock(PropertiesProvider.class);
    String testPath = "/test/test";

    Downloader.changeDownloadFolder(mockPropertiesProvider, testPath);

    Mockito.verify(mockPropertiesProvider, Mockito.never()).setDownloadFolder(Mockito.any());
  }

  @Test
  void passWhenDownloadMethodReturnsNotZeroBytes() throws IOException {
    long result = Downloader.download("https://librinostri.catholica.cz/download/PitHaliIVaKlaPri-z.pdf",
        "/tmp/PitHaliIVaKlaPri-z.pdf");

    assertNotEquals(0, result);

    Files.deleteIfExists(Path.of("/tmp/PitHaliIVaKlaPri-z.pdf"));
  }

  @Test
  void passWhenDownloadMethodReturnZeroBytes() throws FileAlreadyExistsException {
    long result = Downloader.download("https://librinostri.catholica.cz/download/PitHaliIVaKlaPri-z.pdf",
        "/tmp/test/test.test");

    assertEquals(0, result);
  }

  @Test
  void passWhenDocmentReturned() {
    Book book = new Book("test",
        "https://librinostri.catholica.cz/kniha/1954-spor-dvou-monsignoru-a-emeritniho-presidenta",
        "test");
    Document testDocument = Downloader.getHtmlDocument(book);

    int result = testDocument.childNodeSize();

    assertEquals(2, result);
  }

  @Test
  void passWhenRuntimeEceptionThrown() {
    Book book = new Book("test",
        "https://librinori.cholica.sk",
        "test");
    assertThrows(RuntimeException.class, () -> Downloader.getHtmlDocument(book));
  }
}