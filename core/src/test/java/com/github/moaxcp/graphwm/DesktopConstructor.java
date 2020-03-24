package com.github.moaxcp.graphwm;

import io.vavr.collection.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class DesktopConstructor {

  @Test
  void customConstructor() {
    var desktop = new Desktop();
    assertThat(desktop).satisfies(d -> {
      assertThat(d.getScreens()).isEmpty();
      assertThat(d.getWorkspaces()).isEmpty();
    });
  }

  @Test
  void allArgsConstructor_nullScreens() {
    var exception = assertThrows(NullPointerException.class, () -> new Desktop(null, null));
    assertThat(exception).hasMessage("screens is marked non-null but is null");
  }

  @Test
  void allArgsConstructor_nullWorkspaces() {
    var exception = assertThrows(NullPointerException.class, () -> new Desktop(HashMap.empty(), null));
    assertThat(exception).hasMessage("workspaces is marked non-null but is null");
  }

  @Test
  void allArgsConstructor() {
    var desktop = new Desktop(HashMap.empty(), HashMap.empty());
    assertThat(desktop).satisfies(d -> {
      assertThat(d.getScreens()).isEmpty();
      assertThat(d.getWorkspaces()).isEmpty();
    });
  }
}
