package com.github.moaxcp.graphwm;

import lombok.*;

/**
 * Represents a screen within the windows manager. A screen has dementions and defines edges for the screen. Workspaces
 * attached to the screen must fit within those edges.
 */
@Value
@Builder
public class Screen {
  private int width;
  private int height;
}
