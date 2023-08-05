package tools.downloader;

import java.nio.file.Path;
import tools.Downloader;
import tools.config.ConfigProvider;

public class DownloaderProvider {
  private Downloader downloader;
/*=================================================================================================
                                                  Constructors
=================================================================================================*/
  public DownloaderProvider() {}
  public DownloaderProvider(Downloader downloader) {
    this.downloader = downloader;
  }
/*=================================================================================================
                                                  Methods
=================================================================================================*/
  public Path downloadXML() {
    ConfigProvider configProvider = new ConfigProvider();
    String link = configProvider.getRSSURL();
    String mPath = configProvider.getRSSLocation();
    Path path = Path.of(mPath);
    downloader.download(link, path);
    return path;
  }

 /* public File downloadPDFs() {
    File file = downloader.download();
    return new File("");
  }*/
}
