package com.orioninc.db;

import java.time.LocalDateTime;

public class DbRecord {

  private final LocalDateTime dateTime;
  private final String word;
  private final int position;
  private final int source; // position 0 - from text,position 1 - from web

  public DbRecord (LocalDateTime dateTime, String word, int position, int source) {
    this.dateTime = dateTime;
    this.word = word;
    this.position = position;
    this.source = source;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public String getWord() {
    return word;
  }

  public int getPosition() {
    return position;
  }

  public int getSource() {
    return source;
  }
}
