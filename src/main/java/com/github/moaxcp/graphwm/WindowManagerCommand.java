package com.github.moaxcp.graphwm;

import io.micronaut.configuration.picocli.*;
import picocli.CommandLine.*;

@Command(name = "window-manager", description = "...",
    mixinStandardHelpOptions = true)
public class WindowManagerCommand implements Runnable {

  @Option(names = {"-v", "--verbose"}, description = "...")
  boolean verbose;

  public static void main(String[] args) throws Exception {
    PicocliRunner.run(WindowManagerCommand.class, args);
  }

  /**
   * Runs application.
   */
  public void run() {
    // business logic here
    if (verbose) {
      System.out.println("Hi!");
    }
  }
}
