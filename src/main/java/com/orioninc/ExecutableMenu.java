package com.orioninc;

import com.orioninc.binarysearch.BinarySearchTree;
import com.orioninc.binarysearch.FillTheBinarySearchTree;
import com.orioninc.db.DbRecord;
import com.orioninc.fromweb.FromWebToTxt;

import java.io.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.orioninc.logger.LogUtil.*;

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
      logWarning("Exception ::" + e);
      word = null;
    }
    return word;
  }

  static void extractFromFileMenu(String fileName, String regex, Connection connection) {
    System.out.println("Input the word you want to search in a text");
    String specificWord = inputWord();

    FillTheBinarySearchTree fillTheBinarySearchTree = new FillTheBinarySearchTree(fileName, regex);

    LOGGER.info("Search keyword: " + specificWord);

    if (filePathChecker(fileName)) {

      BinarySearchTree bsc = fillTheBinarySearchTree.returnBinaryTreeFromFile();

      boolean searchStatus = bsc.search(specificWord);

      if (searchStatus) {
        logInfo(
            "Word was found "
                + bsc.displayCount(specificWord)
                + " times in "
                + bsc.displayPositions(specificWord)
                + " positions in text.");

        DbRecord dbRecord =
            new DbRecord(
                LocalDateTime.now(), specificWord, bsc.displayPositions(specificWord).get(0), 0);

        try {
          PreparedStatement ps =
              connection.prepareStatement(
                  "INSERT INTO Records () VALUES (?, ?, ?, ?)");

          ps.setObject(1, dbRecord.getDateTime());
          ps.setString(2, dbRecord.getWord());
          ps.setInt(3, dbRecord.getPosition());
          ps.setInt(4, 0);

        } catch (SQLException e) {
          logWarning("SQL Exception");
        }

      } else {
        logInfo("Word wasn't found.");
      }

    } else {
      logWarning("File is not found.");
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

  public static void main(String[] args) { //

    final String fileName = ".\\src\\main\\resources\\lorem.txt";
    final String regex = "[\\]\\.,:\\)\\(!\\-_\\?;~=\\*+>\\{\\}<%\\/#\"\\s\\d&&[^s]]+";
    final String url = "https://delfi.lt";

    final String usernameDb = "sa";
    final String passDb = "";

    int menu;

    try (Connection connection =
        DriverManager.getConnection(
            "jdbc:h2:~/IdeaProjects/TaskBinaryTreeSearch/src/main/java/com/orioninc/db",
            usernameDb,
            passDb)) {
      if (connection.isValid(0)) {
        logInfo("Connected to Database");
      }

      try {
        menu = Integer.parseInt(Objects.requireNonNull(inputWord()));
        if (menu == 1) {
          extractFromFileMenu(fileName, regex, connection);
        } else if (menu == 2) {
          extractFromWebMenu(url, regex);
        } else {
          System.exit(0);
        }
      } catch (NumberFormatException e) {
        logError("Invalid input.", e);
      }

    } catch (SQLException e) {
      logError("Database Connection Failed", e);
    }

    System.out.println("1 - Extract from File / 2 - Extract from Web");
  }
}
