package exceptions;

import java.net.MalformedURLException;

public class LinkNotFoundException extends RuntimeException{
  public LinkNotFoundException(MalformedURLException e) {
    super("Download link not found. Link has bad format. URL object cannot be created.\n" + e);
  }
}
