package tools.file;

import java.nio.file.Path;
import tools.Downloader;
import tools.config.ConfigProvider;

public class PDFFile extends File{
/*=================================================================================================
                                                Attributes
=================================================================================================*/
  private String path;
  private String name;
  private String link;
  private String downloadFolder;
/*=================================================================================================
                                                Constructor
=================================================================================================*/
  public PDFFile(String link, String downlodFolder) {
    this.link = link;
    this.downloadFolder = downlodFolder;
  }
/*=================================================================================================
                                                Methods
=================================================================================================*/
  public void retrieveName() {
    int lastSlash = link.lastIndexOf("/");
    name = link.substring(lastSlash + 1);
  }
  public void makePath() {
    path = downloadFolder + java.io.File.pathSeparator + name;
  }

  public boolean download() {
    long size = Downloader.download(link, path);
    return size > 0;
  }
}
