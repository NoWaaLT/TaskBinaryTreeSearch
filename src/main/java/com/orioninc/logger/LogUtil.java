package com.orioninc.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogUtil {

  public static final Logger LOGGER = Logger.getLogger("MyLog");

  static {
    try {
      FileHandler fileHandler = new FileHandler(".\\TaskBinaryTreeSearch\\log\\log.txt");
      LOGGER.addHandler(fileHandler);
      SimpleFormatter formatter = new SimpleFormatter();
      fileHandler.setFormatter(formatter);
      logInfo("Logger Initialized");
    } catch (IOException e) {
      logWarning("Warning: " + e);
    }
  }

  public static void logInfo(String message) {
    String className = new Exception().getStackTrace()[1].getClassName();
    LOGGER.log(Level.INFO, className + " - " + message);
  }

  public static void logWarning(String message) {
    String className = new Exception().getStackTrace()[1].getClassName();
    LOGGER.log(Level.WARNING, className + " - " + message);
  }

  public static void logError(String message, Exception e) {
    String className = new Exception().getStackTrace()[1].getClassName();
    LOGGER.log(Level.SEVERE, className + " - " + message, e);
  }
}
