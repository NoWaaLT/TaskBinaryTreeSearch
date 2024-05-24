import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.logging.Level;
import java.util.logging.Logger;

class ExecutableMenuTest {

  public static final Logger logger = Logger.getLogger("MyLog");

  @BeforeAll
  static void setUp() {
    new LoggerInit(".\\log\\log.txt");
    logger.log(Level.INFO, "JUnit is loaded.");
  }
  @Test
  @DisplayName("Test 1 File path")
  void checkFilePathExistReturnTrue() {
    String filePath = ".\\src\\main\\java\\lorem.txt";
    assertTrue(ExecutableMenu.filePathChecker(filePath));
  }

  @Test
  @DisplayName("Test 2 File path")
  void checkFilePathExistReturnFalse() {
    String filePath = ".\\src\\lorem2.txt";
    assertFalse(ExecutableMenu.filePathChecker(filePath));
  }

  @AfterAll
  static void finishAll() {
    logger.log(Level.INFO, "All tests finished");
  }
}
