import java.io.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WordSearchInText {

  static boolean filePathChecker(String filePath) {
    return new File(filePath).exists();
  }

  static String inputWord() {
    try {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      String specificWord = bufferedReader.readLine();
      bufferedReader.close();

      return specificWord;

    } catch (IOException e) {
      logger.log(Level.WARNING, "Exception ::", e);

      return null;
    }
  }

  static void insertTextFromFile(String filePath, BinarySearchTree bsc, String regex) {
    try {
      FileReader fileReader = new FileReader(filePath);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      String line;
      int position = 0; // to store position of duplicates

      while ((line = bufferedReader.readLine()) != null) { // reads a line of text till null

        String[] words = line.split(regex); // split by .,:/ and spaces, line breaks

        for (String word : words) {
          position++;
          bsc.insert(word, position);
        }
      }
    } catch (IOException e) {
      logger.log(Level.WARNING, "Exception ::", e);
    }
  }

  public static final Logger logger = Logger.getLogger("MyLog");

  public static void main(String[] args) {

    final String fileName = ".\\src\\main\\java\\lorem.txt";
    final String logFilePath = ".\\src\\main\\java\\log.txt";
    final String regex = "[.,:/\\n\\s\\t]+";

    new LoggerInit(logFilePath);

    BinarySearchTree bsc = new BinarySearchTree();

    String specificWord;
    System.out.println("Input the word you want to search in a text");
    specificWord = inputWord();

    logger.info("Search keyword: " + specificWord);

    if (filePathChecker(fileName)) {

      insertTextFromFile(fileName, bsc, regex);

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
}