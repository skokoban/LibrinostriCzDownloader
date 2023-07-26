package main;

import executor.Executor;
import java.io.File;
import java.util.Scanner;
import tools.config.Config;
import tools.config.LocationProvider;
import ui.Printer;

public class Main {
  //todo pred stahvanim skontrolovat ci uz dany subor existuje. ak nie stiahnut novy. ak ano tak skontrolovat ci je kompletny - porovnat velkost. ak velkost nesedi stiahnut znovu. ak sedi preskocit a ist stahovat dalsi subor.
  /**
   * Check if there was given arguments or not with running application from CLI.
   * If there was no arguments given, prints main menu with possibility of choice task.
   * If there are arguments given then send arguments to further processing.
   * Also let know if there is unknown arguments given.
   * @param args array of string that should be checked.
   */
  public static void main(String[] args) {
    File configFile = LocationProvider.getConfigFile();
    Config config = Config.getInstance(configFile);
    config.checkConfig();
    if (args.length > 0) {
      Printer.printUnknownArgumentError();
    }
    while (true) {
      Printer.printMenu();
      int menuOption = handleIntEntered();
      switch (menuOption) {
        case 1 -> Executor.checkForUpdate();
        case 2 -> Executor.downloadNewFiles();
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

  public static int handleIntEntered() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextInt();
  }
}
