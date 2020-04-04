package com.github.moaxcp.graphwm;

import com.github.moaxcp.graphwm.graph.*;
import io.vavr.collection.*;
import lombok.*;

import static com.github.moaxcp.graphwm.Edge.*;
import static com.github.moaxcp.graphwm.Point.*;
import static com.github.moaxcp.graphwm.Tile.*;

@Value
@With(AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Workspace {

  private static final int EDGE_WIDTH = 1;
  @NonNull String name;
  int width;
  int height;
  ValueGraph<Integer, Integer> graph;
  Map<Integer, Tile> tiles;
  Map<Integer, Edge> edges;

  Workspace(@NonNull String name, int width, int height) {
    this.name = name;
    this.width = width;
    this.height = height;
    tiles = HashMap.of(0, tile(0, point(0, 0), width, height));
    graph = new ValueGraph<Integer, Integer>().vertex(0);
    edges = HashMap.empty();
  }

  Workspace splitTileWest(int tileId) {
    var tile = tiles.get(tileId).getOrElseThrow(() -> new IllegalArgumentException("tileId \"{}\" not found.".formatted(tileId)));
    int edgeX = (tile.getWidth() - 1 - EDGE_WIDTH / 2) / 2;
    var edge = edge(getNextId(edges.keySet()), tile.getNorthEastCorner().withX(edgeX), 1, tile.getHeight());
    var nextEdges = edges.put(edge.getId(), edge);

    var west = tile.withWidth(edge.getNorthWestCorner().getX() - tile.getNorthWestCorner().getX());
    var nextTiles = tiles.put(west.getId(), west);

    var northWest = edge.getNorthEastCorner().withXRelative(1);
    var east = tile(getNextId(nextTiles.keySet()), northWest, tile.getNorthEastCorner().getX() - edge.getNorthEastCorner().getX() - 1, height);
    nextTiles = nextTiles.put(east.getId(), east);

    return withEdges(nextEdges).withTiles(nextTiles);
  }

  private int getNextId(Set<Integer> keys) {
    return keys.toStream()
        .max()
        .map(max -> max + 1)
        .getOrElse(0);
  }
}
