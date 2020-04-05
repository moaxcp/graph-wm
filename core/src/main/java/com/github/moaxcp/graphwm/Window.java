package com.github.moaxcp.graphwm;

public interface Window<SELF extends Window<SELF>> {
  int getId();

  Point getNorthWestCorner();

  public SELF withNorthWestCorner(Point northWestCorner);

  int getWidth();

  SELF withWidth(int width);

  int getHeight();

  SELF withHeight(int height);

  default Point getNorthEastCorner() {
    return getNorthWestCorner()
        .withXRelative(getWidth() - 1);
  }

  default Point getSouthWestCorner() {
    return getNorthWestCorner()
        .withYRelative(getHeight() - 1);
  }

  default Point getSouthEastCorner() {
    return getNorthEastCorner()
        .withYRelative(getHeight() - 1);
  }

  default SELF resizeNorth(int north) {
    return withHeight(getHeight() - north)
        .withNorthWestCorner(getNorthWestCorner().withYRelative(north));
  }

  default SELF resizeSouth(int south) {
    return withHeight(getHeight() + south);
  }

  default SELF resizeWest(int west) {
    return withWidth(getWidth() - west)
        .withNorthWestCorner(getNorthWestCorner().withXRelative(west));
  }

  default SELF resizeEast(int east) {
    return withWidth(getWidth() + east);
  }
}
