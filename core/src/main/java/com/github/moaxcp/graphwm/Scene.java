package com.github.moaxcp.graphwm;

import io.vavr.collection.*;
import lombok.*;

@With
@Value
@AllArgsConstructor
public class Scene {
  Map<String, Screen> screens;

  public Scene() {
    screens = HashMap.empty();
  }

  public Scene withScreen(String id) {
    return withScreen(new Screen(id));
  }

  public Scene withScreen(Screen screen) {
    return withScreens(screens.put(screen.getId(), screen));
  }

  public Scene removeScreen(String id) {
    return withScreens(screens.remove(id));
  }
}
