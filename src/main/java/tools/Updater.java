package tools;

import java.nio.file.Path;
import java.util.Objects;
import tools.config.ConfigProvider;
import tools.downloader.DownloaderProvider;

public class Updater {
/*=================================================================================================
                                                Attributes
=================================================================================================*/
/*=================================================================================================
                                                Constructor
=================================================================================================*/
  public Updater() {
    throw new IllegalStateException("Utility class");
  }
/*=================================================================================================
                                                Methods
=================================================================================================*/
  /*public boolean update() {
      // retrieve crc checksum from properties file as string
    ConfigProvider config = new ConfigProvider();
    long oldChecksum = config.getChecksum();
      // download actual xml file
    DownloaderProvider downloader= new DownloaderProvider();
    Path rssFile = downloader.downloadXML();
      // retrieve checksum for actual xml file
    long newChecksum = tools.file.File.checksum(rssFile);
      // check if checksums are equals or not
    return !Objects.equals(oldChecksum, newChecksum);
  }*/
}
