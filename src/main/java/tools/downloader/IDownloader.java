package tools.downloader;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

public interface IDownloader {
  long download(URL url, Path path) throws IOException;
}