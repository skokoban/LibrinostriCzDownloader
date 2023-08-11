package tools.downloader;
// hotové, otestovať sa nedá
import java.io.File;
import java.util.Objects;
import tools.config.PropertiesProvider;
import tools.config.LocationProvider;
import tools.file.XMLFile;
import ui.Printer;

/**
 * Include method for evaluate if target website has changed.
 */
public class Updater {
/*=================================================================================================
                                                Attributes
=================================================================================================*/
  private Updater() {
    throw new IllegalStateException("Utility class");
  }
/*=================================================================================================
                                                Methods
=================================================================================================*/

  /**
   * Compares actual checksum against old checksum from previous update stored in configuration.
   */
  public static void update() {
    File configFile = LocationProvider.configFile();
    PropertiesProvider config = new PropertiesProvider(configFile);
    long oldChecksum = config.getChecksum();

    String url = config.getRSSURL();
    String path = config.getRSSLocation();
    XMLFile xml = new XMLFile(url, path);
    xml.download();
    long newChecksum = xml.countChecksum();

    if (!Objects.equals(oldChecksum, newChecksum)) {
      xml.saveChecksum(config);
      Printer.printUpdaterNewFiles();
    }
    Printer.printNoNewFiles();
    xml.deleteXML();
  }
}
