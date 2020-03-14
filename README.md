# graph-wm

Experimental project!

A tiling windows manager based on a graph rather than a tree. Instead of 
having a non-visible hierarchy of split panels, which users must memorize, 
this window manager will use a graph which a user can infer by the tiles 
displayed.

The graph is made up of vertices and edges. There are different types of
vertices. Some vertices (Screen, Workspace, Tile) define borders around other 
vertices. A tile vertice defines a border between other tiles. Borders are
represented as edges in the graph.

## Types of Vertices

### Screen

The screen represents the hardware display. It has dementions based on what is
set by the operating system. A screen can display exactly one workspace. A
screen can switch which workspace it displays.

### Workspace

A workspace is a grouping of Tiles. A workspace has the same dementions as the
screen containing it. A workspace can only be displayed by one screen. There 
can be no gaps between tiles in a workspace. The screen can be switched 
between workspaces.

### Tile

A tile is a container for clients. Only one client can be displayed in a tile.
A tile must always be adjacent to other tile edges or the edges of the 
workspace which contains the tile.

### Client

A client is an application managed by the windows manager. It is a client to
X11. A client must fill the space of the tile completely.

## Edges

Edges in the graph represent the border between adjacent vertices in the 
graph. All adjacent edges for a vertex must be a rectangle.

Question: can edges have width?

### Screen edges

The combination of all adjacent edges for a screen must match the dementions
of the X11 display.

### Workspace edges

The combined dementions of all adjacent edges for a workspace must match the
dementions of the screen.

### Tile edges


