package ui;

public enum Strings {

/*======================================================================================================================
                                                    Attributes
======================================================================================================================*/

            // strings for printing
    UNKNOWN_ARGS_ERROR("Unknown arguments. Use ´help´ argument to see available commands."),

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

    OK(" OK"),

    DOWNLOADING("downloading: "),

    NEW_BOOKS_FOLDER("Please type path for downloaded books: "),

    ERROR_DOWNLOADING("\nNew PDF files cannot be downloaded. Please send this error report to mdorusak@gmail.com."),
    ERROR_INVALID_MENU_OPTION("Invalid option selected."),

    NO_NEW_FILES("There is no new files recently added."),

    MENU("""

            ===============================================================================================
            This program allows you automatically download all new added files on librinostri.catholica.cz.
            
            Enter number you want to do.
            \s
            1. download new pdf files from website(s)\s
            2. change folder for downloaded books\s
            3. show folder for downloaded books\s
            4. help\s
            5. about\s
            6. exit
            ==============================================================================================="""),

    UPDATER_INTRO("List of new books:"),

    CANNOT_READ_FROM_FILE("Property impossible to set in case of config file not found");

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
