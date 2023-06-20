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
  public long download(URL url, Path path) throws IOException {
    InputStream inputStream = url.openStream();
    return Files.copy(inputStream, path);
  }
}
