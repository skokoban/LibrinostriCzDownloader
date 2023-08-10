package tools.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;

public class File {

/*=================================================================================================
                                                  Methods
=================================================================================================*/
  protected static Path createFile(String path) throws IOException {
    Path mPath = Path.of(path);
    return Files.createFile(mPath);
  }

  protected static void deleteFile(String path) throws IOException {
    Path mPath = Path.of(path);
    Files.deleteIfExists(mPath);
  }

  /**
   * Count checksum for given file. Returns 0 if file is empty.
   * @param path the path fle for which checksum be counted
   * @return value of CRC32 checksum. Returns -1 if checksum cannot be counted.
   */
  protected static long checksum(Path path) {
    byte[] fileBytes;
    try {
      fileBytes = Files.readAllBytes(path);
    } catch (IOException e) {
      return 0;
    }
    CRC32 rssFileCrc32 = new CRC32();
    rssFileCrc32.update(fileBytes);
    return rssFileCrc32.getValue();
  }
}
