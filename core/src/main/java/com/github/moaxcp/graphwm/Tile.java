package com.github.moaxcp.graphwm;

import lombok.*;

/**
 * A Tile of a workspace. Tiles must be within the boundaries of the surrounding edges. Edges may be shared among other
 * tiles.
 */
@Value
@With
public class Tile implements Window<Tile> {

  static Tile tile(int id, Point point, int width, int height) {
    return new Tile(id, point, width, height);
  }

  int id;
  @NonNull Point northWestCorner;
  int width;
  int height;

  private Tile(int id, @NonNull Point northWestCorner, int width, int height) {
    this.id = id;
    if (width < 0) {
      throw new IllegalArgumentException("width must be positive.");
    }
    if (height < 0) {
      throw new IllegalArgumentException("height must be positive.");
    }
    this.northWestCorner = northWestCorner;
    this.width = width;
    this.height = height;
  }
}
