package com.github.moaxcp.graphwm.graph;

import com.github.moaxcp.graphwm.graph.ValueGraph.*;
import io.vavr.*;
import io.vavr.collection.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ValueGraphTest {
  @Test
  void constructor() {
    var graph = new ValueGraph<>();
    assertThat(graph.getVertices()).isEmpty();
    assertThat(graph.getEdges()).isEmpty();
    assertThat(graph.isEmpty()).isTrue();
  }

  @Test
  void vertex_null_id() {
    var graph = new ValueGraph<>();
    var exception = assertThrows(NullPointerException.class, () -> graph.vertex(null));
    assertThat(exception).hasMessage("id is marked non-null but is null");
  }

  @Test
  void vertex_id() {
    var graph = new ValueGraph<>()
        .vertex(1);
    assertThat(graph.getVertices()).containsExactly(1);
  }

  @Test
  void edge_null_source() {
    var graph = new ValueGraph<>();
    var exception = assertThrows(NullPointerException.class, () -> graph.edge(null, 1, 3));
    assertThat(exception).hasMessage("source is marked non-null but is null");
  }

  @Test
  void edge_null_target() {
    var graph = new ValueGraph<>();
    var exception = assertThrows(NullPointerException.class, () -> graph.edge(1, null, 3));
    assertThat(exception).hasMessage("target is marked non-null but is null");
  }

  @Test
  void edge_null_value() {
    var graph = new ValueGraph<>();
    var exception = assertThrows(NullPointerException.class, () -> graph.edge(1, 2, null));
    assertThat(exception).hasMessage("value is marked non-null but is null");
  }

  @Test
  void edge() {
    var graph = new ValueGraph<Integer, Integer>()
        .edge(1, 2, 3);
    assertThat(graph.getVertices()).containsExactly(1, 2);
    assertThat(graph.getEdges()).containsExactly(Tuple.of(new Edge<>(1, 2), 3));
  }

  @Test
  void edge_reverseOrder() {
    var graph = new ValueGraph<>()
        .edge(1, 2, 3)
        .edge(2, 1, 3);
    assertThat(graph.getVertices()).containsExactly(1, 2);
    assertThat(graph.getEdges()).containsExactly(Tuple.of(new Edge<>(1, 2), 3));
  }

  @Test
  void edgesWithValues_null() {
    var exception = assertThrows(NullPointerException.class, () -> new ValueGraph<>().edgesWithValue(null));
    assertThat(exception).hasMessage("value is marked non-null but is null");
  }

  @Test
  void edgesWithValues() {
    var graph = new ValueGraph<>()
        .edge(1, 2, 'A')
        .edge(3, 4, 'A')
        .edge(5, 6, 'A')
        .edge(10, 11, 'B')
        .edge(20, 21, 'B');
    assertThat(graph.edgesWithValue('A')).isEqualTo(HashSet.of(new Edge<>(1, 2),
        new Edge<>(3, 4),
        new Edge<>(5, 6)));
  }

  @Test
  void verticesWithEdgeValue_null() {
    var graph = new ValueGraph<>();
    var exception = assertThrows(NullPointerException.class, () -> graph.verticesWithEdgeValue(null));
    assertThat(exception).hasMessage("value is marked non-null but is null");
  }

  @Test
  void verticesWithEdgeValue() {
    var graph = new ValueGraph<>()
        .edge(1, 2, 'A')
        .edge(3, 4, 'A')
        .edge(5, 6, 'A')
        .edge(10, 11, 'B')
        .edge(20, 21, 'B');
    assertThat(graph.verticesWithEdgeValue('A')).containsExactly(1, 2, 3, 4, 5, 6);
  }
}
