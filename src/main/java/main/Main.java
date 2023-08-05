package main;

import java.io.File;
import java.io.IOException;
import tools.config.Config;
import tools.config.LocationProvider;
import tools.config.PropertiesFactory;
import tools.config.PropertiesProvider;
import ui.Printer;

public class Main {
  /**
   * Check if there was given arguments or not with running application from CLI.
   * If there was no arguments given, prints main menu with possibility of choice task.
   * If there are arguments given then send arguments to further processing.
   * Also let know if there is unknown arguments given.
   * @param args array of string that should be checked.
   */
  public static void main(String[] args) {
    if (args.length > 0) {
      Printer.printUnknownArgumentError();
    }
    checkConfig();
    Menu.process();
  }

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
      PropertiesProvider propertiesProvider = PropertiesFactory.getPropertiesProvider();
      config.fillDefaultValues(propertiesProvider);
    }
  }
}
