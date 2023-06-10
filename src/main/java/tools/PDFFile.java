/*
package tools;

import main.Book;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import tools.config.Config;

public class PDFFile {
  */
/*=================================================================================================
                                                      Attributes
  =================================================================================================*//*

  */
/*=================================================================================================
                                                      Constructors
  =================================================================================================*//*

  public PDFFile() {
    Config config = new Config();
  }
*/
/*=================================================================================================
                                                    Methods
=================================================================================================*//*


  */
/**
   * Create the <code>File</code> apropriate to with name to downloadLinks provided by <code>Book</code> class.
   *
   * @param books the book from which you want to crate files for every download link
   * @return Map of download links related to files.
   *//*

  public Map<String, File> createFiles(ArrayList<Book> books) {
    Map<String, File> fileMap = new HashMap<>();
    for (Book book : books) {
      for (String dLink : book.getDOWNLOAD_LINKS()) {
        int indexOfLastDivider = dLink.lastIndexOf("/");
        String fileName = dLink.substring(indexOfLastDivider);
        File file = new File(DOWNLOAD_FOLDER + File.separator + book.getTITLE() +
            File.separator + fileName);
        try {
          if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
          }
          file.createNewFile();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        fileMap.put(dLink, file);
      }
    }
    return fileMap;
  }

  public Map<String, File> deleteEntriesOfExistingFiles(Map<String, File> fileMap) {
    Iterator<Map.Entry<String, File>> iterator = fileMap.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<String, File> item = iterator.next();
      if (item.getValue().length() > 0) {
        iterator.remove();
      }
    }
    return fileMap;
  }
}*/
