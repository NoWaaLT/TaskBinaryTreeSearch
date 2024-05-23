import java.io.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WordSearchInText {

  static boolean filePathChecker(String pathName) {
    return new File(pathName).exists();
  }

  public static final Logger logger = Logger.getLogger("MyLog");

  public static void main(String[] args) {

    final String fileName = ".\\src\\main\\java\\lorem.txt";
    final String logFilePath = ".\\src\\main\\java\\log.txt";

    new LoggerInit(logFilePath);

    BinarySearchTree bsc = new BinarySearchTree();

    String specificWord;
    BufferedReader bufferedReader;

    try {

      System.out.println("Input the word you want to search in a text");
      bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      specificWord = bufferedReader.readLine();
      bufferedReader.close();

      logger.info("Search keyword: " + specificWord);

      if (filePathChecker(fileName)) {

        FileReader fileReader = new FileReader(fileName);
        bufferedReader = new BufferedReader(fileReader);

        String line;
        int position = 0; // to store position of duplicates

        while ((line = bufferedReader.readLine()) != null) { // reads a line of text till null

          String[] words = line.split("[.,:/\\n\\s\\t]+"); // split by .,:/ and spaces, line breaks

          for (String word : words) {
            position++;
            bsc.insert(word, position);
          }
        }

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

    } catch (IOException e) {
      logger.log(Level.WARNING, "Exception ::", e);
    }
  }
}
