package com.github.moaxcp.graphwm;

import com.github.moaxcp.graphwm.graph.*;
import io.vavr.*;
import org.junit.jupiter.api.*;

import static com.github.moaxcp.graphwm.Edge.*;
import static com.github.moaxcp.graphwm.Point.*;
import static com.github.moaxcp.graphwm.Tile.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

public class WorkspaceSplit {
  @Test
  void splitTileWest() {
    var workspace = new Workspace("workspace", 200, 200)
      .splitTileWest(0);
    assertThat(workspace.getEdges()).containsExactly(Tuple.of(1, edge(1, point(100, 0), 1, 200)));
    assertThat(workspace.getTiles()).containsExactly(
        Tuple.of(0, tile(0, point(0, 0), 100, 200)),
        Tuple.of(2, tile(2, point(101, 0), 99, 200)));
    assertThat(workspace.getGraph()).satisfies(graph -> {
      assertThat(graph.getVertices()).containsExactly(0, 2);
      assertThat(graph.getEdges()).containsExactly(Tuple.of(new ValueGraph.Edge<Integer>(0, 2), 1));
    });
  }

  @Test
  void splitTileNorth() {
    var workspace = new Workspace("workspace", 200, 200)
        .splitTileNorth(0);
    assertThat(workspace.getEdges()).containsExactly(Tuple.of(1, edge(1, point(0, 100), 200, 1)));
    assertThat(workspace.getTiles()).containsExactly(
        Tuple.of(0, tile(0, point(0, 0), 200, 100)),
        Tuple.of(2, tile(2, point(0, 101), 200, 99)));
    assertThat(workspace.getGraph()).satisfies(graph -> {
      assertThat(graph.getVertices()).containsExactly(0, 2);
      assertThat(graph.getEdges()).containsExactly(Tuple.of(new ValueGraph.Edge<Integer>(0, 2), 1));
    });
  }
}
