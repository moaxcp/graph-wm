package com.github.moaxcp.graphwm;

import static com.github.moaxcp.graphwm.Point.*;

public interface Window {
  Point getNorthWestCorner();

  default Point getNorthEastCorner() {
    var northWest = getNorthWestCorner();
    return point(northWest.getX() + getWidth(), northWest.getY());
  }

  int getWidth();

  int getHeight();
}
