package com.github.moaxcp.graphwm.graphservice;

import com.github.moaxcp.graphwm.graphservice.GraphService.*;
import org.junit.jupiter.api.*;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class GraphServiceScreenMethods {
  private final GraphService service = new GraphService();

  @Test
  void screen_nullId() {
    var exception = assertThrows(NullPointerException.class, () -> service.screen(null, 0, 0));
    assertThat(exception).hasMessageThat().isEqualTo("id is marked non-null but is null");
  }

  @Test
  void createScreen() {
    Screen screen = service.screen("id", 100, 120);
    assertThat(screen).isNotNull();
    assertThat(screen.getWidth()).isEqualTo(100);
    assertThat(screen.getHeight()).isEqualTo(120);
    assertThat(service.getGraph()).hasVertex("id").withProperty("width").hasValue(100);
    assertThat(service.getGraph()).hasVertex("id").withProperty("height").hasValue(120);
  }
}
