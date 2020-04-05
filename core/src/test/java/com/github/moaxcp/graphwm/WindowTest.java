package com.github.moaxcp.graphwm;

import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.stream.*;

import static com.github.moaxcp.graphwm.Edge.*;
import static com.github.moaxcp.graphwm.Point.*;
import static com.github.moaxcp.graphwm.Tile.*;
import static org.assertj.core.api.Assertions.*;

public class WindowTest {
  private static final int INITIAL_X = 100;
  private static final int INITIAL_Y = 200;
  private static final int INITIAL_WIDTH = 100;
  private static final int INITIAL_HEIGHT = 200;
  @MethodSource
  static Stream<Window<?>> windows() {
    return Stream.of(
        edge(1, point(INITIAL_X, INITIAL_Y), INITIAL_WIDTH, INITIAL_HEIGHT),
        tile(1, point(INITIAL_X, INITIAL_Y), INITIAL_WIDTH, INITIAL_HEIGHT)
    );
  }

  @ParameterizedTest
  @MethodSource("windows")
  void initialWindow(Window<?> window) {
    assertThat(window.getWidth()).isEqualTo(INITIAL_WIDTH);
    assertThat(window.getHeight()).isEqualTo(INITIAL_HEIGHT);
    assertThat(window.getNorthWestCorner()).isEqualTo(point(INITIAL_X, INITIAL_Y));
    assertThat(window.getNorthEastCorner()).isEqualTo(point(INITIAL_X + INITIAL_WIDTH - 1, INITIAL_Y));
    assertThat(window.getSouthWestCorner()).isEqualTo(point(INITIAL_X, INITIAL_Y + INITIAL_HEIGHT - 1));
    assertThat(window.getSouthEastCorner()).isEqualTo(point(INITIAL_X + INITIAL_WIDTH - 1, INITIAL_Y + INITIAL_HEIGHT - 1));
  }

  @ParameterizedTest
  @MethodSource("windows")
  void withNorthWestCorner(Window<?> window) {
    window = window.withNorthWestCorner(point(111, 112));
    assertThat(window.getWidth()).isEqualTo(INITIAL_WIDTH);
    assertThat(window.getHeight()).isEqualTo(INITIAL_HEIGHT);
    assertThat(window.getNorthWestCorner()).isEqualTo(point(111, 112));
    assertThat(window.getNorthEastCorner()).isEqualTo(point(111 + INITIAL_WIDTH - 1, 112));
    assertThat(window.getSouthWestCorner()).isEqualTo(point(111, 112 + INITIAL_HEIGHT - 1));
    assertThat(window.getSouthEastCorner()).isEqualTo(point(111 + INITIAL_WIDTH - 1, 112 + INITIAL_HEIGHT - 1));
  }

  @ParameterizedTest
  @MethodSource("windows")
  void withWidth(Window<?> window) {
    window = window.withWidth(200);
    assertThat(window.getWidth()).isEqualTo(200);
    assertThat(window.getHeight()).isEqualTo(INITIAL_HEIGHT);
    assertThat(window.getNorthWestCorner()).isEqualTo(point(INITIAL_X, INITIAL_Y));
    assertThat(window.getNorthEastCorner()).isEqualTo(point(INITIAL_X + 200 - 1, INITIAL_Y));
    assertThat(window.getSouthWestCorner()).isEqualTo(point(INITIAL_X, INITIAL_Y + INITIAL_HEIGHT - 1));
    assertThat(window.getSouthEastCorner()).isEqualTo(point(INITIAL_X + 200 - 1, INITIAL_Y + INITIAL_HEIGHT - 1));
  }

  @ParameterizedTest
  @MethodSource("windows")
  void withHeight(Window<?> window) {
    window = window.withHeight(400);
    assertThat(window.getWidth()).isEqualTo(INITIAL_WIDTH);
    assertThat(window.getHeight()).isEqualTo(400);
    assertThat(window.getNorthWestCorner()).isEqualTo(point(INITIAL_X, INITIAL_Y));
    assertThat(window.getNorthEastCorner()).isEqualTo(point(INITIAL_X + INITIAL_WIDTH - 1, INITIAL_Y));
    assertThat(window.getSouthWestCorner()).isEqualTo(point(INITIAL_X, INITIAL_Y + 400 - 1));
    assertThat(window.getSouthEastCorner()).isEqualTo(point(INITIAL_X + INITIAL_WIDTH - 1, INITIAL_Y + 400 - 1));
  }

  @ParameterizedTest
  @MethodSource("windows")
  void resizeNorth_Positive(Window<?> window) {
    window = window.resizeNorth(10);
    assertThat(window.getWidth()).isEqualTo(INITIAL_WIDTH);
    assertThat(window.getHeight()).isEqualTo(INITIAL_HEIGHT - 10);
    assertThat(window.getNorthWestCorner()).isEqualTo(point(INITIAL_X, INITIAL_Y + 10));
    assertThat(window.getNorthEastCorner()).isEqualTo(point(INITIAL_X + INITIAL_WIDTH - 1, INITIAL_Y + 10));
    assertThat(window.getSouthWestCorner()).isEqualTo(point(INITIAL_X, INITIAL_Y + 10 + INITIAL_HEIGHT - 10 - 1));
    assertThat(window.getSouthEastCorner()).isEqualTo(point(INITIAL_X + INITIAL_WIDTH - 1, INITIAL_Y + 10 + INITIAL_HEIGHT - 10 - 1));
  }

