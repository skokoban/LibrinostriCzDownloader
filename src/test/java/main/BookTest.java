package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BookTest {
  @Test
  void normalizeBookName() {
    String badString = "íášéí_REĎÚ/*-+32b";
    Book book = new Book("íášéi_ŘÉĎÚ32b", "test", "test");

    String result = book.normalizeString(badString);

    assertEquals("iasei_REDU32b", result);
  }
}