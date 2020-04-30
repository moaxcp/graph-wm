package com.github.moaxcp.graphwm;

import io.vavr.collection.*;
import lombok.*;

/**
 * Represents a desktop. Immutable.
 */
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

  public Desktop workspace(@NonNull String id, int width, int height) {
    return withWorkspaces(workspaces.put(id, new Workspace(id, width, height)));
  }

  public Desktop removeWorkspace(@NonNull String id) {
    //todo cannot remove workspace if it is assigned to a screen?
    return withWorkspaces(workspaces.remove(id));
  }

  /**
   * Assigns a workspace to a screen using the provided ids.
   * @param workspaceId workspace to assign
   * @param screenId screen to assign workspace
   * @return the resulting Desktop
   */
  public Desktop assignWorkspace(@NonNull String workspaceId, @NonNull String screenId) {
    if (!workspaces.containsKey(workspaceId)) {
      throw new IllegalArgumentException("Workspace \"%s\" not found.".formatted(workspaceId));
    }
    if (!screens.containsKey(screenId)) {
      throw new IllegalArgumentException("Screen \"%s\" not found.".formatted(screenId));
    }
    //todo implement in screen and then call from desktop
    Screen screen = screens.get(screenId).get()
        .withWorkspace(workspaces.get(workspaceId).get());
    return withScreens(screens.put(screenId, screen));
  }
}
