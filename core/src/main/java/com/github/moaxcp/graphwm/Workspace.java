package com.github.moaxcp.graphwm;

import com.github.moaxcp.graphwm.graph.*;
import io.vavr.collection.*;
import lombok.*;

@Value
@With
@AllArgsConstructor
public class Workspace {
  @NonNull String id;
  int width;
  int height;
  ValueGraph<Integer, Integer> graph;
  Map<Integer, Tile> tiles;
  Map<Integer, Edge> edges;

  public Workspace(@NonNull String id) {
    this.id = id;
    width = 0;
    height = 0;
    graph = new ValueGraph<>();
    tiles = HashMap.empty();
    edges = HashMap.empty();
  }
}
