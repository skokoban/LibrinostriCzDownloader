package tools;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class Downloader {
/*=================================================================================================
                                                Attributes
=================================================================================================*/
/*=================================================================================================
                                                Constructor
=================================================================================================*/
/*=================================================================================================
                                                Methods
=================================================================================================*/
  public static long download(String link, String path) {
    Path mPath = Path.of(path);
    URL url;
    long length;
    try {
      url = new URL(link);
      InputStream inputStream = url.openStream();
      length = Files.copy(inputStream, mPath);
    } catch (IOException e) {
      length = 0;
    }
    return length;
  }
}
