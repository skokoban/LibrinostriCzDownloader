package ui;

import main.Book;

import java.util.LinkedList;

public class Printer {

/*--------------------------------------------------------------------------------------------------
                                            Methods
--------------------------------------------------------------------------------------------------*/

    public static void printText(String text) {
        System.out.println(text);
    }
    public static void printMenu() {
        System.out.println(Strings.MENU.getText());
    }

    public static void printHelp() {
        System.out.println(Strings.HELP.getText());
    }

    public static void printUnknownArgumentError() {
        System.out.println(Strings.UNKNOWN_ARGS_ERROR.getText());
    }

    public static void printInvalidCountOfArguments() {
        System.out.println(Strings.INVALID_COUNT_OF_ARGUMENTS.getText());
    }

    public static void printUpdateIntro() {
        System.out.println(Strings.UPDATER_INTRO.getText());
    }

    public static void printFileCannotBeCreated() {
        System.out.println(Strings.CANNOT_CREATE_NEW_FILE.getText());
    }

    public static void printAskDownload() {
        System.out.println(Strings.ASK_FOR_DOWNLOAD.getText());
    }

    public static void printCannotWriteToFile() {
        System.out.println(Strings.CANNOT_WRITE_TO_FILE.getText());
    }
}
