package com.github.moaxcp.graphwm;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ScreenConstructor {
  @Test
  void customConstructor_nullId() {
    var exception = assertThrows(NullPointerException.class, () -> new Screen(null, 0, 0));
    assertThat(exception).hasMessage("id is marked non-null but is null");
  }

  @Test
  void customConstructor() {
    var screen = new Screen("id", 0, 0);
    assertThat(screen).satisfies(s -> {
      assertThat(s.getId()).isEqualTo("id");
      assertThat(s.getWidth()).isEqualTo(0);
      assertThat(s.getHeight()).isEqualTo(0);
    });
  }
}
