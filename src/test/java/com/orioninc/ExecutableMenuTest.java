package com.orioninc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static com.orioninc.logger.LogUtil.*;

class ExecutableMenuTest {

  @BeforeAll
  static void setUp() {
    logInfo("JUnit is loaded.");
  }
  @Test
  @DisplayName("Test 1 File path")
  void checkFilePathExistReturnTrue() {
    String filePath = ".\\src\\main\\java\\lorem.txt";
    assertTrue(ExecutableMenu.filePathChecker(filePath));
  }

  @Test
  @DisplayName("Test 2 File path")
  void checkFilePathExistReturnFalse() {
    String filePath = ".\\src\\lorem2.txt";
    assertFalse(ExecutableMenu.filePathChecker(filePath));
  }

  @AfterAll
  static void finishAll() {
    logInfo("All tests finished");
  }
}
