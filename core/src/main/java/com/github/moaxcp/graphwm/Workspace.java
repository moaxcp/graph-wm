package com.github.moaxcp.graphwm;

import lombok.*;

@Value
@With
@AllArgsConstructor
public class Workspace {
  @NonNull String id;
  int width;
  int height;

  public Workspace(@NonNull String id) {
    this.id = id;
    width = 0;
    height = 0;
  }
}
