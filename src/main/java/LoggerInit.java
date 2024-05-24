import java.io.IOException;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerInit {

  public final Logger logger = Logger.getLogger(LoggerInit.class.getName());

  public LoggerInit(String logFilePath) {

    FileHandler fileHandler;

    try {
      fileHandler = new FileHandler(logFilePath);
      logger.addHandler(fileHandler);
      SimpleFormatter formatter = new SimpleFormatter();
      fileHandler.setFormatter(formatter);
      logger.info("Logger Initialized");


    } catch (IOException e) {
      logger.warning("Warning: " + e);
    }
  }
}
