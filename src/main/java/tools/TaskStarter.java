package tools;

import ui.Printer;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Instance of class <code>TaskStarter</code> handles arguments given at starting of application.
 */
public class TaskStarter {

/*--------------------------------------------------------------------------------------------------
                                            Attributes
--------------------------------------------------------------------------------------------------*/

    private final String ARG_UPDATE   = "update";
    private final String ARG_DOWNLOAD = "download";
    private final String ARG_HELP     = "help";

/*--------------------------------------------------------------------------------------------------
                                            Constructors
--------------------------------------------------------------------------------------------------*/

    /**
     * Constructs handler that takes arguments as array of strings.
     * @param args array of strings that should be proceeded.
     */
    public TaskStarter(String[] args) {
        runTask(args);
    }
/*--------------------------------------------------------------------------------------------------
                                            Methods
--------------------------------------------------------------------------------------------------*/

    /**
     * Runs apropriate task based on arguments given.
     * @param args array of string that should be checked.
     */
    public void runTask(String[] args) {
        switch (args.length) {
            case 0 -> {
                Printer.printMenu();
                try {
                    functionExecutor(handleNumberEntered());
                }
                catch (NoSuchElementException ignored) {}
            }
            case 1 -> {
                parseArgs(args);
                runTask(new String[0]);     // show menu after succesfully run task
            }
            default -> Printer.printInvalidCountOfArguments();
        }
    }

    public void parseArgs(String[] args) {
        switch (args[0]) {
            case ARG_HELP     -> Printer.printHelp();
            default           -> Printer.printUnknownArgumentError();
        }
    }


    public void functionExecutor(int selection) {
        switch (selection) {
            case 3 -> Printer.printHelp();
            default -> {
            }
        }
        runTask(new String[0]);     // show menu after succesfully task proceed
    }

    public int handleNumberEntered() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public String handleLineEntered() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
