import java.io.*;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExecutableMenu {

  static boolean filePathChecker(String filePath) {
    return new File(filePath).exists();
  }

  static String inputWord() {
    String word;
    try {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      word = bufferedReader.readLine();

    } catch (IOException e) {
      logger.log(Level.WARNING, "Exception ::", e);

      word = null;
    }
    return word;
  }

  static void extractFromFileMenu(String fileName, String regex) {
    System.out.println("Input the word you want to search in a text");
    String specificWord = inputWord();

    FillTheBinarySearchTree fillTheBinarySearchTree = new FillTheBinarySearchTree(fileName, regex);

    logger.info("Search keyword: " + specificWord);

    if (filePathChecker(fileName)) {

      BinarySearchTree bsc = fillTheBinarySearchTree.returnBinaryTreeFromFile();

      boolean searchStatus = bsc.search(specificWord);

      if (searchStatus) {
        logger.info(
            "Word was found "
                + bsc.displayCount(specificWord)
                + " times in "
                + bsc.displayPositions(specificWord)
                + " positions in text.");
      } else {
        logger.info("Word wasn't found.");
      }

    } else {
      logger.log(Level.WARNING, "File is not found.");
    }
  }

  static void extractFromWebMenu(String url, String pattern) {
    FromWebToTxt fromWebToTxt = new FromWebToTxt(url);
    String extractedFileName = fromWebToTxt.getExtractedFileName(fromWebToTxt.getElementsFromWeb());

    FillTheBinarySearchTree fillTheBinarySearchTree =
        new FillTheBinarySearchTree(extractedFileName, pattern);
    BinarySearchTree bst;
    bst = fillTheBinarySearchTree.returnBinaryTreeFromFile();
    bst.displayTree();
  }

  public static final Logger logger = Logger.getLogger("MyLog");

  public static void main(String[] args) {

    final String fileName = ".\\src\\main\\java\\lorem.txt";
    final String logFilePath = ".\\log\\log.txt";
    final String regex = "[\\]\\.,:\\)\\(!\\-_\\?;~=\\*+>\\{\\}<%\\/#\"\\s\\d&&[^s]]+";
    final String url = "https://delfi.lt";

    int menu;
    new LoggerInit(logFilePath);

    System.out.println("1 - Extract from File / 2 - Extract from Web");

    try {
      menu = Integer.parseInt(Objects.requireNonNull(inputWord()));
      if (menu == 1) {
        extractFromFileMenu(fileName, regex);
      } else if (menu == 2) {
        extractFromWebMenu(url, regex);
      } else {
        System.exit(0);
      }
    } catch (NumberFormatException e) {
      logger.log(Level.WARNING, "Invalid input.");
    }
  }
}
