package tools.config;
// hotové, testy ok
import java.io.File;
import java.io.IOException;

/**
 * Represents methods for operation with configuration file. It allows to create new
 * configuration file if none exists.
 */
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
  private static final String TMPDIR = "java.io.tmpdir";
  private static final String PROPERTY_DOWNLOAD_FOLDER_KEY = "downloadFolder";
  private static Config instance;
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
  /**
   * Returns instance of class Config.
   * @param config file where properties in key-value pairs are stored
   * @return instance of class config
   * @throws NullPointerException if given file points to null.
   */
  public static Config getInstance(File config) throws NullPointerException {
    if (config == null) throw new NullPointerException(REASON_NULL_EXCEPTION);
    if (instance == null) instance = new Config(config);
    return instance;
  }

  /**
   * Create necessary directories and file where configuration will be stored.
   * @throws IOException if creation of file failed.
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
   * Create necessary directories in path for new configuration file.
   * @return the file.
   */
  protected File createDirectoryPath() {
    String config = configFile.toString();
    int lastSlash = config.lastIndexOf("/");
    String configDir = config.substring(0, lastSlash);
    return new File(configDir);
  }

  /**
   * Fill default values to newly created empty config file.
   * @param properties interface for working with properties.
   */
  public void fillDefaultValues(Properties properties) {
    String downloadLocation = LocationProvider.defaultDownloadLocation();
    properties.setProperty(PROPERTY_DOWNLOAD_FOLDER_KEY, downloadLocation);
    properties.setProperty(CHECKSUM_KEY, " "); // because no checksum was counted so far
    properties.setProperty(RSS_URL_KEY, RSS_URL_VALUE);
    String rssFileLocation = System.getProperty(TMPDIR) + File.separator + RSS_FILE_NAME_VALUE;
    properties.setProperty(RSS_TEMP_LOCATION_KEY, rssFileLocation);
  }
}
