package ui;

public enum Strings {

/*--------------------------------------------------------------------------------------------------
                                            Attributes
--------------------------------------------------------------------------------------------------*/

            // strings for printing
    UNKNOWN_ARGS_ERROR("Unknown arguments. Use ´help´ argument to see available commands."),

    INVALID_COUNT_OF_ARGUMENTS("Invalid arguments. Use ´help´ argument to see available commands."),

    CANNOT_CREATE_NEW_FILE("File to save list of downloaded PDF files cannot be created. Try to remove" +
            "write protection, please."),

    CANNOT_WRITE_TO_FILE("Cannot write to file."),

    ASK_FOR_DOWNLOAD("Do you want to download all of them? (Y/N)"),

    HELP("""
            List of available commands:
             help     - show this help.
             update   - check for new added files.
             download - download all newly addded files."""),

    MENU("""

            ==================================================================================
            This program allows you simply check for new added files on librinostri.catholica.cz.
            You can also download new PDF files recently added to this site for download.
            Enter number you want to do.
            ==================================================================================
            1. check for updates on website\s
            2. download new pdf(s) from websites\s
            3. help
            =================================================================================="""),

    UPDATER_INTRO("List of new books:");

            // field
    private final String text;

/*--------------------------------------------------------------------------------------------------
                                            Constructor
--------------------------------------------------------------------------------------------------*/

    Strings(String text) {
        this.text = text;
    }

/*--------------------------------------------------------------------------------------------------
                                            Getter
--------------------------------------------------------------------------------------------------*/

    public String getText() {
        return text;
    }
}
