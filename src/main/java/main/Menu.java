package main;

import executor.Executor;
import java.util.Scanner;
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
      int menuOption = handleIntEntered();
      switch (menuOption) {
        case 1 -> Executor.update();
        case 2 -> Executor.downloadNewFiles();
        case 3 -> Executor.changeDownloadFolder();
        case 4 -> Executor.showDownloadFolder();
        case 5 -> Printer.printHelp();
        case 6 -> Printer.printAbout();
        case 7 -> {return;}
        default -> Printer.printUnknownMenuOptionError();
      }
    }
  }

  protected static int handleIntEntered() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextInt();
  }
}
