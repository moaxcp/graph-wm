package com.github.moaxcp.graphwm;

import io.vavr.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


public class DesktopWorkspaceMethods {
  @Test
  void workspace_null() {
    var desktop = new Desktop();
    var exception = assertThrows(NullPointerException.class, () -> desktop.workspace(null));
    assertThat(exception).hasMessage("id is marked non-null but is null");
  }

  @Test
  void workspace() {
    var desktop = new Desktop().workspace("id");
    assertThat(desktop).satisfies(d -> {
      assertThat(d.getWorkspaces()).containsExactly(new Tuple2<>("id", new Workspace("id")));
    });
  }

  @Test
  void removeWorkspace_null() {
    var desktop = new Desktop();
    var exception = assertThrows(NullPointerException.class, () -> desktop.removeWorkspace(null));
    assertThat(exception).hasMessage("id is marked non-null but is null");
  }

  @Test
  void removeWorkspace_empty() {
    var desktop = new Desktop().removeWorkspace("id");
    assertThat(desktop.getWorkspaces()).isEmpty();
  }

  @Test
  void removeWorkspace() {
    var desktop = new Desktop().workspace("id");
    desktop = desktop.removeWorkspace("id");
    assertThat(desktop.getWorkspaces()).isEmpty();
  }
}
