package exceptions;

public class LinkNotFoundException extends RuntimeException{
  public LinkNotFoundException(String message) {
    super(message);
  }
}
