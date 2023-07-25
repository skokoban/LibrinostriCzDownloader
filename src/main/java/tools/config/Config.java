package tools.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {
/*=================================================================================================
                                                          Attributes
=================================================================================================*/
  public static final String REASON_NULL_EX = "Instance cannot be created because "
      + "file you provided points to null";
  public static final String CHECKSUM_KEY = "checksum";
  public static final String RSS_URL_KEY = "rssURL";
  public static final String RSS_URL = "https://librinostri.catholica.cz/rss.php";
  private static final String RSS_TEMP_LOCATION_KEY = "rssTempLocation";
  private String RSS_TEMP_LOCATION_VALUE = null;
  private static Config instance;
  private final String DOWNLOADED_FOLDER_NAME = "librinostri-downlaoder";
  private final String PROPERTY_DOWNLOAD_FOLDER_KEY = "downloadFolder";
  private final String PROPERTIES_COMMENT = "This is auto-generated properties file." +
      " Do not modify key. Value can be modified.";
  private Path configFile;
/*=================================================================================================
                                             Constructor
=================================================================================================*/
  private Config(Path config) {
    this.configFile = config;
  }
/*=================================================================================================
                                             Methods
=================================================================================================*/
  public static Config getInstance(Path config) throws NullPointerException {
    if (config == null) throw new NullPointerException(REASON_NULL_EX);
    if (instance == null) {
      instance = new Config(config);
    }
    return instance;
  }

  /**
   * Create directory and file for specified config file path of instance.
   * @throws IOException if default file cannot be created.
   */
  public void createConfigFile() throws IOException {
    Path configDirPath = createDirectoryPath();
    Files.createDirectories(configDirPath);

    Files.createFile(configFile);
  }

  /**
   * Inspect weather config file in user´s home directory exists or not
   * @return true if config file already exists, false if not.
   */
  public Boolean exists() {
    return Files.exists(configFile);
  }

  protected Path createDirectoryPath() {
    String config = configFile.toString();
    int lastSlash = config.lastIndexOf("/");
    String configDir = config.substring(0, lastSlash);
    return Path.of(configDir);
  }

  public void fillDefaultValues(IProperties provider) {
    Path downloadLocation = LocationProvider.getDefaultDownloadLocation();
    String downloadLocationString = downloadLocation.toString();
    provider.setProperty(PROPERTY_DOWNLOAD_FOLDER_KEY, downloadLocationString);
    provider.setProperty(CHECKSUM_KEY, "");
    provider.setProperty(RSS_URL_KEY, RSS_URL);
    String xmlFile = System.getProperty("java.io.tmpdir") + File.separator + "rss.php";
    provider.setProperty(RSS_TEMP_LOCATION_KEY, xmlFile);
  }
}
