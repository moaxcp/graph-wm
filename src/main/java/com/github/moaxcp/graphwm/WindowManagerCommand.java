package com.github.moaxcp.graphwm;

import gnu.x11.Display;
import io.micronaut.configuration.picocli.PicocliRunner;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import static gnu.x11.DisplayName.parse;

@Command(name = "graph-wm", description = "A tiling window manager which uses a graph to make tiling easy.",
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

    try(Display display = parse().connect()) {

    }
  }
}
