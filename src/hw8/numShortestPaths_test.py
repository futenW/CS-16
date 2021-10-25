#/usr/bin/python3

# DO NOT DELETE THESE STATEMENTS
import numShortestPaths
from importlib import reload
reload(numShortestPaths)
from numShortestPaths import *
from mygraph import *
import pytest

def sample_test():
    assert True != False, 'Error: True is equal to False'

def test1():
    g = MyGraph()
    v0 = GraphVertex("v0")
    v1 = GraphVertex("v1")
    v2 = GraphVertex("v2")
    g.insertVertex(v0)
    g.insertVertex(v1)
    g.insertVertex(v2)
    e0 = GraphEdge("e0", 1)
    e1 = GraphEdge("e1", 1)
    e2 = GraphEdge("e2", 1)
    g.insertEdge(v0, v2, e0)
    g.insertEdge(v0, v1, e1)
    g.insertEdge(v1, v2, e2)
    ret = numShortestPaths(g, v0, v2)
    assert ret == 1, "test failed"

















































def simple_test(): # only one shortest path
    g = MyGraph()
    v0 = GraphVertex("v0")
    v1 = GraphVertex("v1")
    v2 = GraphVertex("v2")
    g.insertVertex(v0)
    g.insertVertex(v1)
    g.insertVertex(v2)
    e0 = GraphEdge("e0", 1)
    e1 = GraphEdge("e1", 1)
    e2 = GraphEdge("e2", 1)
    g.insertEdge(v0, v2, e0)
    g.insertEdge(v0, v1, e1)
    g.insertEdge(v1, v2, e2)
    ret = numShortestPaths(g, v0, v2)
    assert ret == 1, " test failed"

def test_three_paths(): # three possible shortest paths
    e1 = GraphEdge("e1", 1)
    e2 = GraphEdge("e2", 1)
    e3 = GraphEdge("e3", 1)
    e4 = GraphEdge("e4", 1)
    e5 = GraphEdge("e5", 1)
    e6 = GraphEdge("e6", 1)

    g = MyGraph()
    p = GraphVertex("p")
    c1 = GraphVertex("c1")
    c2 = GraphVertex("c2")
    c3 = GraphVertex("c3")
    h = GraphVertex("h")

    g.insertVertex(p)
    g.insertVertex(c1)
    g.insertVertex(c2)
    g.insertVertex(c3)
    g.insertVertex(h)

    g.insertEdge(c1, p, e1)
    g.insertEdge(c2, p, e2)
    g.insertEdge(c3, p, e3)
    g.insertEdge(h, c1, e4)
    g.insertEdge(h, c2, e5)
    g.insertEdge(h, c3, e6)

    assert numShortestPaths(g, h, p) == 3

def one_vertex(): # only one vertex
    g = MyGraph()
    v0 = GraphVertex("v0")
    g.insertVertex(v0)
    ret = numShortestPaths(g, v0, v0)
    assert ret == 1, "test failed"

def multiple_paths_test(): # multiple shortest paths
    g = MyGraph()
    v0 = GraphVertex("v0")
    v1 = GraphVertex("v1")
    v2 = GraphVertex("v2")
    v3 = GraphVertex("v3")
    g.insertVertex(v0)
    g.insertVertex(v1)
    g.insertVertex(v2)
    g.insertVertex(v3)
    e0 = GraphEdge("e0", 1)
    e1 = GraphEdge("e1", 1)
    e2 = GraphEdge("e2", 1)
    e3 = GraphEdge("e3", 1)
    g.insertEdge(v0, v2, e0)
    g.insertEdge(v0, v1, e1)
    g.insertEdge(v2, v3, e2)
    g.insertEdge(v1, v3, e3)
    ret = numShortestPaths(g, v0, v3)
    assert ret == 2, "test failed"

def some_long_paths_test(): # multiple shortest paths with other valid path(s) that aren't shortest
    g = MyGraph()
    v0 = GraphVertex("v0")
    v1 = GraphVertex("v1")
    v2 = GraphVertex("v2")
    v3 = GraphVertex("v3")
    v4 = GraphVertex("v4")
    v5 = GraphVertex("v5")
    g.insertVertex(v0)
    g.insertVertex(v1)
    g.insertVertex(v2)
    g.insertVertex(v3)
    g.insertVertex(v4)
    g.insertVertex(v5)
    e0 = GraphEdge("e0", 1)
    e1 = GraphEdge("e1", 1)
    e2 = GraphEdge("e2", 1)
    e3 = GraphEdge("e3", 1)
    e4 = GraphEdge("e4", 1)
    e5 = GraphEdge("e5", 1)
    e6 = GraphEdge("e6", 1)
    g.insertEdge(v0, v2, e0)
    g.insertEdge(v0, v1, e1)
    g.insertEdge(v2, v3, e2)
    g.insertEdge(v1, v3, e3)
    g.insertEdge(v0, v4, e4)
    g.insertEdge(v4, v5, e5)
    g.insertEdge(v5, v3, e6)
    ret = numShortestPaths(g, v0, v3)
    assert ret == 2, "test failed"

def exception_test(): # should raise InvalidInputException
    g = MyGraph()
    v0 = GraphVertex("v0")
    v1 = GraphVertex("v1")
    v2 = None
    v4 = GraphVertex("v4")
    v5 = GraphVertex("v5")
    g.insertVertex(v4)
    g.insertVertex(v5)
    
    with pytest.raises(InvalidInputException):
        numShortestPaths(g, v0, v4) # one vertex not in graph
    with pytest.raises(InvalidInputException):
        numShortestPaths(g, v4, v0) # other vertex not in graph

    with pytest.raises(InvalidInputException):
        numShortestPaths(g, v0, v2) # one vertex is None
    with pytest.raises(InvalidInputException):
        numShortestPaths(g, v2, v0) # other vertex is None

    g = None # graph doesn't exist
    with pytest.raises(InvalidInputException):
        numShortestPaths(g, v0, v1) # only graph is None

def get_tests():
    return [sample_test, test1, simple_test, exception_test, multiple_paths_test, some_long_paths_test,
    test_three_paths, one_vertex]






















































# DO NOT EDIT BELOW THIS LINE ==================================================

# The mainline runs all of the test functions in the list returned by get_tests
if __name__ == '__main__' :
    print('Running tests...')
    for test in get_tests():
        test()
    print('All tests passed!')
