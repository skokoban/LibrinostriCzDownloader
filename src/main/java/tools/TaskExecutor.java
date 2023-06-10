package tools;

import main.Main;
import ui.Printer;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Instance of class <code>TaskStarter</code> deales with executing of apropriate tasks within the application.
 * Tasks are executed on two basis. First is by arguments given with running of application. Second basis works on
 * selecting options from menu.
 */
public class TaskExecutor {
/*======================================================================================================================
                                                    Attributes
======================================================================================================================*/
        // available arguments
    private final String ARG_DOWNLOAD   = "download";
    private final String ARG_DWNLD_DEST = "path --set";
    private final String ARG_SHOW_D_DEST= "path --show";
    private final String ARG_HELP       = "help";
    private final String ARG_ABOUT      = "about";
/*======================================================================================================================
                                                    Methods
======================================================================================================================*/
    /**
     * Check if there was given arguments or not with running application from CLI.
     * If there was no arguments given, prints main menu with possibility of choice task.
     * If there are arguments given then send arguments to further processing.
     * Also let know if there is unknown arguments given.
     * @param args array of string that should be checked.
     */
    public void startApp(String[] args) {
        switch (args.length) {
            case 0 -> {
                Printer.printMenu();
                try {functionSelector(handleNumberEntered());
                }
                catch (NoSuchElementException ignored) {}
            }
            case 1 -> {
                parseArgs(args[0]);
                Main.main(new String[0]);     // show menu after succesfully run task
            }
            default -> Printer.printInvalidCountOfArguments();
        }
    }

    /**
     * It runs apropriate task based on given String. Print Unknown argument error if String is not recognised.
     * @param arg String of known arguments.
     */
    public void parseArgs(String arg) {
        switch (arg) {
            case ARG_DOWNLOAD   -> functionSelector(1);
            case ARG_DWNLD_DEST -> functionSelector(2);
            case ARG_SHOW_D_DEST-> functionSelector(3);
            case ARG_HELP       -> Printer.printHelp();
            case ARG_ABOUT      -> Printer.printAbout();
            default             -> Printer.printUnknownArgumentError();
        }
    }

    /**
     * It runs apropriate task based on user input.
     * Below follows numbers associated with tasks:
     *  <p>1 - download</p>
     *  <p>2 - change download folder</p>
     *  <p>3 - not assigned</p>
     *  <p>4 - not assigned</p>
     *  <p>5 - exit</p>
     * @param selection Integer of number that task should be executed.
     */
    public void functionSelector(int selection) {
        switch (selection) {
/*
            case 1 -> new Executor().download();
            case 2 -> new Executor().changeDownloadLocation();
            case 3 -> new Executor().showDownloadLocation();
*/
            case 4 -> Printer.printHelp();
            case 5 -> Printer.printAbout();
            case 6 -> {return;}
            default ->Printer.printInvalidOptionEntered();
        }
        Main.main(new String[0]);     // show menu after succesfully task proceeded
    }

    /**
     * Handle number given by user.
     * @return Integer of number that user provided.
     */
    public int handleNumberEntered() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}