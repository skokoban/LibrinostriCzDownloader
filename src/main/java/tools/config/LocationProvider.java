package tools.config;

import java.io.File;

/**
 * This class provide methods for gaining necessarry locations in filesystem.
 */
public class LocationProvider {
/*=================================================================================================
                                                Attributes
=================================================================================================*/
  private final String PROPERTY_USER_HOME = "user.home";
  private final String CONFIG_FOLDER_NAME = ".config";
  private final String LIBRI_NOSTI_FOLDER_NAME = "librinostri-downloader";
  private final String CONFIGFILE_NAME = "librinostri-downloader.properties";
/*=================================================================================================
                                                Constructors
=================================================================================================*/
/*=================================================================================================
                                                Methods
=================================================================================================*/
  /**
   * create and provide File where configuration is stored. File is located in users home directory
   * in folder named librinostri-downloader.
   * @return the file.
   */
  public File configFile() {
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
  public String defaultDownloadLocation() {
    return System.getProperty(PROPERTY_USER_HOME) +
           File.separator +
           LIBRI_NOSTI_FOLDER_NAME;
  }
}
