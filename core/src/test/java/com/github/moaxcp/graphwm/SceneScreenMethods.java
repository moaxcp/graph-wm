package com.github.moaxcp.graphwm;

import io.vavr.*;
import io.vavr.collection.*;
import org.junit.jupiter.api.*;

import static org.assertj.vavr.api.VavrAssertions.*;

public class SceneScreenMethods {
  @Test
  void withScreens() {
    var scene = new Scene();
    scene = scene.withScreens(HashMap.of("id", new Screen("id")));
    assertThat(scene.getScreens()).containsExactly(new Tuple2<>("id", new Screen("id")));
  }

  @Test
  void withScreen() {
    var scene = new Scene();
    scene = scene.withScreen(new Screen("id"));
    assertThat(scene.getScreens()).containsExactly(new Tuple2<>("id", new Screen("id")));
  }

  @Test
  void withScreen_id() {
    var scene = new Scene();
    scene = scene.withScreen("id");
    assertThat(scene.getScreens()).containsExactly(new Tuple2<>("id", new Screen("id")));
  }

  @Test
  void removeScreen() {
    var scene = new Scene(HashMap.of("id", new Screen("id")));
    scene = scene.removeScreen("id");
    assertThat(scene.getScreens()).isEmpty();
  }
}
