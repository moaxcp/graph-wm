package com.github.moaxcp.graphwm.graphservice;

import org.junit.jupiter.api.*;

import static com.github.moaxcp.graphs.truth.Truth.*;

public class GraphServiceConstructor {
  @Test
  void graphIsEmpty() {
    var service = new GraphService();
    assertThat(service.getGraph()).isEmpty();
  }
}
