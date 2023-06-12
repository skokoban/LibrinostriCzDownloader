package tools.config;

import java.io.IOException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ConfigTest {

  @Test
  public void passWhenInstanceIsNotNull() {
    Config instance1;
    Config instance2;

    instance1 = Config.getInstance();
    instance2 = Config.getInstance();

    assertNotNull(instance1);
    assertNotNull(instance2);
  }

  @Test
  public void passWhenInstanceIsSame() {
    Config instance1;
    Config instance2;

    instance1 = Config.getInstance();
    instance2 = Config.getInstance();

    assertSame(instance1, instance2);
  }

  @Test
  public void passWhenFileArgumentIsNull() throws IOException {
  }
}
