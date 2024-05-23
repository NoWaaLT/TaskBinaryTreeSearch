import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.logging.Level;
import java.util.logging.Logger;

class WordSearchInTextTest {

  public static final Logger logger = Logger.getLogger("MyLog");

  @BeforeAll
  static void setUp() {
    new LoggerInit(".\\src\\main\\java\\log.txt");
    logger.log(Level.INFO, "JUnit is loaded.");
  }

  @Test
  void checkDoesFileExistReturnTrue() {
    String filePath = ".\\src\\main\\java\\lorem.txt";
    assertTrue(WordSearchInText.filePathChecker(filePath));
  }

  @Test
  void checkDoesFileExistReturnFalse() {
    String filePath = "C:\\Users\\vjelis\\IdeaProjects\\TaskBinaryTreeSearch\\src\\lorem2.txt";
    assertFalse(WordSearchInText.filePathChecker(filePath));
  }

  @AfterAll
  static void finishAll() {
    logger.log(Level.INFO, "All tests finished");
  }
}
