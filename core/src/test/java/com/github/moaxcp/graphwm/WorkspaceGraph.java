package com.github.moaxcp.graphwm;

import io.vavr.*;
import org.junit.jupiter.api.*;

import static com.github.moaxcp.graphwm.Edge.*;
import static com.github.moaxcp.graphwm.Point.*;
import static com.github.moaxcp.graphwm.Tile.*;
import static org.assertj.vavr.api.VavrAssertions.*;

public class WorkspaceGraph {
  @Test
  void splitTileWest() {
    var workspace = new Workspace("workspace", 200, 200);
    workspace = workspace.splitTileWest(0);
    assertThat(workspace.getTiles()).containsExactly(
        Tuple.of(0, tile(0, point(0, 0), 99, 200)),
        Tuple.of(1, tile(1, point(101, 0), 99, 200)));
    assertThat(workspace.getEdges()).containsExactly(Tuple.of(0, edge(0, point(99, 0), 1, 200)));
  }
}
