package tools.config;
// hotové, netreba testovať
import java.io.File;

public class PropertiesProvider {
/*=================================================================================================
                                                Attributes
=================================================================================================*/
  private static final String DOWNLOAD_FOLDER_KEY = "downloadFolder";
  private static final String CHECKSUM_KEY = "checksum";
  private static final String RSS_LOC_KEY = "rssLocation";
  private static final String RSS_URL_KEY = "rssURL";
  Properties properties;
/*=================================================================================================
                                                Constructor
=================================================================================================*/
  public PropertiesProvider(File configFile) {
    properties = new Properties(configFile);
  }
/*=================================================================================================
                                                Methods
=================================================================================================*/
  public long getChecksum() {
    String checksum = properties.getProperty(CHECKSUM_KEY);
    return Long.parseLong(checksum);
  }

  public void setChecksum(String checksum) {
    properties.setProperty(CHECKSUM_KEY, checksum);
  }

  public String getDownloadFolder() {
    return properties.getProperty(DOWNLOAD_FOLDER_KEY);
  }

  public void setDownloadFolder(String path) {
    properties.setProperty(DOWNLOAD_FOLDER_KEY, path);
  }

  public String getRSSLocation() {
    return properties.getProperty(RSS_LOC_KEY);
  }

  public String getRSSURL() {
    return properties.getProperty(RSS_URL_KEY);
  }
}
