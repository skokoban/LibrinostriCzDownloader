package tools.downloader;

import java.io.IOException;
import java.nio.file.Path;

public interface IDownloader {
  long download(String link, Path path) throws IOException;
}