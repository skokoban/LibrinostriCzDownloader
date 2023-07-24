package main;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.Executor;
import tools.config.Config;
import tools.config.ConfigLocationProvider;
import tools.config.PropertiesProvider;
import ui.Printer;

public class Main {
  //todo na zaciatku stahovania vytvori vsetky pdf subory popredu a az potom stahuje. ak nastane prerusenie tak ostanu prazdne nachystane subory. treba spravit aby sa vymazali. skontrolovat ci bolo stiahnutie komplet. alebo skontrolovat velkost suboru ci je 0.
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
        //case 1 -> Executor.downloadNewFiles();
        //case 2 -> Executor.changeDownloadFolder();
        //case 3 -> Executor.showDownloadFolder();
        case 4 -> Printer.printHelp();
        case 5 -> Printer.printAbout();
        case 6 -> {
          return;
        }
        default -> Printer.printUnknownArgumentError();
      }
    }
  }

  private static void checkConfig() {
    Path configLocation = ConfigLocationProvider.getConfigFileLocation();
    Config config = Config.getInstance(configLocation);
    if (!config.exists()) {
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
