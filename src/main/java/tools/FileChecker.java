package tools;

import java.io.File;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.Map;

public class FileChecker {
    public void deleteEntriesOfExistingFiles(Map<String, File> fileMap) {
        for (Map.Entry<String, File> fileEntry : fileMap.entrySet()) {
            if (fileEntry.getValue().exists()) {
                fileMap.remove(fileEntry.getKey(), fileEntry.getValue());
            }
        }
    }
}
