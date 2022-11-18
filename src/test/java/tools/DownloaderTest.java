package tools;

import main.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

public class DownloaderTest {

/*======================================================================================================================
                                                Prepare methods
======================================================================================================================*/

/*======================================================================================================================
                                                Test methods
======================================================================================================================*/
/*======================================================================================================================
                                                After methods
======================================================================================================================*/

    private void deleteTestFile(String downloaded_pdfs_file_path) {
        new File(downloaded_pdfs_file_path).delete();
    }
}
