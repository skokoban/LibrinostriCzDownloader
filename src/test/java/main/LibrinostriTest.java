package main;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LibrinostriTest {

  @Test
  void passWhenChecksumsEqualsReturnsFalse() {
    Librinostri librinostri = new Librinostri();

    boolean result = librinostri.isNewFileAdded(16465165L, 16465165L);

    assertFalse(result);
  }

  @Test
  void passWhenChecksumsNotEqualsReturnsTrue() {
    Librinostri librinostri = new Librinostri();

    boolean result = librinostri.isNewFileAdded(16465165L, 1222222L);

    assertTrue(result);
  }
}