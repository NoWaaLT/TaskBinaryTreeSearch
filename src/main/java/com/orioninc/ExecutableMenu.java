package com.orioninc;

import com.orioninc.abstractfactory.AbstractFactory;
import com.orioninc.abstractfactory.FactoryProducer;
import com.orioninc.abstractfactory.Search;
import com.orioninc.db.DatabaseMigration;

import java.util.Scanner;

public class ExecutableMenu {
  public static void main(String[] args) {

    AbstractFactory searchFactory = FactoryProducer.getFactory();

    Scanner sc = new Scanner(System.in);
    System.out.println("1 - Extract from File / 2 - Extract from Web / 3 - Export a database");
    int menu = sc.nextInt();

    if (menu == 1) {
      Search searchFromText = searchFactory.getSearch(".\\src\\main\\resources\\lorem.txt");
      searchFromText.search();
    } else if (menu == 2) {
      Search searchFromWeb = searchFactory.getSearch("https://simplepage.com");
      searchFromWeb.search();
    } else if (menu == 3) {
      DatabaseMigration databaseMigration = new DatabaseMigration();
      databaseMigration.backupDatabaseIntoSqlScript(
          "C:\\Users\\vjelis\\IdeaProjects\\TaskBinaryTreeSearch\\db_script.sql");
    } else {
      System.exit(0);
    }
    sc.close();
  }
}
