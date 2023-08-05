package tools.config;

import java.io.File;

public class ConfigProvider {
/*=================================================================================================
                                                Attributes
=================================================================================================*/
  private static final String DOWNLOAD_FOLDER_KEY = "downloadFolder";
  private static final String CHECKSUM_KEY = "checksum";
  private static final String RSS_LOC_KEY = "rssLocation";
  private static final String RSS_URL_KEY = "rssURL";
  PropertiesProvider propertiesProvider;
/*=================================================================================================
                                                Constructor
=================================================================================================*/
  public ConfigProvider() {
    File config = LocationProvider.configFile();
    propertiesProvider = new PropertiesProvider(config);
  }
/*=================================================================================================
                                                Methods
=================================================================================================*/
  public long getChecksum() {
    String checksum = propertiesProvider.getProperty(CHECKSUM_KEY);
    return Long.getLong(checksum);
  }
  public String getDownloadFolder() {
    return propertiesProvider.getProperty(DOWNLOAD_FOLDER_KEY);
  }
  public String getRSSLocation() {
    return propertiesProvider.getProperty(RSS_LOC_KEY);
  }
  public String getRSSURL() {
    return propertiesProvider.getProperty(RSS_URL_KEY);
  }
}
