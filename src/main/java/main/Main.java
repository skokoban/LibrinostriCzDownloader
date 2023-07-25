package main;

import executor.Executor;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import tools.config.Config;
import tools.config.LocationProvider;
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
    checkConfig();
    if (args.length > 0) {
      Printer.printUnknownArgumentError();
    }
    while (true) {
      Printer.printMenu();
      int menuOption = handleIntEntered();
      switch (menuOption) {
        case 1 -> Executor.checkForUpdate();
        case 2 -> Executor.downloadNewFiles(); //todo spravit vytvaranie adresarov pre pdf podla nazvu knih.
        case 3 -> Executor.changeDownloadFolder();
        case 4 -> Executor.showDownloadFolder();
        case 5 -> Printer.printHelp();
        case 6 -> Printer.printAbout();
        case 7 -> {
          return;
        }
        default -> Printer.printUnknownArgumentError();
      }
    }
  }

  private static void checkConfig() {
    Path configLocation = LocationProvider.getConfigFileLocation();
    Config config = Config.getInstance(configLocation);
    if (Boolean.FALSE.equals(config.exists())) {
      try {
        config.createConfigFile();
      } catch (IOException e) {
        Printer.printCannotCreateConfigFile();
        System.exit(0);
      }
      config.fillDefaultValues(new PropertiesProvider());
    }
  }

  public static int handleIntEntered() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextInt();
  }
}
