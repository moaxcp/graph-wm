package com.github.moaxcp.graphwm;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

public class DesktopConstructor {

  @Test
  void customConstructor() {
    var desktop = new Desktop();
    assertThat(desktop).satisfies(d -> {
      assertThat(d.getScreens()).isEmpty();
      assertThat(d.getWorkspaces()).isEmpty();
    });
  }
}
