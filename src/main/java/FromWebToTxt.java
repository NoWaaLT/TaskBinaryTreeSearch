import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FromWebToTxt { //

  private String url;

  FromWebToTxt(String url) {
    this.url = url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public static final Logger logger = Logger.getLogger("MyLog");

  public Elements getElementsFromWeb() {
    Elements elements;

    try {
      Document document = Jsoup.connect(url).get();
      elements = document.select("h1, h2, h3, h4, h5, h6, p, a, li, span, small, button");

    } catch (IOException e) {
      logger.log(Level.WARNING, "File is not found.");
      elements = null;
    }

    return elements;
  }

  public String getExtractedFileName(Elements elements) {

    String extractedFileName1;

    try (BufferedWriter writer = new BufferedWriter(new FileWriter("extracted.txt"))) {
      for (Element element : elements) {
        writer.write(element.text());
        writer.newLine();
      }

      extractedFileName1 = "extracted.txt";

    } catch (IOException e) {
      logger.log(Level.WARNING, "File is not found.");
      extractedFileName1 = null;
    }

    return extractedFileName1;
  }
}
