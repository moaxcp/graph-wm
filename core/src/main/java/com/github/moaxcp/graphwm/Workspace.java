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

  /**
   * Splits a tile vertically keeping the original tile on the west side.
   * @param tileId
   * @return
   */
  Workspace splitTileWest(int tileId) {
    var nextId = getNextId();

    var tile = tiles.get(tileId).getOrElseThrow(() -> new IllegalArgumentException("tileId \"{}\" not found.".formatted(tileId)));

    var edgePoint = tile.getNorthWestCorner().withXRelative(tile.getWidth() / 2 - EDGE_WIDTH / 2);
    var edge = edge(nextId++, edgePoint, EDGE_WIDTH, tile.getHeight());
    var nextEdges = edges.put(edge.getId(), edge);

    var west = tile.withWidth(edge.getNorthWestCorner().getX() - tile.getNorthWestCorner().getX());
    var nextTiles = tiles.put(west.getId(), west);

    var northWest = edge.getNorthEastCorner().withXRelative(1);
    var east = tile(nextId++, northWest, tile.getNorthEastCorner().getX() - edge.getNorthEastCorner().getX(), height);
    nextTiles = nextTiles.put(east.getId(), east);

    var nextGraph = graph.edge(west.getId(), east.getId(), edge.getId());

    return withEdges(nextEdges).withTiles(nextTiles).withGraph(nextGraph);
  }

  private int getNextId() {
    return Stream.of(tiles.keySet(), edges.keySet())
        .flatMap(Set::toStream)
        .max()
        .map(max -> max + 1)
        .getOrElse(0);
  }

  public Workspace splitTileNorth(int tileId) {
    var nextId = getNextId();

    var tile = tiles.get(tileId).getOrElseThrow(() -> new IllegalArgumentException("tileId \"{}\" not found.".formatted(tileId)));

    var edgePoint = tile.getNorthWestCorner().withYRelative(tile.getHeight() / 2 - EDGE_WIDTH / 2);
    var edge = edge(nextId++, edgePoint, tile.getWidth(), EDGE_WIDTH);
    var nextEdges = edges.put(edge.getId(), edge);

    var north = tile.withHeight(edge.getNorthWestCorner().getY() - tile.getNorthWestCorner().getY());
    var nextTiles = tiles.put(north.getId(), north);

    var northWest = edge.getSouthWestCorner().withYRelative(1);
    var south = tile(nextId++, northWest, tile.getWidth(), tile.getSouthWestCorner().getY() - edge.getSouthWestCorner().getY());
    nextTiles = nextTiles.put(south.getId(), south);

    var nextGraph = graph.edge(north.getId(), south.getId(), edge.getId());
    return withEdges(nextEdges).withTiles(nextTiles).withGraph(nextGraph);
  }

  public Workspace resizeNorth(int edgeId, int resize) {

    return this;
  }

  public Set<Integer> getTilesWithEdge(int edgeId) {
    return graph.verticesWithEdgeValue(edgeId);
  }
}
