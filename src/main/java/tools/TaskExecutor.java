package tools;

import ui.Printer;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Instance of class <code>TaskStarter</code> handles arguments given at start of application.
 */
public class TaskExecutor {
/*======================================================================================================================
                                                    Attributes
======================================================================================================================*/
        // usable arguments
    private final String ARG_DOWNLOAD   = "download";
    private final String ARG_DWNLD_DEST = "path";
    private final String ARG_HELP       = "help";
    private final String ARG_ABOUT      = "about";
/*======================================================================================================================
                                                    Methods
======================================================================================================================*/
    /**
     * Runs apropriate task based on given arguments.
     * @param args array of string that should be checked.
     */
    public void runTask(String[] args) {
        switch (args.length) {
            case 0 -> {
                Printer.printMenu();
                try {
                    functionSelector(handleNumberEntered());
                }
                catch (NoSuchElementException ignored) {}
            }
            case 1 -> {
                parseArgs(args[0]);
                runTask(new String[0]);     // show menu after succesfully run task
            }
            default -> Printer.printInvalidCountOfArguments();
        }
    }

    public void parseArgs(String arg) {
        switch (arg) {
            case ARG_DOWNLOAD   -> functionSelector(1);
            case ARG_DWNLD_DEST -> functionSelector(2);
            case ARG_HELP       -> Printer.printHelp();
            case ARG_ABOUT      -> Printer.printAbout();
            default             -> Printer.printUnknownArgumentError();
        }
    }

    public void functionSelector(int selection) {
        switch (selection) {
            case 1  -> new Executor().download();
            case 2  -> new Executor().changeDownloadLocation();
            case 5 -> {return;}
        }
        runTask(new String[0]);     // show menu after succesfully task proceeded
    }

    public int handleNumberEntered() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
