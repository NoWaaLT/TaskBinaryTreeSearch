package com.orioninc.fromweb;

import com.orioninc.abstractfactory.Search;
import com.orioninc.binarysearch.BinarySearchTree;
import com.orioninc.binarysearch.FillTheBinarySearchTree;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

import static com.orioninc.logger.LogUtil.logWarning;

public class SearchFromWeb implements Search {
  private String url;

  public SearchFromWeb(String url) {
    this.url = url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public void search() {

    String extractedFileName = getExtractedFileName(getElementsFromWeb());

    FillTheBinarySearchTree fillTheBinarySearchTree =
            new FillTheBinarySearchTree(extractedFileName, REGEX);

    BinarySearchTree bst = fillTheBinarySearchTree.returnBinaryTreeFromFile();
    String specificWord = inputWord();
    boolean searchStatus = bst.search(specificWord);
    int source = 1; // Means from WebPage
    printResultAndPushToDatabase(searchStatus, bst, specificWord, source);
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
