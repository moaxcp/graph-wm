package com.github.moaxcp.graphwm;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

public class WorkspaceMethods {
  @Test
  void getTilesWithEdge() {
    var workspace = new Workspace("workspace", 400, 500)
        .splitTileNorth(0);
    assertThat(workspace.getTilesWithEdge(workspace.getEdges().get()._1()))
        .containsExactly(0, 2);
  }
}
