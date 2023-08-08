package ui;

import java.io.File;
import java.util.Map;

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

  public static void printUnknownMenuOptionError() {
    System.out.println(Strings.UNKNOWN_MENU_OPTION_ERROR.getText());
  }

  public static void printInvalidCountOfArguments() {
    System.out.println(Strings.INVALID_COUNT_OF_ARGUMENTS.getText());
  }

  public static void printUpdaterNewFiles() {
    System.out.println(Strings.UPDATER_NEW_FILES.getText());
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

  public static void printAbout() {
    System.out.println(Strings.ABOUT.getText());
  }

    public static void printOK() {
    System.out.println(Strings.OK.getText());
  }

  public static void printDownloading(String PDFFileName) {
    System.out.print(Strings.DOWNLOADING.getText() + PDFFileName);
  }

  public static void printNewDownloadLocAsking() {
    System.out.print(Strings.NEW_BOOKS_FOLDER.getText());
  }

  public static void printDownloadingError() {
    System.out.println(Strings.ERROR_DOWNLOADING.getText());
  }

  public static void printNoNewFiles() {
    System.out.println(Strings.NO_NEW_FILES.getText());
  }

  public static void printInvalidOptionEntered() {
    System.out.println(Strings.ERROR_INVALID_MENU_OPTION.getText());
  }

  public static void printCannotSetPropertyBadFile() {
    System.out.println(Strings.CANNOT_READ_FROM_FILE.getText());
  }

  public static void printCannotCreateConfigFile() {
    System.out.println(Strings.CANNOT_CREATE_CONFIG_FILE.getText());
  }

  public static void printXMLParsingError() {
    System.out.println(Strings.XML_PARSE_ERROR_TEXT.getText());
  }

  public static void printCannotFindDownloadLinksForBook() {
    System.out.print(Strings.CANNOT_FIND_DOWNLOAD_LINKS.getText());
  }

  public static void printFileAlreadyDownloaded() {
    System.out.println(Strings.FILE_ALREADY_EISTS.getText());
  }

  public static void printUnknownArgumentError() {
    System.out.println(Strings.UNKNOWN_ARGUMENT_ERROR.getText());
  }
  public static void printPathNotEists() {
    System.out.println(Strings.BAD_PATH_ENTERED.getText());
  }
}
