package tools.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {
/*=================================================================================================
                                                      Attributes
=================================================================================================*/
  private static final String CONFIGFILE_NAME = "librinostri-downloader.properties";
  private static final String PROPERTY_USER_HOME = "user.home";
  private static final String CONFIG_FOLDER_NAME = ".config";
  private static final String LIBRI_NOSTI_FOLDER_NAME = "librinostri-downlaoder";
  private final String DOWNLOADED_FOLDER_NAME = "librinostri-downlaoder";
  private final String PROPERTY_DOWNLOAD_FOLDER = "downloadFolder";
  private final String PROPERTIES_COMMENT = "This is auto-generated properties file." +
      " Do not modify key. Value can be modified.";
  private static File configFile;
/*=================================================================================================
                                                 Methods
=================================================================================================*/
  public static Path createDefault() throws IOException {
    String configFileDir = System.getProperty(PROPERTY_USER_HOME) +
        File.separator +
        CONFIG_FOLDER_NAME +
        File.separator +
        LIBRI_NOSTI_FOLDER_NAME;
    Path configFileDirPath = Path.of(configFileDir);
    Files.createDirectories(configFileDirPath);
    Path configFilePath = Path.of(configFileDirPath + File.separator + CONFIGFILE_NAME);
    return Files.createFile(configFilePath);
  }

  /**
   * Inspect weather config file in user´s home directory exists or not
   * @return true if config file already exists, false if not.
   */
  public static Boolean exists() {
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
