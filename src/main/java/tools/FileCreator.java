package tools;

import main.Book;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileCreator {
    /**
     * Create the <code>File</code> apropriate to with name to downloadLinks provided by <code>Book</code> class.
     * @param books the book from which you want to crate files for every download link
     * @param DLDest path to directory where files should be strored
     * @return Map of download links linked to files.
     */
    public Map<String, File> createFiles(ArrayList<Book> books, Path DLDest) {
        Map<String, File> fileMap = new HashMap<>();
        for (Book book: books) {
            for (String dLink: book.getDOWNLOAD_LINKS()) {
                if (!dLink.contains("/")) {
                    System.out.println("Error: Not valid link:" + dLink);
                    continue;
                }
                int lastDivider = dLink.lastIndexOf("/");
                String fileName = dLink.substring(lastDivider);
                File file       = new File(DLDest + File.separator + book.getTITLE() + File.separator + fileName);
                fileMap.put(dLink, file);
            }
        }
        return fileMap;
    }
}
