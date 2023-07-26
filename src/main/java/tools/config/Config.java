package tools.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import ui.Printer;

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
    if (config == null) throw new NullPointerException(REASON_NULL_EX);
    if (instance == null) {
      instance = new Config(config);
    }
    return instance;
  }

  /**
   * Check if config file already exists. If yes then nothing happen.
   * If not then config file with default values be created.
   */
  public void checkConfig() {
    if (Boolean.FALSE.equals(configFile.exists())) {
      try {
        createConfigFile();
      } catch (IOException e) {
        Printer.printCannotCreateConfigFile();
        System.exit(0);
      }
      fillDefaultValues(new PropertiesProvider());
    }
  }

  /**
   * Create directory and file for specified config file path of instance.
   * @throws IOException if default file cannot be created.
   */
  public void createConfigFile() throws IOException {
    Path configDirPath = createDirectoryPath();
    Files.createDirectories(configDirPath);
    configFile.createNewFile();
  }

  /**
   * @return true if config file already exists, false if not.
   */
  public Boolean exists() {
    return configFile.exists();
  }

  /**
   * Create Path object with path to directory where config file be located.
   * @return the path
   */
  protected Path createDirectoryPath() {
    String config = configFile.toString();
    int lastSlash = config.lastIndexOf("/");
    String configDir = config.substring(0, lastSlash);
    return Path.of(configDir);
  }

  /**
   * Fill default values to newly created empty config file.
   * @param provider interface for working with properties.
   */
  protected void fillDefaultValues(IProperties provider) {
    Path downloadLocation = LocationProvider.getDefaultDownloadLocation();
    String downloadLocationString = downloadLocation.toString();
    provider.setProperty(PROPERTY_DOWNLOAD_FOLDER_KEY, downloadLocationString);
    provider.setProperty(CHECKSUM_KEY, "");
    provider.setProperty(RSS_URL_KEY, RSS_URL);
    String xmlFile = System.getProperty("java.io.tmpdir") + File.separator + "rss.php";
    provider.setProperty(RSS_TEMP_LOCATION_KEY, xmlFile);
  }
}
