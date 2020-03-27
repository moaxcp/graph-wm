package com.github.moaxcp.graphwm;

import lombok.*;

@Value
@With
public class Edge implements Window {

  public static Edge edge(Point point, int width, int height) {
    return new Edge(point, width, height);
  }

  Point point;
  int width;
  int height;

  private Edge(Point point, int width, int height) {
    if (width < 0) {
      throw new IllegalArgumentException("width must be positive.");
    }
    if (height < 0) {
      throw new IllegalArgumentException("height must be positive.");
    }
    this.point = point;
    this.width = width;
    this.height = height;
  }
}
