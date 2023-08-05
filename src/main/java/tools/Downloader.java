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
  public long download(String link, Path path) {
    URL url;
    long length;
    try {
      url = new URL(link);
      InputStream inputStream = url.openStream();
      length = Files.copy(inputStream, path);
    } catch (IOException e) {
      length = 0;
    }
    return length;
  }
}
