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

  @Test
  void withX_negative() {
    var exception = assertThrows(IllegalArgumentException.class, () -> point(0, 0).withX(-1));
    assertThat(exception).hasMessage("x must be positive.");
  }

  @Test
  void withX() {
    Point point = point(0, 0)
        .withX(10);
    assertThat(point.getX()).isEqualTo(10);
    assertThat(point.getY()).isEqualTo(0);
  }

  @Test
  void withY_negative() {
    var exception = assertThrows(IllegalArgumentException.class, () -> point(0, 0).withY(-1));
    assertThat(exception).hasMessage("y must be positive.");
  }

  @Test
  void withY() {
    Point point = point(0, 0)
        .withY(10);
    assertThat(point.getX()).isEqualTo(0);
    assertThat(point.getY()).isEqualTo(10);
  }

  @Test
  void withXRelative_x_will_be_negative() {
    var exception = assertThrows(IllegalArgumentException.class, () -> point(0, 0).withXRelative(-1));
    assertThat(exception).hasMessage("x must be positive.");
  }

  @Test
  void withXRelative() {
    Point point = point(0, 0)
        .withXRelative(10);
    assertThat(point.getX()).isEqualTo(10);
    assertThat(point.getY()).isEqualTo(0);
  }

  @Test
  void withYRelative_y_will_be_negative() {
    var exception = assertThrows(IllegalArgumentException.class, () -> point(0, 0).withYRelative(-10));
    assertThat(exception).hasMessage("y must be positive.");
  }

  @Test
  void withYRelative() {
    Point point = point(0, 0)
        .withYRelative(10);
    assertThat(point.getX()).isEqualTo(0);
    assertThat(point.getY()).isEqualTo(10);
  }
}
