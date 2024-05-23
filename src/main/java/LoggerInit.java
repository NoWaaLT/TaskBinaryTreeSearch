import java.io.IOException;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerInit {

  public final Logger logger = Logger.getLogger("MyLog");

  public LoggerInit(String logFilePath) {

    FileHandler fileHandler;

    try {
      fileHandler = new FileHandler(logFilePath);
      logger.addHandler(fileHandler);
      SimpleFormatter formatter = new SimpleFormatter();
      fileHandler.setFormatter(formatter);
      logger.info("Logger Initialized");

    } catch (IOException e) {
      logger.log(Level.WARNING, "Exception ::", e);
    }
  }
}
