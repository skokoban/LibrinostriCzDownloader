package tools.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import tools.config.IProperties;
import tools.config.PropertiesProvider;

public class File {
/*=================================================================================================
                                                  Methods
=================================================================================================*/
  public static Path create(String path) throws IOException {
    Path mPath = Path.of(path);
    return Files.createFile(mPath);
  }

  /**
   * Count checksum for given file. Returns 0 if file is empty.
   * @param path the path fle for which checksum be counted
   * @return value of CRC32 checksum
   * @throws IOException if checksum cannot be counted.
   */
  public static long checksum(Path path) throws IOException {
    byte[] fileBytes = Files.readAllBytes(path);
    CRC32 rssFileCrc32 = new CRC32();
    rssFileCrc32.update(fileBytes);
    return rssFileCrc32.getValue();
  }

  public static java.io.File getRSSFile() {
    IProperties properties= new PropertiesProvider();
    String rssFile = properties.getProperty("RSS file temporary location");
    return new java.io.File(rssFile);
  }

  public static Path getRSSFilePath() {
    IProperties properties= new PropertiesProvider();
    String rssFile = properties.getProperty("RSS file temporary location");
    return Path.of(rssFile);
  }
}
