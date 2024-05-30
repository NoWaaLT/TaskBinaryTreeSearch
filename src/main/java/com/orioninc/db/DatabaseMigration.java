package com.orioninc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.orioninc.logger.LogUtil.*;

public class DatabaseMigration extends DatabaseConnection {

  public DatabaseMigration() {}

  public void backupDatabaseIntoSqlScript(String sqlFilePath) {
    try {
      Connection connection = getDatabaseConnection();
      PreparedStatement preparedStatement =
          connection.prepareStatement(String.format("SCRIPT TO '%s'", sqlFilePath));
      preparedStatement.execute();
      logInfo("SQL script file created.");
    } catch (SQLException e) {
      logWarning("Exception: " + e);
    }
  }
}
