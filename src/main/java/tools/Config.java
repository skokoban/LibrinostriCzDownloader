package tools;

import java.io.*;
import java.util.Properties;
import ui.Printer;

public class Config {
/*=================================================================================================
                                                      Attributes
=================================================================================================*/
  private final String CONFIGFILE_NAME = "librinostri-downloader.properties";
  private File configFileDir;
  private String configFileDirPath;
  private File configFile;

/*=================================================================================================
                                                          Constructor
=================================================================================================*/
  public Config() {
    configFileDirPath = System.getProperty("user.home") +
        File.separator +
        ".config" +
        File.separator +
        "librinostri-downlaoder";
    configFileDir = new File(configFileDirPath);
    configFile = new File(configFileDirPath + File.separator + CONFIGFILE_NAME);
  }
/*=================================================================================================
                                                          Methods
=================================================================================================*/
  public Boolean exists() {
    return configFile.exists();
  }

  /**
   * Creates text file with just one property consists download folder. Key is named
   * <code>downloadFolder</code>. Value is set to
   * <code>home_folder/librinostri-cz-downloader.</code>
   */
  public void createDefaultConfig(File configFileDir) {
    configFileDir.mkdirs();
    Properties properties = new Properties();
    try (OutputStream outputStream = new FileOutputStream(configFile)) {
      properties.setProperty("downloadFolder", System.getProperty("user.home") +
          File.separator +
          "librinostri-cz-downlaoder");
      properties.store(outputStream, "This is auto-generated properties file."
          + " Do not modify key. Value can be modified.");
    } catch (IOException e) {
      Printer.printFileCannotBeCreated();
    }

  }

  public String getProperty(String key) {
    Properties properties = new Properties();
    try (InputStream inputStream = new FileInputStream(configFile)) {
      properties.load(inputStream);
    } catch (NullPointerException e) {
      e.printStackTrace();
      return "";
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    }
    return properties.getProperty(key);
  }

  public boolean setProperty(String key, String value) { // opravit. pri zmene jednej hodnoty ostatne zahodi. vytvorit novy subor.
    Properties properties = new Properties();
    try (OutputStream outputStream = new FileOutputStream(configFile)) {
      properties.setProperty(key, value);
      properties.store(outputStream, "");
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
}
