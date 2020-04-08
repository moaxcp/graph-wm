package com.github.moaxcp.graphwm.graph;

import io.vavr.collection.*;
import lombok.*;

/**
 * An immutable graph where each edge has a non-unique value.
 */
@Value
@With
@AllArgsConstructor
@NonNull
public class ValueGraph<K, V> {

  @Value
  @NonNull
  public static class Edge<K> {
    K source;
    K target;

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Edge<?> edge = (Edge<?>) o;

      return (source.equals(edge.source) || source.equals(edge.target)) && (target.equals(edge.source) || target.equals(edge.target));
    }

    @Override
    public int hashCode() {
      int result = source.hashCode();
      result = 31 * result * target.hashCode();
      return result;
    }
  }

  Set<K> vertices;
  Map<Edge<K>, V> edges;

  public ValueGraph() {
    vertices = HashSet.empty();
    edges = HashMap.empty();
  }

  public ValueGraph<K, V> vertex(@NonNull K id) {
    return withVertices(vertices.add(id));
  }

  public ValueGraph<K, V> edge(@NonNull K source, @NonNull K target, @NonNull V value) {
    return withVertices(vertices.add(source).add(target))
        .withEdges(edges.put(new Edge<>(source, target), value));
  }

  public boolean isEmpty() {
    return vertices.isEmpty();
  }

  public Set<Edge<K>> edgesWithValue(@NonNull K value) {
    return edges.toStream()
        .filter(t -> t._2().equals(value))
        .map(t -> t._1())
        .foldLeft(HashSet.of(), HashSet::add);
  }

  public Set<K> verticesWithEdgeValue(@NonNull K value) {
    return edgesWithValue(value).toStream()
      .foldLeft(HashSet.of(), (set, edge) -> set.add(edge.source).add(edge.target));
  }
}