  @ParameterizedTest
  @MethodSource("windows")
  void resizeNorth_Negative(Window<?> window) {
    window = window.resizeNorth(-10);
    assertThat(window.getWidth()).isEqualTo(INITIAL_WIDTH);
    assertThat(window.getHeight()).isEqualTo(INITIAL_HEIGHT + 10);
    assertThat(window.getNorthWestCorner()).isEqualTo(point(INITIAL_X, INITIAL_Y - 10));
    assertThat(window.getNorthEastCorner()).isEqualTo(point(INITIAL_X + INITIAL_WIDTH - 1, INITIAL_Y - 10));
    assertThat(window.getSouthWestCorner()).isEqualTo(point(INITIAL_X, INITIAL_Y + 10 + INITIAL_HEIGHT - 10 - 1));
    assertThat(window.getSouthEastCorner()).isEqualTo(point(INITIAL_X + INITIAL_WIDTH - 1, INITIAL_Y + 10 + INITIAL_HEIGHT - 10 - 1));
  }

  @ParameterizedTest
  @MethodSource("windows")
  void resizeSouth_Positive(Window<?> window) {
    window = window.resizeSouth(10);
    assertThat(window.getWidth()).isEqualTo(INITIAL_WIDTH);
    assertThat(window.getHeight()).isEqualTo(INITIAL_HEIGHT + 10);
    assertThat(window.getNorthWestCorner()).isEqualTo(point(INITIAL_X, INITIAL_Y));
    assertThat(window.getNorthEastCorner()).isEqualTo(point(INITIAL_X + INITIAL_WIDTH - 1, INITIAL_Y));
    assertThat(window.getSouthWestCorner()).isEqualTo(point(INITIAL_X, INITIAL_Y + INITIAL_HEIGHT + 10 - 1));
    assertThat(window.getSouthEastCorner()).isEqualTo(point(INITIAL_X + INITIAL_WIDTH - 1, INITIAL_Y + INITIAL_HEIGHT + 10 - 1));
  }

  @ParameterizedTest
  @MethodSource("windows")
  void resizeSouth_Negative(Window<?> window) {
    window = window.resizeSouth(-10);
    assertThat(window.getWidth()).isEqualTo(INITIAL_WIDTH);
    assertThat(window.getHeight()).isEqualTo(INITIAL_HEIGHT - 10);
    assertThat(window.getNorthWestCorner()).isEqualTo(point(INITIAL_X, INITIAL_Y));
    assertThat(window.getNorthEastCorner()).isEqualTo(point(INITIAL_X + INITIAL_WIDTH - 1, INITIAL_Y));
    assertThat(window.getSouthWestCorner()).isEqualTo(point(INITIAL_X, INITIAL_Y + INITIAL_HEIGHT - 10 - 1));
    assertThat(window.getSouthEastCorner()).isEqualTo(point(INITIAL_X + INITIAL_WIDTH - 1, INITIAL_Y + INITIAL_HEIGHT - 10 - 1));
  }

  @ParameterizedTest
  @MethodSource("windows")
  void resizeWest_Positive(Window<?> window) {
    window = window.resizeWest(10);
    assertThat(window.getWidth()).isEqualTo(INITIAL_WIDTH - 10);
    assertThat(window.getHeight()).isEqualTo(INITIAL_HEIGHT);
    assertThat(window.getNorthWestCorner()).isEqualTo(point(INITIAL_X + 10, INITIAL_Y));
    assertThat(window.getNorthEastCorner()).isEqualTo(point(INITIAL_X + 10 + INITIAL_WIDTH - 10 - 1, INITIAL_Y));
    assertThat(window.getSouthWestCorner()).isEqualTo(point(INITIAL_X + 10, INITIAL_Y + INITIAL_HEIGHT - 1));
    assertThat(window.getSouthEastCorner()).isEqualTo(point(INITIAL_X + 10 + INITIAL_WIDTH - 10 - 1, INITIAL_Y + INITIAL_HEIGHT - 1));
  }

  @ParameterizedTest
  @MethodSource("windows")
  void resizeWest_Negative(Window<?> window) {
    window = window.resizeWest(-10);
    assertThat(window.getWidth()).isEqualTo(INITIAL_WIDTH + 10);
    assertThat(window.getHeight()).isEqualTo(INITIAL_HEIGHT);
    assertThat(window.getNorthWestCorner()).isEqualTo(point(INITIAL_X - 10, INITIAL_Y));
    assertThat(window.getNorthEastCorner()).isEqualTo(point(INITIAL_X - 10 + INITIAL_WIDTH + 10 - 1, INITIAL_Y));
    assertThat(window.getSouthWestCorner()).isEqualTo(point(INITIAL_X - 10, INITIAL_Y + INITIAL_HEIGHT - 1));
    assertThat(window.getSouthEastCorner()).isEqualTo(point(INITIAL_X - 10 + INITIAL_WIDTH + 10 - 1, INITIAL_Y + INITIAL_HEIGHT - 1));
  }

  @ParameterizedTest
  @MethodSource("windows")
  void resizeEast_Positive(Window<?> window) {
    window = window.resizeEast(10);
    assertThat(window.getWidth()).isEqualTo(INITIAL_WIDTH + 10);
    assertThat(window.getHeight()).isEqualTo(INITIAL_HEIGHT);
    assertThat(window.getNorthWestCorner()).isEqualTo(point(INITIAL_X, INITIAL_Y));
    assertThat(window.getNorthEastCorner()).isEqualTo(point(INITIAL_X + INITIAL_WIDTH + 10 - 1, INITIAL_Y));
    assertThat(window.getSouthWestCorner()).isEqualTo(point(INITIAL_X, INITIAL_Y + INITIAL_HEIGHT - 1));
    assertThat(window.getSouthEastCorner()).isEqualTo(point(INITIAL_X + INITIAL_WIDTH + 10 - 1, INITIAL_Y + INITIAL_HEIGHT - 1));
  }
}
