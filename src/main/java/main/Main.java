package main;

import tools.Config;
import tools.TaskExecutor;
import ui.Printer;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class Main { //todo pridat jednoduche overenie ci je nieco nove na stranke naiesto stahovania vsetkych knih. pomocou hashu zrejme
    /**
     * Check if there was given arguments or not with running application from CLI.
     * If there was no arguments given, prints main menu with possibility of choice task.
     * If there are arguments given then send arguments to further processing.
     * Also let know if there is unknown arguments given.
     * @param args array of string that should be checked.
     */
    public static void main(String[] args) {
        // check config file
        Config config = new Config();
        if (!config.exists()) {
            config.createDefaultConfig();
        }
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

    public static int handleIntEntered() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
