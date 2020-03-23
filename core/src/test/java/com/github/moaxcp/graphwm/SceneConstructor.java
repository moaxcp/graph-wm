package com.github.moaxcp.graphwm;

import io.vavr.*;
import io.vavr.collection.*;
import org.junit.jupiter.api.*;

import static org.assertj.vavr.api.VavrAssertions.*;

public class SceneConstructor {
  @Test
  void constructor() {
    var scene = new Scene();
    assertThat(scene.getScreens()).isEmpty();
  }

  @Test
  void constructor_allArgs() {
    var scene = new Scene(HashMap.of("id", new Screen("id")));
    assertThat(scene.getScreens()).containsExactly(new Tuple2<>("id", new Screen("id")));
  }
}
