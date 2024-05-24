import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExtractTextFromWeb {

  public static final Logger logger = Logger.getLogger("MyLog");

  public static void writeElementsToFile(Elements elements) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("extracted.txt"))) {
      for (Element element : elements) {
        writer.write(element.text());
        writer.newLine();
      }
    } catch (IOException e) {
      logger.log(Level.WARNING, "File is not found.");
    }
  }

  public static void main(String[] args) throws IOException {

    BinarySearchTree bst = new BinarySearchTree();

    final String regex = "[\\.,:)(!-_;~=*+>{}<%/#\"\\s\\d]+";
    final String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";

    try {
      Document document = Jsoup.connect(url).get();
      Elements elements = document.select("h1, h2, h3, h4, h5, h6, p, a, li, span, small, button");
      writeElementsToFile(elements);
    } catch (IOException e) {
      logger.log(Level.WARNING, "File is not found.");
    }

    WordSearchInText.insertTextFromFile("extracted.txt", bst, regex);
    bst.displayTree();
  }
}
