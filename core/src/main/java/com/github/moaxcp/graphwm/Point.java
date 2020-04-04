package com.github.moaxcp.graphwm;

import lombok.*;

/**
 * A point on the screen. The coordinates x and y must be positive.
 */
@Value
@With
public class Point {

  static Point point(int x, int y) {
    return new Point(x, y);
  }

  int x;
  int y;

  private Point(int x, int y) {
    if (x < 0) {
      throw new IllegalArgumentException("x must be positive.");
    }
    if (y < 0) {
      throw new IllegalArgumentException("y must be positive.");
    }
    this.x = x;
    this.y = y;
  }

  public Point withXRelative(int add) {
    return withX(x + add);
  }
}
