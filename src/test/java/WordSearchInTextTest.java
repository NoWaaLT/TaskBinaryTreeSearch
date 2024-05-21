import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;

class WordSearchInTextTest {

    BinarySearchTree bst = new BinarySearchTree();
    WordSearchInText mainProgramObject = new WordSearchInText();

    @BeforeAll
    static void setUp () {
        WordSearchInText.logger.log(Level.INFO, "JUnit is loaded.");
    }

    @Test
    void checkDoesBSTSearchWorksReturnsTrue() {
        bst.insert("good", 1);
        assertTrue(bst.search("good"));
    }

    @Test
    void checkDoesBSTSearchWorksReturnsFalse() {
        bst.insert("good", 1);
        assertFalse(bst.search("goods"));
    }

    @Test
    void checkDoesBSTSearchWorksWhenNotTrimReturnsFalse() {
        bst.insert(" good ", 1);
        assertFalse(bst.search("good"));
    }

    @Test
    void checkDoesFileExistReturnTrue() {
        String filePath = "C:\\Users\\vjelis\\IdeaProjects\\TaskBinaryTreeSearch\\src\\main\\java\\lorem.txt";
        assertTrue(WordSearchInText.filePathChecker(filePath));
    }

    @Test
    void checkDoesFileExistReturnFalse() {
        String filePath = "C:\\Users\\vjelis\\IdeaProjects\\TaskBinaryTreeSearch\\src\\lorem2.txt";
        assertFalse(WordSearchInText.filePathChecker(filePath));
    }

    @AfterAll
    static void finishAll() {
        WordSearchInText.logger.log(Level.INFO, "All tests finished");
    }


}