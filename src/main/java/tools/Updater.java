package tools;

import java.util.Objects;
import tools.config.ConfigProvider;
import tools.file.XMLFile;

public class Updater {
/*=================================================================================================
                                                Attributes
=================================================================================================*/
/*=================================================================================================
                                                Constructor
=================================================================================================*/
  public Updater() {
    throw new IllegalStateException("Utility class");
  }
/*=================================================================================================
                                                Methods
=================================================================================================*/
  public static boolean update() {
      // retrieve crc checksum from properties file as string
    ConfigProvider config = new ConfigProvider();
    long oldChecksum = config.getChecksum();
      // download actual xml file
    String url = config.getRSSURL();
    String path = config.getRSSLocation();
    XMLFile xml = new XMLFile(url, path);
    xml.download();
    long newChecksum = xml.countChecksum();
      // check if checksums are equals or not
    if (!Objects.equals(oldChecksum, newChecksum)) {
      xml.saveChecksum(config);
      return true;
    }
    return false;
  }
}
