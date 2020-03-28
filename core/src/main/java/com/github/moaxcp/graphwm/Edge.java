package com.github.moaxcp.graphwm;

import lombok.*;

@Value
@With
public class Edge implements Window {

  public static Edge edge(int id, Point point, int width, int height) {
    return new Edge(id, point, width, height);
  }

  int id;
  Point point;
  int width;
  int height;

  private Edge(int id, @NonNull Point point, int width, int height) {
    this.id = id;
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
