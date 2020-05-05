package com.github.moaxcp.graphwm;

import gnu.x11.*;
import gnu.x11.extension.XTest;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static gnu.x11.DisplayName.parse;

@Singleton
public class WindowManager {
  private static Logger log = LoggerFactory.getLogger(WindowManager.class);
  private Display display;

  @PreDestroy
  public void destroy() {
    display.close();
  }

  void setupDisplay(String displayArg) {
    DisplayName name;
    if (displayArg == null) {
      name = parse();
    } else {
      name = parse(displayArg);
    }
    log.info("display name is {}", name);

    display = name.connect();
    Window root = display.getDefaultRoot();
    new XTest(display);
  }
}
