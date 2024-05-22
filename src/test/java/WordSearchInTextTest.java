import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.logging.Level;

class WordSearchInTextTest {

    @BeforeAll
    static void setUp () {
        LoggerInit loggerInit = new LoggerInit(".\\src\\main\\java\\log.txt");
        loggerInit.logger.log(Level.INFO, "JUnit is loaded.");
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
        LoggerInit loggerInit = new LoggerInit(".\\src\\main\\java\\log.txt");
        loggerInit.logger.log(Level.INFO, "All tests finished");
    }


}