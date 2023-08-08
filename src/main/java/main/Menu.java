package main;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import tools.Updater;
import tools.config.ConfigProvider;
import ui.Printer;

public class Menu {
/*=================================================================================================
                                                Attributes
=================================================================================================*/
/*=================================================================================================
                                                Constructor
=================================================================================================*/
private Menu() {
  throw new IllegalStateException("Utility class");
}
/*=================================================================================================
                                                Methods
=================================================================================================*/
  public static void process() {
    while (true) {
      Printer.printMenu();
      int menuOption = handleInt();
      switch (menuOption) {
        case 1 -> update();
        case 2 -> Downloader.download();
        case 3 -> changeDownloadFolder();
        case 4 -> showDownloadFolder();
        case 5 -> Printer.printUnknownMenuOptionError();
        case 6 -> Printer.printAbout();
        case 7 -> {return;}
        default -> Printer.printUnknownMenuOptionError();
      }
    }
  }
  protected static void changeDownloadFolder() {
    Printer.printNewDownloadLocAsking();
    String givenPath = handleLine();
    Path path = Path.of(givenPath);
    if (Files.notExists(path)) {
      Printer.printPathNotEists();
      return;
    }
    ConfigProvider configProvider = new ConfigProvider();
    configProvider.setDownloadFolder(givenPath);
  }

  protected static void showDownloadFolder() {
    ConfigProvider configProvider = new ConfigProvider();
    System.out.println(configProvider.getDownloadFolder());
  }

  protected static void update() {
    if (Updater.update()) Printer.printUpdaterNewFiles();
    else Printer.printNoNewFiles();
  }

  protected static int handleInt() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextInt();
  }

  protected static String handleLine() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }
}
