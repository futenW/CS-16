class InvalidInputException(Exception):
    def __str__(self):
        return "Invalid Input given."

def numShortestPaths(g, start, end):
    """graph, start node, end node -> int
    Purpose: find the number of shortest paths between two nodes in a graph
    Raises: raise InvalidInputException if an input is None, or
    if start or end are not in g"""

    if g == None: # None exceptions
        raise InvalidInputException("graph is None")
    if start == None:
        raise InvalidInputException("start is None")
    if end == None:
        raise InvalidInputException("end is None")
    
    if g.containsVertex(start) == False: # not contained in graph exceptions
        raise InvalidInputException("start not in graph")
    if g.containsVertex(end) == False:
        raise InvalidInputException("end not in graph")
    if start == end:
        return 1

    num_paths = 0 # number of shortest paths
    curr_shortest_length = g.numVertices() * 10 # the length of shortest possible path

    for v in g.iterVertices(): # initialize visited, set stops to higher than possible
        v.visited = False
        v.stops = g.numVertices() * 5

    queue = []
    start.stops = 0 # itself
    start.visited = True
    queue.append(start)

    while len(queue) != 0:
        temp = queue.pop(0)
        for e in g.incidentEdges(temp): # decorate stops and visited
            neighbor = g.opposite(temp, e)
            if neighbor.visited == False:
                neighbor.visited = True
                neighbor.stops = temp.stops + 1
                queue.append(neighbor)
            if neighbor == end: # reached end, check if is a possible shortest stop
                if temp.stops + 1 < curr_shortest_length:
                    curr_shortest_length = temp.stops + 1
                    num_paths = 1
                elif temp.stops + 1 == curr_shortest_length:
                    num_paths += 1
    
    return num_paths