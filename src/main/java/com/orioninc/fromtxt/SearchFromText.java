package com.orioninc.fromtxt;

import com.orioninc.abstractfactory.Search;
import com.orioninc.binarysearch.BinarySearchTree;
import com.orioninc.binarysearch.FillTheBinarySearchTree;

import static com.orioninc.logger.LogUtil.logWarning;

public class SearchFromText implements Search {
  private String fileName;

  public SearchFromText() {

  }

  public SearchFromText(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public void search() {
    if (filePathChecker(fileName)) {
      FillTheBinarySearchTree fillTheBinarySearchTree = new FillTheBinarySearchTree(fileName, REGEX);

      BinarySearchTree bst = fillTheBinarySearchTree.returnBinaryTreeFromFile();
      String specificWord = inputWord();
      boolean searchStatus = bst.search(specificWord);
      int source = 0; // Means from TextFile
      printResultAndPushToDatabase(searchStatus, bst, specificWord, source);
    }  else {
      logWarning("File is not found.");
    }

  }
}
