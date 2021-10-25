from heappriorityqueue import *
from mygraph import *

def dijkstra(g, src):
    """ Calculate the shortest path tree from the src in the input
    connected graph g using Dijkstra's algorithm. The elements attached
    to the edges should be the distances. Must run in O((|E| + |V|) log |V|)
    time using the provided HeapPriorityQueue data structure.

    Returns the shortest path tree in the form of a new MyGraph object.
    Do not modify the input MyGraph instance.

    Raise the InvalidInputException if input is None or if src is not in g.

    Note: To access the actual vertices in the HeapPriorityQueue,
    you need to call pop().value(), not just pop().
    """
    if g == None:
        raise InvalidInputException("graph is None")
    if src == None:
        raise InvalidInputException("source is None")
    if g.containsVertex(src) == False:
        raise InvalidInputException("source not in graph")
    if g.numVertices() == 1: # src is only node in graph
        tree = MyGraph()
        tree.insertVertex(src)
        return tree
    if g.numVertices() == 0:
        raise InvalidInputException("graph is empty")
    
    tree = MyGraph()
    tree.insertVertex(src)
    q = HeapPriorityQueue()

    for v in g.iterVertices(): # initialize starting vars
        if v == src:
            v.dist = 0
        else:
            v.dist = float('inf')
        v.prev = None
        v.entry = q.push(v.dist, v) # add all to priority queue

    while q.isEmpty() == False:
        u = q.pop().value()
        for e in g.incidentEdges(u):
            v = g.opposite(u, e) # vertex opposite of "u" through edge "e"
            if u.dist + e.element() < v.dist:
                v.dist = u.dist + e.element()
                v.prev = u
                q.replaceKey(v.entry, v.dist)

                if tree.containsVertex(v): # if already in tree
                    for parent in tree.incidentEdges(v): # WORST-CASE will always have only one parent
                        tree.removeEdge(parent)
                else:
                    tree.insertVertex(v)
                tree.insertEdge(u, v, e) # add same edge

    return tree











































class InvalidInputException(Exception):
    def __str__(self):
        return "Invalid Input Given."
