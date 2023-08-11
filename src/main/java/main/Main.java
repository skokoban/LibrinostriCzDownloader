package main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import tools.downloader.Updater;
import tools.config.Config;
import tools.config.PropertiesProvider;
import tools.config.LocationProvider;
import tools.config.Properties;
import tools.downloader.DownloadBooksFacade;
import tools.downloader.Downloader;
import ui.Printer;

public class Main {

  private static final File CONFIG_FILE = LocationProvider.configFile();
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
    process();
  }

  /**
   * Checks if config file already exists. If not, then new config file will be created.
   * This new file will be filled wit default values. Location of configuration file
   * is given from special class which is determined for providing locations needed
   * for this application.
   */
  private static void checkConfig() {
    Config config = Config.getInstance(CONFIG_FILE);
    if (Boolean.FALSE.equals(config.exists())) {
      try {
        config.createConfigFile();
      } catch (IOException e) {
        Printer.printCannotCreateConfigFile();
        System.exit(0);
      }
      Properties properties = new Properties(CONFIG_FILE);
      config.fillDefaultValues(properties);
    }
  }

  /**
   * Shows menu options and execute apropriate task.
   */
  public static void process() {
    while (true) {
      Printer.printMenu();
      PropertiesProvider propertiesProvider = new PropertiesProvider(CONFIG_FILE);
      Scanner scanner = new Scanner(System.in);
      int menuOption = scanner.nextInt();
      switch (menuOption) {
        case 1 -> Updater.update();
        case 2 -> DownloadBooksFacade.download(propertiesProvider);
        case 3 -> Downloader.changeDownloadFolder(propertiesProvider, getNextLine());
        case 4 -> System.out.println(propertiesProvider.getDownloadFolder());
        case 5 -> Printer.printUnknownMenuOptionError();
        case 6 -> Printer.printAbout();
        case 7 -> {return;}
        default -> Printer.printUnknownMenuOptionError();
      }
    }
  }

  private static String getNextLine() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }
}
