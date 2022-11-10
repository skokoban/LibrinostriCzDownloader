package tools;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

public class FileChecker {
    public void deleteExistingFiles(Map<String, File> fileMap) {
        for (Map.Entry<String, File> fileEntry : fileMap.entrySet()) {
            fileEntry.getKey();
            fileEntry.getValue();
        }
    }
}
