package com.github.moaxcp.graphwm;

import io.vavr.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class DesktopScreenMethods {

  @Test
  void screen_null() {
    var desktop = new Desktop();
    var exception = assertThrows(NullPointerException.class, () -> desktop.screen(null, 0, 0));
    assertThat(exception).hasMessage("id is marked non-null but is null");
  }

  @Test
  void screen() {
    var desktop = new Desktop();
    desktop = desktop.screen("id", 100, 120);
    assertThat(desktop.getScreens()).containsExactly(new Tuple2<>("id", new Screen("id", 100, 120)));
  }

  @Test
  void screen_modifyWidthHeight() {
    var desktop = new Desktop().screen("id", 100, 120);
    desktop = desktop.screen("id", 400, 500);
    assertThat(desktop.getScreens()).containsExactly(new Tuple2<>("id", new Screen("id", 400, 500)));
  }

  @Test
  void removeScreen_nullId() {
    var exception = assertThrows(NullPointerException.class, () -> new Desktop().removeScreen(null));
    assertThat(exception).hasMessage("id is marked non-null but is null");
  }

  @Test
  void removeScreen_empty() {
    var desktop = new Desktop().removeScreen("id");
    assertThat(desktop.getScreens()).isEmpty();
  }

  @Test
  void removeScreen() {
    var desktop = new Desktop().screen("id", 100, 120);
    desktop = desktop.removeScreen("id");
    assertThat(desktop.getScreens()).isEmpty();
  }

  @Test
  void assignWorkspace_nullWorkspaceId() {
    var desktop = new Desktop();
    var exception = assertThrows(NullPointerException.class, () -> desktop.assignWorkspace(null, "id"));
    assertThat(exception).hasMessage("workspaceId is marked non-null but is null");
  }

  @Test
  void assignWorkspace_nullScreenId() {
    var desktop = new Desktop();
    var exception = assertThrows(NullPointerException.class, () -> desktop.assignWorkspace("id", null));
    assertThat(exception).hasMessage("screenId is marked non-null but is null");
  }

  @Test
  void assignWorkspace_workspaceNotFound() {
    var desktop = new Desktop().screen("screen", 100, 120);
    var exception = assertThrows(IllegalArgumentException.class, () -> desktop.assignWorkspace("workspace", "screen"));
    assertThat(exception).hasMessage("Workspace \"workspace\" not found.");
  }

  @Test
  void assignWorkspace_screenNotFound() {
    var desktop = new Desktop().workspace("workspace");
    var exception = assertThrows(IllegalArgumentException.class, () -> desktop.assignWorkspace("workspace", "screen"));
    assertThat(exception).hasMessage("Screen \"screen\" not found.");
  }

  @Test
  void assignWorkspace() {
    var desktop = new Desktop().workspace("workspace").screen("screen", 100, 120);
    desktop = desktop.assignWorkspace("workspace", "screen");
    var workspace = desktop.getWorkspaces().get("workspace").get();
    assertThat(desktop.getScreens().get("screen").get().getWorkspace().get()).isSameAs(workspace);
  }
}
