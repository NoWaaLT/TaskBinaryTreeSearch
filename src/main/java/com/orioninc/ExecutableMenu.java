package com.orioninc;

import com.orioninc.abstractfactory.AbstractFactory;
import com.orioninc.abstractfactory.FactoryProducer;
import com.orioninc.abstractfactory.Search;
import com.orioninc.db.DatabaseConnection;
import com.orioninc.db.DatabaseMigration;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import static com.orioninc.logger.LogUtil.*;

import java.sql.SQLException;
import java.util.Scanner;

public class ExecutableMenu {

  enum Choice {
    EXTRACT,
    EXPORT,
    UPDATE
  }

  public static void main(String[] args) {

    AbstractFactory searchFactory = FactoryProducer.getFactory();
    System.out.println(
        "EXTRACT - Extract from File/Web page \nEXPORT - Export a database\nUPDATE - Liquibase update");

    try {

      Scanner sc = new Scanner(System.in);
      Choice choice = Choice.valueOf(sc.nextLine().toUpperCase());

      switch (choice) {
        case EXTRACT -> {
          System.out.println("Input a file name or URL: ");
          String input = sc.nextLine();
          Search searchFromText = searchFactory.getSearch(input);
          searchFromText.search();
        }
        case EXPORT -> {
          DatabaseMigration databaseMigration = new DatabaseMigration();
          databaseMigration.backupDatabaseIntoSqlScript(
              "C:\\Users\\vjelis\\IdeaProjects\\TaskBinaryTreeSearch\\db_script.sql");
        }
        case UPDATE -> {
          java.sql.Connection connection = new DatabaseConnection().getDatabaseConnection();

          Database database =
              DatabaseFactory.getInstance()
                  .findCorrectDatabaseImplementation(new JdbcConnection(connection));
          Liquibase liquibase =
              new Liquibase(
                  "changelog\\changelog.sql", new ClassLoaderResourceAccessor(), database);

          liquibase.update(new Contexts(), new LabelExpression());
        }

        default -> System.exit(0);
      }
      sc.close();
    } catch (DatabaseException | IllegalArgumentException e) {
      logError("Exception:", e);
    } catch (SQLException | LiquibaseException e) {
      logError(e.getMessage(), e);
      throw new RuntimeException(e);
    }
  }
}
