package abstractFactory;

import com.orioninc.binarysearch.BinarySearchTree;
import com.orioninc.db.DbRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static com.orioninc.logger.LogUtil.*;

public interface Search {
  String REGEX = "[\\]\\.,:\\)\\(!\\-_\\?;~=\\*+„“–>\\{\\}<%©\\/#\"\\s\\d&&[^s]]+";

  void search();

  default String inputWord() {
    System.out.println("Input the word you want to search in a text");
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

  default boolean filePathChecker(String filePath) {
    return new File(filePath).exists();
  }

  default PreparedStatement getPreparedStatement(Connection connection, DbRecord dbRecord) {
    PreparedStatement ps = null;
    String sqlQuery =
        "INSERT INTO RECORDS (record_timestamp, word, position, source) VALUES (?, ?, ?, ?)";
    try {
      ps = connection.prepareStatement(sqlQuery);
      ps.setObject(1, dbRecord.getDateTime());
      ps.setString(2, dbRecord.getWord());
      ps.setInt(3, dbRecord.getPosition());
      ps.setInt(4, dbRecord.getSource());

    } catch (SQLException e) {
      logWarning("SQL Exception" + e.getMessage());
    }
    return ps;
  }

  default void printResultAndPushToDatabase(boolean searchStatus, BinarySearchTree bst, String specificWord, int source) {
    try {
      Connection databaseConnection = new DatabaseConnection().getDatabaseConnection();

      if (searchStatus) {
        logInfo(
            "Word was found "
                + bst.displayCount(specificWord)
                + " times in "
                + bst.displayPositions(specificWord)
                + " positions in text.");

        DbRecord dbRecord =
            new DbRecord(
                LocalDateTime.now(), specificWord, bst.displayPositions(specificWord).get(0), source);
        try {
          getPreparedStatement(databaseConnection, dbRecord).executeUpdate();
        } catch (SQLException e) {
          logWarning("SQL Exception" + e.getMessage());
        }

      } else {
        logInfo("Word wasn't found.");

        DbRecord dbRecord = new DbRecord(LocalDateTime.now(), specificWord, 0, source);
        try {
          getPreparedStatement(databaseConnection, dbRecord).executeUpdate();
        } catch (SQLException e) {
          logWarning("SQL Exception" + e.getMessage());
        }
      }
    } catch (SQLException e) {
      logError("Database Connection Failed", e);
    }
  }
}
