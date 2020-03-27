package com.github.moaxcp.graphwm;

import io.vavr.collection.*;
import lombok.*;

@With(AccessLevel.PRIVATE)
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Desktop {
  @NonNull Map<String, Screen> screens;
  @NonNull Map<String, Workspace> workspaces;

  public Desktop() {
    screens = HashMap.empty();
    workspaces = HashMap.empty();
  }

  public Desktop screen(@NonNull String id, int width, int height) {
    return withScreens(screens.put(id, new Screen(id, width, height)));
  }

  public Desktop removeScreen(@NonNull String id) {
    return withScreens(screens.remove(id));
  }

  public Desktop workspace(@NonNull String id) {
    return withWorkspaces(workspaces.put(id, new Workspace(id)));
  }

  public Desktop removeWorkspace(@NonNull String id) {
    return withWorkspaces(workspaces.remove(id));
  }

  public Desktop assignWorkspace(@NonNull String workspaceId, @NonNull String screenId) {
    if(!workspaces.containsKey(workspaceId)) {
      throw new IllegalArgumentException("Workspace \"%s\" not found.".formatted(workspaceId));
    }
    if(!screens.containsKey(screenId)) {
      throw new IllegalArgumentException("Screen \"%s\" not found.".formatted(screenId));
    }
    Screen screen = screens.get(screenId).get()
      .withWorkspace(workspaces.get(workspaceId).get());
    return withScreens(screens.put(screenId, screen));
  }
}
