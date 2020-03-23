package com.github.moaxcp.graphwm;

import lombok.*;

@Value
@With
@AllArgsConstructor
public class Workspace {
  String id;
  int width;
  int height;

  public Workspace(String id) {
    this.id = id;
    width = 0;
    height = 0;
  }
}
