package tools.downloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class DownloaderProvider implements IDownloader {
/*=================================================================================================
                                                  Methods
=================================================================================================*/
  @Override
  public long download(String link, Path path) throws IOException {
    URL url = new URL(link);
    InputStream inputStream = url.openStream();
    return Files.copy(inputStream, path);
  }
}
