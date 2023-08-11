package tools.config;

import java.io.File;

public class PropertiesProvider {
/*=================================================================================================
                                                Attributes
=================================================================================================*/
  private static final String DOWNLOAD_FOLDER_KEY = "downloadFolder";
  private static final String CHECKSUM_KEY = "checksum";
  private static final String RSS_LOC_KEY = "rssLocation";
  private static final String RSS_URL_KEY = "rssURL";
  private final Properties PROPERTIES;
/*=================================================================================================
                                                Constructor
=================================================================================================*/
  public PropertiesProvider(File configFile) {
    PROPERTIES = new Properties(configFile);
  }
/*=================================================================================================
                                                Methods
=================================================================================================*/
  public long getChecksum() {
    String checksum = PROPERTIES.getProperty(CHECKSUM_KEY);
    return Long.parseLong(checksum);
  }

  public void setChecksum(String checksum) {
    PROPERTIES.setProperty(CHECKSUM_KEY, checksum);
  }

  public String getDownloadFolder() {
    return PROPERTIES.getProperty(DOWNLOAD_FOLDER_KEY);
  }

  public void setDownloadFolder(String path) {
    PROPERTIES.setProperty(DOWNLOAD_FOLDER_KEY, path);
  }

  public String getRSSLocation() {
    return PROPERTIES.getProperty(RSS_LOC_KEY);
  }

  public String getRSSURL() {
    return PROPERTIES.getProperty(RSS_URL_KEY);
  }
}
