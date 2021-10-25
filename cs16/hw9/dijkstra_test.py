#!/usr/bin/python3

# DO NOT DELETE THESE STATEMENTS
import dijkstra
from importlib import reload
reload(dijkstra)
from dijkstra import *
from mygraph import *
import pytest

# Write your testing functions here! Each testing function should have an
# informative name and test a specific aspect of your program's functionality.
# It is fine to have multiple assert statements in each function to test for
# various related conditions.

# DO NOT write your tests within the example test functions we provide!
# Our scripts will skip the test functions we provide, so write your own
# functions to test your code thoroughly.

# Here are some example test functions. Feel free to delete example_test_1
# and remove it from the list in get_tests().

def example_test_1():
    assert 1 == 1, 'Error: 1 does not equal 1!'

def simple_test():
    # Setup simple graph
    g = MyGraph()
    v0 = GraphVertex("v0")
    v1 = GraphVertex("v1")
    v2 = GraphVertex("v2")
    g.insertVertex(v0)
    g.insertVertex(v1)
    g.insertVertex(v2)

    e0 = GraphEdge("e0", 8)
    e1 = GraphEdge("e1", 3)
    e2 = GraphEdge("e2", 4)
    g.insertEdge(v0, v2, e0)
    g.insertEdge(v0, v1, e1)
    g.insertEdge(v1, v2, e2)

    # Run the algorithm
    ret = dijkstra(g, v0)

    # Make sure it matches our expectations
    assert e1 in ret.edges()
    assert e2 in ret.edges()
    assert e0 not in ret.edges()






















































def exceptions_test(): # should raise InvalidInputExceptions
    # Setup simple graph
    g = MyGraph()
    v0 = GraphVertex("v0")

    with pytest.raises(InvalidInputException): # None input
        dijkstra(None, v0)
    with pytest.raises(InvalidInputException): # None input
        dijkstra(g, None)
    with pytest.raises(InvalidInputException): # None input
        dijkstra(None, None)
    with pytest.raises(InvalidInputException): # source not in graph
        dijkstra(g, v0)

def basic_test(): # basic test to ensure it works as intended
    # Setup simple graph
    g = MyGraph()
    v0 = GraphVertex("v0")
    v1 = GraphVertex("v1")
    v2 = GraphVertex("v2")
    g.insertVertex(v0)
    g.insertVertex(v1)
    g.insertVertex(v2)

    e0 = GraphEdge("e0", 8)
    e1 = GraphEdge("e1", 3)
    e2 = GraphEdge("e2", 4)
    g.insertEdge(v0, v2, e0)
    g.insertEdge(v0, v1, e1)
    g.insertEdge(v1, v2, e2)
    # g.popup("basic_test graph")

    # Run the algorithm
    ret = dijkstra(g, v0)
    assert e1 in ret.edges() # Make sure it matches our expectations
    assert e2 in ret.edges()
    assert e0 not in ret.edges()
    # ret.popup("v0")

    # Run the algorithm
    ret = dijkstra(g, v1) # Make sure it matches our expectations
    assert e1 in ret.edges()
    assert e2 in ret.edges()
    assert e0 not in ret.edges()
    # ret.popup("v1")

    # Run the algorithm
    ret = dijkstra(g, v2) # Make sure it matches our expectations
    assert e1 in ret.edges()
    assert e2 in ret.edges()
    assert e0 not in ret.edges()
    # ret.popup("v2")

def source_only_node(): # source is only node in graph
    # Setup simple graph
    g = MyGraph()
    v0 = GraphVertex("v0")
    g.insertVertex(v0)
    tree = dijkstra(g, v0) # run the algorithm
    assert tree.numVertices() == 1, "test failed"
    assert tree.containsVertex(v0) == True, "test failed"

def multiple_shortest(): # there are multiple shortest paths
    # Setup simple graph
    g = MyGraph()
    v0 = GraphVertex("v0")
    v1 = GraphVertex("v1")
    v2 = GraphVertex("v2")
    g.insertVertex(v0)
    g.insertVertex(v1)
    g.insertVertex(v2)

    e0 = GraphEdge("e0", 8)
    e1 = GraphEdge("e1", 4)
    e2 = GraphEdge("e2", 4)
    g.insertEdge(v0, v2, e0)
    g.insertEdge(v0, v1, e1)
    g.insertEdge(v1, v2, e2)

    # Run the algorithm
    ret = dijkstra(g, v0)
    assert e1 in ret.edges() # Make sure it matches our expectations
    assert (e0 in ret.edges() and e2 not in ret.edges()) or (e0 not in ret.edges() and e2 in ret.edges())

    # Run the algorithm
    ret = dijkstra(g, v1) # Make sure it matches our expectations
    assert e1 in ret.edges()
    assert e2 in ret.edges()
    assert e0 not in ret.edges()

    # Run the algorithm
    ret = dijkstra(g, v2) # Make sure it matches our expectations
    assert e2 in ret.edges()
    assert (e1 in ret.edges() and e0 not in ret.edges()) or (e1 not in ret.edges() and e0 in ret.edges())

def two_nodes(): # only two nodes in the graph
    # Setup simple graph
    g = MyGraph()
    v1 = GraphVertex("v1")
    v2 = GraphVertex("v2")
    g.insertVertex(v1)
    g.insertVertex(v2)

    e0 = GraphEdge("e0", 1)
    g.insertEdge(v1, v2, e0)

    # Run the algorithm
    ret = dijkstra(g, v1) # Make sure it matches our expectations
    assert e0 in ret.edges()

def empty_graph(): # zero nodes in graph
    g= MyGraph()
    v1 = GraphVertex("v1")
    with pytest.raises(InvalidInputException):
        ret = dijkstra(g, v1) # Run the algorithm

def get_tests():
    return [example_test_1, simple_test,
    basic_test, exceptions_test, source_only_node, multiple_shortest, two_nodes, empty_graph]











































































# DO NOT EDIT BELOW THIS LINE ==================================================

# The mainline runs all of the test functions in the list returned by get_tests
if __name__ == '__main__' :
    print('Running tests...')
    for test in get_tests():
        test()
    print('All tests passed!')
