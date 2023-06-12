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
  private final String CONFIGFILE_NAME = "librinostri-downloader.properties";
  private final String PROPERTY_USER_HOME = "user.home";
  private final String CONFIG_FOLDER_NAME = ".config";
  private final String LIBRI_NOSTI_FOLDER_NAME = "librinostri-downlaoder";
  private final String DOWNLOADED_FOLDER_NAME = "librinostri-downlaoder";
  private final String PROPERTY_DOWNLOAD_FOLDER = "downloadFolder";
  private final String PROPERTIES_COMMENT = "This is auto-generated properties file." +
      " Do not modify key. Value can be modified.";
  private File configFile;
/*=================================================================================================
                                             Constructor
=================================================================================================*/
  private Config() {
    String configFileString = System.getProperty(PROPERTY_USER_HOME) +
        File.separator +
        CONFIG_FOLDER_NAME +
        File.separator +
        LIBRI_NOSTI_FOLDER_NAME +
        File.separator +
        CONFIGFILE_NAME;
    configFile = new File(configFileString);
  }
/*=================================================================================================
                                             Methods
=================================================================================================*/
  public static Config getInstance() {
    if (instance == null) {
      instance = new Config();
    }
    return instance;
  }

  public Path createDefault() throws IOException {
    String configFileDirString = System.getProperty(PROPERTY_USER_HOME) +
        File.separator +
        CONFIG_FOLDER_NAME +
        File.separator +
        LIBRI_NOSTI_FOLDER_NAME;
    Path configFileDirPath = Path.of(configFileDirString);
    Files.createDirectories(configFileDirPath);

    String configFilePathString = configFileDirPath + File.separator + CONFIGFILE_NAME;
    Path configFilePath = Path.of(configFilePathString);
    return Files.createFile(configFilePath);
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
}
