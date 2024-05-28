package com.orioninc.fromweb;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static com.orioninc.logger.LogUtil.logWarning;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FromWeb {
  private String url;

  public FromWeb(String url) {
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
    } catch (IOException e) {
      logWarning("File is not found.");
      elements = null;
    }

    return elements;
  }

  public String getExtractedFileName(Elements elements) {
    String fileName = "";

    try (BufferedWriter writer = new BufferedWriter(new FileWriter("extracted.txt"))) {
      for (Element element : elements) {
        writer.write(element.text());
        writer.newLine();
        fileName = "extracted.txt";
      }
    } catch (IOException e) {
      logWarning("Something went wrong.");
      fileName = null;
    }
    return fileName;
  }
}
