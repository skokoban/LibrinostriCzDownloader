package ui;

public enum Strings {

/*======================================================================================================================
                                                    Attributes
======================================================================================================================*/

  // strings for printing
  UNKNOWN_MENU_OPTION_ERROR("Unknown menu option."),

  CANNOT_CREATE_CONFIG_FILE("Missing config file. New config file cannot be created. Try to remove "
      + "writing protection. Exiting..."),

  INVALID_COUNT_OF_ARGUMENTS("Invalid arguments. Use ´help´ argument to see available commands."),

  CANNOT_CREATE_NEW_FILE("File to save list of downloaded PDF files cannot be created. Try to remove" +
      "write protection, please."),

  CANNOT_WRITE_TO_FILE("Cannot write to file."),

  ASK_FOR_DOWNLOAD("Do you want to download all of them? (Y/N)"),

  HELP("""
            List of available commands:
             help     - show this help
             download - download all newly addded files
             path     - change path to download folder
             about    - information about this aplication.
             
             Usage: command"""),

  ABOUT("""
            Librinosti.cz PDF File downloader v.0.22.12.18
            created by Marcel Dorušák."""),

  OK(". OK"),

  DOWNLOADING("trying to download "),

  NEW_BOOKS_FOLDER("Please type path for downloaded books: "),

  ERROR_DOWNLOADING("\nNew PDF files cannot be downloaded. Please send this error report to mdorusak@gmail.com."),
  ERROR_INVALID_MENU_OPTION("Invalid option selected."),

  NO_NEW_FILES("There is no new files recently added."),

  MENU("""

            ===============================================================================================
            This program allows you automatically download all new added files on librinostri.catholica.cz.
            
            Enter number you want to do.
            \s
            1. check for updates\s
            2. download new pdf files from website(s)\s
            3. change folder for downloaded books\s
            4. show folder for downloaded books\s
            5. help\s
            6. about\s
            7. exit
            ==============================================================================================="""),

  UPDATER_NEW_FILES("There is new files to download."),
  CANNOT_READ_FROM_FILE("Property impossible to set because config file not found"),
  XML_PARSE_ERROR_TEXT("Error occured when parsing XML file with new books information."
      + "Unexpected XML file structure."),
  CANNOT_FIND_DOWNLOAD_LINKS("Cannot find download links for book "),
  FILE_ALREADY_EISTS(". already exists. Skipping..."), UNKNOWN_ARGUMENT_ERROR("Unknown"
      + "arguemnt.");

  // field
  private final String text;

/*======================================================================================================================
                                                    Constructors
======================================================================================================================*/

  Strings(String text) {
    this.text = text;
  }

/*======================================================================================================================
                                                    Getters
======================================================================================================================*/

  public String getText() {
    return text;
  }
}
