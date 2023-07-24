package tools.config;

import java.io.File;
import java.nio.file.Path;

public class LocationProvider {
/*=================================================================================================
                                                Attributes
=================================================================================================*/
  private static final String PROPERTY_USER_HOME = "user.home";
  private static final String CONFIG_FOLDER_NAME = ".config";
  private static final String LIBRI_NOSTI_FOLDER_NAME = "librinostri-downloader";
  private static final String CONFIGFILE_NAME = "librinostri-downloader.properties";
/*=================================================================================================
                                                Methods
=================================================================================================*/
  public static Path getConfigFileLocation() {
    String configFileString = System.getProperty(PROPERTY_USER_HOME) +
        File.separator +
        CONFIG_FOLDER_NAME +
        File.separator +
        LIBRI_NOSTI_FOLDER_NAME +
        File.separator +
        CONFIGFILE_NAME;
    return Path.of(configFileString);
  }

  public static Path getDefaultDownloadLocation() {
    String downlaodLocation = System.getProperty(PROPERTY_USER_HOME) +
        File.separator +
        LIBRI_NOSTI_FOLDER_NAME;
    return Path.of(downlaodLocation);
  }
}
