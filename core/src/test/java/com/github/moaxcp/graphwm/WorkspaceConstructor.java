package com.github.moaxcp.graphwm;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class WorkspaceConstructor {
  @Test
  void customConstructor_null() {
    var exception = assertThrows(NullPointerException.class, () -> new Workspace(null));
    assertThat(exception).hasMessage("id is marked non-null but is null");
  }

  @Test
  void customConstructor() {
    var workspace = new Workspace("id");
    assertThat(workspace).satisfies(w -> {
      assertThat(w.getId()).isEqualTo("id");
      assertThat(w.getWidth()).isEqualTo(0);
      assertThat(w.getHeight()).isEqualTo(0);
    });
  }

  @Test
  void allArgsConstructor_null() {
    var exception = assertThrows(NullPointerException.class, () -> new Workspace(null, 0, 0));
    assertThat(exception).hasMessage("id is marked non-null but is null");
  }

  @Test
  void allArgsConstructor() {
    var workspace = new Workspace("id", 100, 120);
    assertThat(workspace).satisfies(w -> {
      assertThat(w.getId()).isEqualTo("id");
      assertThat(w.getWidth()).isEqualTo(100);
      assertThat(w.getHeight()).isEqualTo(120);
    });
  }
}