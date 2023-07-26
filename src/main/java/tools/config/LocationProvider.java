package tools.config;

import java.io.File;
import java.nio.file.Path;

/**
 * This class provide methods for gaining necessarry locations in filesystem.
 */
public class LocationProvider {
/*=================================================================================================
                                                Attributes
=================================================================================================*/
  private static final String PROPERTY_USER_HOME = "user.home";
  private static final String CONFIG_FOLDER_NAME = ".config";
  private static final String LIBRI_NOSTI_FOLDER_NAME = "librinostri-downloader";
  private static final String CONFIGFILE_NAME = "librinostri-downloader.properties";
/*=================================================================================================
                                                Constructors
=================================================================================================*/
  private LocationProvider() {
    throw new IllegalStateException("Utility class");
  }
/*=================================================================================================
                                                Methods
=================================================================================================*/
  /**
   * create and provide File where configuration is stored. File is located in users home directory
   * in folder named librinostri-downloader.
   * @return the file.
   */
  public static File getConfigFile() {
    String configFileString = System.getProperty(PROPERTY_USER_HOME) +
        File.separator +
        CONFIG_FOLDER_NAME +
        File.separator +
        LIBRI_NOSTI_FOLDER_NAME +
        File.separator +
        CONFIGFILE_NAME;
    return new File(configFileString);
  }

  /**
   * Provide default location where downloaded PDF files be saved. Is located in users home
   * directory
   * @return path to default download location.
   */
  public static Path getDefaultDownloadLocation() {
    String downlaodLocation = System.getProperty(PROPERTY_USER_HOME) +
        File.separator +
        LIBRI_NOSTI_FOLDER_NAME;
    return Path.of(downlaodLocation);
  }
}
