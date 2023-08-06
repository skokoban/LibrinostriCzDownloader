package tools.downloader;

import java.nio.file.Path;
import tools.Downloader;
import tools.config.ConfigProvider;

public class DownloaderProvider {
  private Downloader downloader;
  private ConfigProvider configProvider;
  private final String rssLink;
  private final String rssPath;
/*=================================================================================================
                                                  Constructors
=================================================================================================*/
  public DownloaderProvider() {
    rssLink = configProvider.getRSSURL();
    rssPath = configProvider.getRSSLocation();
  }
/*=================================================================================================
                                                  Methods
=================================================================================================*/
  public Path downloadXML() {
    downloader.download(rssLink, rssPath);
    return Path.of(rssPath);
  }

/*  public boolean downloadPDFs() {
      // stiahnut xml
    downloader.download(rssLink, rssPath);
      // najst linky na knihy

      // najst ku kazdej knihy odkazy na stiahnutie pdfs

      // povymazávať pdfs, ktoré sa už nachádzajú v zložke pre sťahovanie

      // --začína samotné sťahovanie
      //
    return new File(" ");
  }*/
}
