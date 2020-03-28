package com.github.moaxcp.graphwm;

import org.junit.jupiter.api.*;

import static com.github.moaxcp.graphwm.Tile.*;
import static com.github.moaxcp.graphwm.Point.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class TileTest {

  private Point point = point(100, 100);
  @Test
  void tile_nullPoint() {
    var exception = assertThrows(NullPointerException.class, () -> tile(1, null, 0, 0));
    assertThat(exception).hasMessage("point is marked non-null but is null");
  }

  @Test
  void tile_negativeWidth() {
    var exception = assertThrows(IllegalArgumentException.class, () -> tile(1, point, -100, 0));
    assertThat(exception).hasMessage("width must be positive.");
  }

  @Test
  void tile_negativeHeight() {
    var exception = assertThrows(IllegalArgumentException.class, () -> tile(1, point, 100, -1));
    assertThat(exception).hasMessage("height must be positive.");
  }

  @Test
  void createTile() {
    var tile = tile(1, point, 1, 1000);
    assertThat(tile.getPoint()).isEqualTo(point);
    assertThat(tile.getWidth()).isEqualTo(1);
    assertThat(tile.getHeight()).isEqualTo(1000);
  }
}
