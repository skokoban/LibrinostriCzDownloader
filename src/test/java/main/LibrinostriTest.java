package main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

public class LibrinostriTest {

  private static Path emptyFile;
/*=================================================================================================
                                                Attributes
=================================================================================================*/
/*=================================================================================================
                                                Constructor
=================================================================================================*/
/*=================================================================================================
                                                Methods
=================================================================================================*/
  @Test
  void passWhenChecksumIsNotZero() throws IOException {
    //long checksum = Librinostri.countChecksum(getTestFile());

    //assertNotEquals(0, checksum);
  }

  Path getTestFile() {
    return Path.of("src/test/resources/testRSS.php");
  }

  Path getTestEmptyFile() throws IOException {
    emptyFile = Files.createTempFile("emptyTest", "tmp");
    return emptyFile;
  }

  @Test
  void passWhenEmptyFileChecksumIsZero() throws IOException {
    //long checksum = Librinostri.countChecksum(getTestEmptyFile());

    //assertEquals(0, checksum);
  }

  @AfterAll
  static void clean() throws IOException {
    Files.deleteIfExists(emptyFile);
  }
}
