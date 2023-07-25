package tools.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class FileTest {

  private String testPath;

  @Test
    void passWhenCreatedPathIsNotNull() throws IOException {
      testPath = "test.txt";
      Files.deleteIfExists(Path.of(testPath));

      Path result = File.createFile(testPath);

      assertNotNull(result);

      Files.deleteIfExists(result);
    }

  @Test
    void passWhenFileExistenceIsTrue() throws IOException {
      testPath = "test.txt";
      Files.deleteIfExists(Path.of(testPath));

      Path result = File.createFile(testPath);

      assertTrue(Files.exists(result));
      assertTrue(Files.isRegularFile(result));

      Files.deleteIfExists(result);
    }

  @Test
    void passWhenCreatedFileIsRegular() throws IOException {
      testPath = "test.txt";
      Files.deleteIfExists(Path.of(testPath));

      Path result = File.createFile(testPath);

      assertTrue(Files.isRegularFile(result));

      Files.deleteIfExists(result);
    }

/*
  @Test
    void passWhenFromUrlWithOneSlashIsSucsesfullyNameCreated() {
      String testUrl= "http://www.test.com/test.pdf";

      String result = Book.retrieveName(testUrl);

      assertEquals("test.pdf", result);
    }

  @Test
    void passWhenFromUrlWithMultipleSlashesIsSucsesfullyNameCreated() {
      String testUrl= "http://www.test.com//files/old/test.pdf";

      String result = Book.retrieveName(testUrl);

      assertEquals("test.pdf", result);
    }

  @Test
    void passWhenNoNameAfterSlashReturnEmptyString() {
      String testUrl= "http://www.test.com/";

      String result = Book.retrieveName(testUrl);

      assertEquals("", result);
    }
*/

  @Test
    void passWhenReturnsSomeChecksum() throws IOException {
    byte[] fileBytes = {1, 2, 3, 4, 5};
    Path tempFile = Files.createTempFile("test", ".txt");
    Files.write(tempFile, fileBytes);

    long result = File.checksum(tempFile);

    assertNotEquals(0, result);

    Files.deleteIfExists(tempFile);
  }
}