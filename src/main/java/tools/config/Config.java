package tools.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {
/*=================================================================================================
                                                      Attributes
=================================================================================================*/
  private static Config instance;
  private final String DOWNLOADED_FOLDER_NAME = "librinostri-downlaoder";
  private final String PROPERTY_DOWNLOAD_FOLDER = "downloadFolder";
  private final String PROPERTIES_COMMENT = "This is auto-generated properties file." +
      " Do not modify key. Value can be modified.";
  private File configFile;
/*=================================================================================================
                                             Constructor
=================================================================================================*/
  private Config(File config) {
    this.configFile = config;
  }
/*=================================================================================================
                                             Methods
=================================================================================================*/
  public static Config getInstance(File config) {
    if (instance == null) {
      instance = new Config(config);
    }
    return instance;
  }

  public void createDefault() throws IOException {
    Path configDirPath = Path.of(createDirectoryPath());
    Files.createDirectories(configDirPath);

    String config = configFile.getPath();
    Path configPath = Path.of(config);
    Files.createFile(configPath);
  }

  /**
   * Inspect weather config file in user´s home directory exists or not
   * @return true if config file already exists, false if not.
   */
  public Boolean exists() {
    return configFile.exists();
  }

  /**
   * @return Returns path to config file.
   */
  @Override
  public String toString() {
    return configFile.toString();
  }

  protected String createDirectoryPath() {
    String config = configFile.getPath();
    int lastSlash = config.lastIndexOf("/");
    String configDir = config.substring(0, lastSlash);
    return config;
  }
}
