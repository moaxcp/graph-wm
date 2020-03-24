package com.github.moaxcp.graphwm;

import io.vavr.collection.*;
import lombok.*;

@With
@Value
@AllArgsConstructor
public class Desktop {
  @NonNull Map<String, Screen> screens;
  @NonNull Map<String, Workspace> workspaces;

  public Desktop() {
    screens = HashMap.empty();
    workspaces = HashMap.empty();
  }

  public Desktop withScreen(@NonNull String id) {
    return withScreen(new Screen(id));
  }

  public Desktop withScreen(@NonNull Screen screen) {
    return withScreens(screens.put(screen.getId(), screen));
  }

  public Desktop removeScreen(@NonNull String id) {
    return withScreens(screens.remove(id));
  }
}
