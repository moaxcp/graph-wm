package com.github.moaxcp.graphwm;

import io.vavr.*;
import org.junit.jupiter.api.*;

import static com.github.moaxcp.graphwm.Point.*;
import static com.github.moaxcp.graphwm.Tile.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class WorkspaceConstructor {
  @Test
  void customConstructor_null() {
    var exception = assertThrows(NullPointerException.class, () -> new Workspace(null, 0, 0));
    assertThat(exception).hasMessage("name is marked non-null but is null");
  }

  @Test
  void customConstructor() {
    var workspace = new Workspace("name", 220, 230);
    assertThat(workspace).satisfies(w -> {
      assertThat(w.getName()).isEqualTo("name");
      assertThat(w.getWidth()).isEqualTo(220);
      assertThat(w.getHeight()).isEqualTo(230);
      assertThat(w.getGraph().getEdges()).isEmpty();
      assertThat(w.getGraph().getVertices()).containsExactly(0);
      assertThat(w.getEdges()).isEmpty();
      assertThat(w.getTiles()).containsExactly(Tuple.of(0, tile(0, point(0, 0), 220, 230)));
    });
  }
}
