import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FillTheBinarySearchTree { //

  private String fileName;
  private String regex;

  public static final Logger logger = Logger.getLogger("MyLog");

  FillTheBinarySearchTree(String fileName, String regex) {
    this.fileName = fileName;
    this.regex = regex;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public void setRegex(String regex) {
    this.regex = regex;
  }

  public BinarySearchTree returnBinaryTreeFromFile() {
    BinarySearchTree bst = new BinarySearchTree();

    try {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      String line;
      int position = 0; // to store position of duplicates

      while ((line = bufferedReader.readLine()) != null) {

        String[] words = line.split(regex);

        for (String word : words) {
          position++;
          bst.insert(word, position);
        }
      }
    } catch (IOException e) {
      logger.log(Level.WARNING, "Exception ::", e);
    }

    return bst;
  }
}
