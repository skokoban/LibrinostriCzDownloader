package main;

import tools.config.Config;
import tools.TaskExecutor;
import ui.Printer;

import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
  //todo pridat jednoduche overenie ci je nieco nove na stranke naiesto stahovania vsetkych knih. pomocou hashu zrejme
  //todo pri novom spustení a vybraní možnosti 3 vypíše cestu "aaaaaaaaaaa". zistit prečo a opraviť - ZISTENE v /res je subor odkial sa to berie
  //todo pri kontrole existencie knihy pocitat hash lebo nazov sa moze menit
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
    switch (args.length) {
      case 0 -> {
        Printer.printMenu();
        try { TaskExecutor taskExecutor = new TaskExecutor();
          taskExecutor.functionSelector(handleIntEntered());
        } catch (InputMismatchException ignored) {
          Printer.printInvalidOptionEntered();
          main(new String[0]);
        }
      }
      case 1 -> {
        TaskExecutor taskExecutor = new TaskExecutor();
        taskExecutor.parseArgs(args[0]);
        main(new String[0]);     // show menu after succesfully run task
      }
      default -> Printer.printInvalidCountOfArguments();
    }
  }

  private static void checkConfig() {
    Config config = new Config();
    if (!config.exists()) {
      //config.createDefaultConfig();
    }
  }

  public static int handleIntEntered() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextInt();
  }
}
