package main;

import java.io.File;
import java.io.IOException;
import tools.config.Config;
import tools.config.LocationProvider;
import tools.config.PropertiesProvider;
import ui.Printer;

public class Main {
  /**
   * Check if there was given arguments or not with running application from CLI.
   * If there were arguments given then prints error message. In any case displays menu.
   * @param args array of string that should be checked.
   */
  public static void main(String[] args) {
    if (args.length > 0) {
      Printer.printUnknownArgumentError();
    }
    checkConfig();
    Menu.process();
  }

  /**
   * Checks if config file already exists. If not, then new config file will be created.
   * This new file will be filled wit default values. Location of configuration file
   * is given from special class which is determined for providing locations needed
   * for this applivation.
   */
  private static void checkConfig() {
    File configFile = LocationProvider.configFile();
    Config config = Config.getInstance(configFile);
    if (Boolean.FALSE.equals(config.exists())) {
      try {
        config.createConfigFile();
      } catch (IOException e) {
        Printer.printCannotCreateConfigFile();
        System.exit(0);
      }
      PropertiesProvider propertiesProvider = new PropertiesProvider(configFile);
      config.fillDefaultValues(propertiesProvider);
    }
  }
}
