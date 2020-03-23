package com.github.moaxcp.graphwm.graphservice;

import com.github.moaxcp.graphwm.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ScreenConstructor {
  @Test
  void constructor() {
    var screen = new Screen("id");
    assertThat(screen.getId()).isEqualTo("id");
    assertThat(screen.getWidth()).isEqualTo(0);
    assertThat(screen.getHeight()).isEqualTo(0);
  }

  @Test
  void constructor_null() {
    var exception = assertThrows(NullPointerException.class, () -> new Screen(null));
    assertThat(exception).hasMessage("id is marked non-null but is null");
  }
}
