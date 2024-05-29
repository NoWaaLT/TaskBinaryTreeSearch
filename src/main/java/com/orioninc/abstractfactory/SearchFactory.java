package com.orioninc.abstractfactory;

import com.orioninc.fromtxt.SearchFromText;
import com.orioninc.fromweb.SearchFromWeb;

public class SearchFactory extends AbstractFactory {
  final String FROM_TEXT = "FromText";
  final String FROM_WEB = "FromWeb";

  private String getIdentity(String executableObject) {
    LinkIdentify linkIdentify = new LinkIdentify();

    if (linkIdentify.isLink(executableObject)) {
      return FROM_WEB;
    } else {
      return FROM_TEXT;
    }
  }

  @Override
  public Search getSearch(String executableObject) {
    if (getIdentity(executableObject).equalsIgnoreCase(FROM_TEXT)) {
      return new SearchFromText(executableObject);
    } else if (getIdentity(executableObject).equalsIgnoreCase(FROM_WEB)) {
      return new SearchFromWeb(executableObject);
    }
    return null;
  }
}
