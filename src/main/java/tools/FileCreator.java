package tools;

import main.Book;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FileCreator {
    /**
     *
     * @param book
     * @param DLDest
     * @return
     */
    public Map<String, File> createFiles(Book book, Path DLDest) {
        Map<String, File> fileMap = new HashMap<>();
        for (String dLink: book.getDOWNLOAD_LINKS()) {
            int lastDivider = dLink.lastIndexOf("/");
            String fileName = dLink.substring(lastDivider);
            File file       = new File(DLDest + File.separator + book.getTITLE() + File.separator + fileName);
            fileMap.put(dLink, file);
        }
        return fileMap;
    }
}
