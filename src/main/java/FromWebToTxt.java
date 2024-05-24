import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FromWebToTxt {
    private String url;
    public static final Logger logger = Logger.getLogger("MyLog");

    FromWebToTxt(String url) {
        this.url = url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Elements getElementsFromWeb() {
        Elements elements;
        try {
            Document document = Jsoup.connect(this.url).get();
            elements = document.select("h1, h2, h3, h4, h5, h6, p, a, li, span, small, button");
        } catch (IOException var3) {
            logger.log(Level.WARNING, "File is not found.");
            elements = null;
        }

        return elements;
    }

    public String getExtractedFileName(Elements elements) {
        String extractedFileName1;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("extracted.txt"));

            try {
                Iterator var4 = elements.iterator();

                while(true) {
                    if (!var4.hasNext()) {
                        extractedFileName1 = "extracted.txt";
                        break;
                    }

                    Element element = (Element)var4.next();
                    writer.write(element.text());
                    writer.newLine();
                }
            } catch (Throwable var7) {
                try {
                    writer.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }

                throw var7;
            }

            writer.close();
        } catch (IOException var8) {
            logger.log(Level.WARNING, "File is not found.");
            extractedFileName1 = null;
        }

        return extractedFileName1;
    }
}
