package com.github.moaxcp.graphwm;

import com.github.moaxcp.graphs.*;
import lombok.*;

/**
 * Encapsulates the graph for the windows manager. Translates domain objects to the graph and back. Enforces the
 * invariants of the windows manager and ensures the graph is always valid.
 */
public class GraphService {
  @Getter private Graph graph = new UndirectedGraph();

  @Value
  public class Screen {
    private final int width;
    private final int height;
  }

  @Builder
  public Screen screen(int width, int height) {
    ScreenBuilder builder = new ScreenBuilder();
    return builder.width(width).height(height).build();
  }


}
