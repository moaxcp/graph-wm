package com.github.moaxcp.graphwm;

import org.junit.jupiter.api.*;

import static com.github.moaxcp.graphwm.Point.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class PointTest {

  @Test
  void point_negativeX() {
    var exception = assertThrows(IllegalArgumentException.class, () -> point(-1, -1));
    assertThat(exception).hasMessage("x must be positive.");
  }

  @Test
  void point_negativeY() {
    var exception = assertThrows(IllegalArgumentException.class, () -> point(0, -1));
    assertThat(exception).hasMessage("y must be positive.");
  }

  @Test
  void point_allArgs() {
    var point = point(100, 120);
    assertThat(point.getX()).isEqualTo(100);
    assertThat(point.getY()).isEqualTo(120);
  }
}
