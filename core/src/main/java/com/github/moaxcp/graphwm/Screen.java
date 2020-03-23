package com.github.moaxcp.graphwm;

import lombok.*;

@Value
@With
@AllArgsConstructor
public class Screen {
  @NonNull String id;
  int width;
  int height;
  Workspace workspace;

  public Screen(@NonNull String id) {
    this.id = id;
    width = 0;
    height = 0;
    workspace = null;
  }
}
