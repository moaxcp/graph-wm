package com.github.moaxcp.graphwm;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.logging.LogLevel;
import io.micronaut.logging.LoggingSystem;
import javax.inject.Inject;
import org.slf4j.Logger;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "graph-wm", description = "A tiling window manager which uses a graph to make tiling easy.",
    mixinStandardHelpOptions = true)
public class WindowManagerCommand implements Runnable {

  @Option(names = {"--info"}, description = "ouput info logging")
  boolean info;
  @Option(names = {"--debug"}, description = "output debug logging")
  boolean debug;
  @Option(names = {"--trace"}, description = "output trace logging")
  boolean trace;
  @Option(names = {"-d", "--display"}, description = "display to use")
  String displayArg;

  @Inject
  private WindowManager manager;

  @Inject
  private LoggingSystem loggingSystem;

  public static void main(String[] args) throws Exception {
    PicocliRunner.run(WindowManagerCommand.class, args);
  }

  /**
   * Runs application.
   */
  public void run() {
    configureLogging();
    manager.setupDisplay(displayArg);
  }

  private void configureLogging() {
    LogLevel level;
    if(info) {
      level = LogLevel.INFO;
    } else if (debug) {
      level = LogLevel.DEBUG;
    } else if (trace) {
      level = LogLevel.TRACE;
    } else {
      level = LogLevel.WARN;
    }
    loggingSystem.setLogLevel(Logger.ROOT_LOGGER_NAME, level);
  }
}
