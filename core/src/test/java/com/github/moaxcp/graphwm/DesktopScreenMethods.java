package com.github.moaxcp.graphwm;

import io.vavr.*;
import io.vavr.collection.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class DesktopScreenMethods {
  @Test
  void withScreens_null() {
    var desktop = new Desktop();
    var exception = assertThrows(NullPointerException.class, () -> desktop.withScreens(null));
    assertThat(exception).hasMessage("screens is marked non-null but is null");
  }

  @Test
  void withScreens() {
    var desktop = new Desktop();
    desktop = desktop.withScreens(HashMap.of("id", new Screen("id")));
    assertThat(desktop.getScreens()).containsExactly(new Tuple2<>("id", new Screen("id")));
  }

  @Test
  void withScreen_null() {
    var desktop = new Desktop();
    var exception = assertThrows(NullPointerException.class, () -> desktop.withScreen((Screen) null));
    assertThat(exception).hasMessage("screen is marked non-null but is null");
  }

  @Test
  void withScreen() {
    var desktop = new Desktop();
    desktop = desktop.withScreen(new Screen("id"));
    assertThat(desktop.getScreens()).containsExactly(new Tuple2<>("id", new Screen("id")));
  }

  @Test
  void withScreen_nullId() {
    var desktop = new Desktop();
    var exception = assertThrows(NullPointerException.class, () -> desktop.withScreen((String) null));
    assertThat(exception).hasMessage("id is marked non-null but is null");
  }

  @Test
  void withScreen_id() {
    var desktop = new Desktop();
    desktop = desktop.withScreen("id");
    assertThat(desktop.getScreens()).containsExactly(new Tuple2<>("id", new Screen("id")));
  }

  @Test
  void removeScreen_nullId() {
    var exception = assertThrows(NullPointerException.class, () -> new Desktop().removeScreen(null));
    assertThat(exception).hasMessage("id is marked non-null but is null");
  }

  @Test
  void removeScreen() {
    var desktop = new Desktop().withScreen(new Screen("id"));
    desktop = desktop.removeScreen("id");
    assertThat(desktop.getScreens()).isEmpty();
  }
}
