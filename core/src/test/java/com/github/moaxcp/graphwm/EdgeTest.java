package com.github.moaxcp.graphwm;

import org.junit.jupiter.api.*;

import static com.github.moaxcp.graphwm.Edge.*;
import static com.github.moaxcp.graphwm.Point.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class EdgeTest {
  private Point point = point(0, 0);
  @Test
  void edge_negativeWidth() {
    var exception = assertThrows(IllegalArgumentException.class, () -> edge(point, -1, -1));
    assertThat(exception).hasMessage("width must be positive.");
  }

  @Test
  void edge_negativeHeight() {
    var exception = assertThrows(IllegalArgumentException.class, () -> edge(point, 100, -1));
    assertThat(exception).hasMessage("height must be positive.");
  }

  @Test
  void createEdge() {
    var edge = edge(point, 1, 1000);
    assertThat(edge.getPoint()).isEqualTo(point);
    assertThat(edge.getWidth()).isEqualTo(1);
    assertThat(edge.getHeight()).isEqualTo(1000);
  }
}
