package tools.config;

import java.io.File;
import java.io.IOException;

public class Config {

  /*=================================================================================================
                                             Attributes
  =================================================================================================*/
  private static final String REASON_NULL_EXCEPTION = "Instance cannot be created because "
      + "file you provided points to null";
  private static final String CHECKSUM_KEY = "checksum";
  private static final String RSS_URL_KEY = "rssURL";
  private static final String RSS_URL_VALUE = "https://librinostri.catholica.cz/rss.php";
  private static final String RSS_TEMP_LOCATION_KEY = "rssLocation";
  private static final String RSS_FILE_NAME_VALUE = "rss.php";
  public static final String TMPDIR = "java.io.tmpdir";
  private static Config instance;
  private static final String PROPERTY_DOWNLOAD_FOLDER_KEY = "downloadFolder";
  private File configFile;
/*=================================================================================================
                                             Constructor
=================================================================================================*/
  private Config(File configFile) {
    this.configFile = configFile;
  }
/*=================================================================================================
                                             Methods
=================================================================================================*/
  public static Config getInstance(File config) throws NullPointerException {
    if (config == null) throw new NullPointerException(REASON_NULL_EXCEPTION);
    if (instance == null) instance = new Config(config);
    return instance;
  }

  /**
   * Create directory and file for specified config file path of instance.
   * @throws IOException if default file cannot be created.
   */
  public void createConfigFile() throws IOException {
    File configDir = createDirectoryPath();
    configDir.mkdirs();
    configFile.createNewFile();
  }

  public boolean exists() {
    return configFile.exists();
  }

  /**
   * Create Path object with path to directory where config file be located.
   * @return the path
   */
  protected File createDirectoryPath() {
    String config = configFile.toString();
    int lastSlash = config.lastIndexOf("/");
    String configDir = config.substring(0, lastSlash);
    return new File(configDir);
  }

  /**
   * Fill default values to newly created empty config file.
   * @param propertiesProvider interface for working with properties.
   * @param locationProvider provider for locations on filesystem.
   */
  public void fillDefaultValues(PropertiesProvider propertiesProvider,
      LocationProvider locationProvider) {
    String downloadLocation = locationProvider.defaultDownloadLocation();
    propertiesProvider.setProperty(PROPERTY_DOWNLOAD_FOLDER_KEY, downloadLocation);
    propertiesProvider.setProperty(CHECKSUM_KEY, " ");
    propertiesProvider.setProperty(RSS_URL_KEY, RSS_URL_VALUE);
    String rssFileLocation = System.getProperty(TMPDIR) + File.separator + RSS_FILE_NAME_VALUE;
    propertiesProvider.setProperty(RSS_TEMP_LOCATION_KEY, rssFileLocation);
  }
}
