package com.orioninc.abstractfactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkIdentify {
  private static final String URL_REGEX =
      "(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})";

  public boolean isLink(String str) {
    Pattern pattern = Pattern.compile(URL_REGEX);
    Matcher matcher = pattern.matcher(str);
    return matcher.matches();
  }
}
