package com.github.moaxcp.graphwm;

import static com.github.moaxcp.graphwm.Point.*;

public interface Window<SELF extends Window<SELF>> {
  int getId();

  Point getNorthWestCorner();

  public SELF withNorthWestCorner(Point northWestCorner);

  int getWidth();

  SELF withWidth(int width);

  int getHeight();

  SELF withHeight(int height);

  default Point getNorthEastCorner() {
    var northWest = getNorthWestCorner();
    return point(northWest.getX() + getWidth() - 1, northWest.getY());
  }

  default Point getSouthWestCorner() {
    var northWest = getNorthWestCorner();
    return point(northWest.getX(), northWest.getY() + getHeight() - 1);
  }

  default Point getSouthEastCorner() {
    var northEast = getNorthEastCorner();
    return point(northEast.getX(), northEast.getY() + getHeight() - 1);
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
