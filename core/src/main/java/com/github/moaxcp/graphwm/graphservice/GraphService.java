package com.github.moaxcp.graphwm.graphservice;

import com.github.moaxcp.graphs.*;
import lombok.*;

/**
 * Encapsulates the graph for the windows manager. Translates domain objects to the graph and back. Enforces the
 * invariants of the windows manager and ensures the graph is always valid.
 */
public class GraphService {
  //todo need an unmofiable view
  @Getter private Graph<String> graph = new UndirectedGraph<>();

  public class Screen {
    private String id;

    private Screen(String id) {
      this.id = id;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      graph.getVertex(id).setId(id);
      this.id = id;
    }

    public int getWidth() {
      return graph.getVertex(id).getProperty("width");
    }

    public void setWidth(int width) {
      graph.getVertex(id).property("width", width);
    }

    public int getHeight() {
      return graph.getVertex(id).getProperty("height");
    }

    public void setHeight(int height) {
      graph.getVertex(id).property("height", height);
    }
  }

  public Screen screen(@NonNull String id, int width, int height) {
    graph.vertex(id, "type", "Screen", "width", width, "height", height);
    Screen screen = new Screen(id);
    return screen;
  }


}
