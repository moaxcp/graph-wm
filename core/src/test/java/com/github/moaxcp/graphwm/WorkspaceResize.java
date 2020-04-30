package com.github.moaxcp.graphwm;

import io.vavr.*;
import org.junit.jupiter.api.*;

import static com.github.moaxcp.graphwm.Edge.*;
import static com.github.moaxcp.graphwm.Point.*;
import static com.github.moaxcp.graphwm.Tile.*;
import static org.assertj.vavr.api.VavrAssertions.*;

public class WorkspaceResize {
  @Test
  @Disabled
  void resizeNorth() {
    var workspace = new Workspace("workspace", 400, 500)
        .splitTileNorth(0);
    assertThat(workspace.getEdges()).hasSize(1);
    var edge = workspace.getEdges().get()._2();
    workspace = workspace.resizeNorth(edge.getId(), 25);

    assertThat(workspace.getEdges()).containsExactly(Tuple.of(1, edge(1, point(0, 225), 400, 1)));
    assertThat(workspace.getTiles()).containsExactly(
        Tuple.of(0, tile(0, point(0, 0), 400, 225)),
        Tuple.of(2, tile(2, point(0, 226), 200, 224)));
  }
}
