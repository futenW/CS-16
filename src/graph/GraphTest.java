package graph;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static support.graph.Constants.MAX_VERTICES;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import support.graph.*;

/**
 * This class tests the functionality of a Graph based on a 'String' type
 * parameter for the vertices. Edge elements are Integers. The general framework
 * of a test case is: - Name the test method descriptively, mentioning what is
 * being tested (it is ok to have slightly verbose method names here) - Set-up
 * the program state (ex: instantiate a heap and insert K,V pairs into it) - Use
 * assertions to validate that the program is in the state you expect it to be
 * See header comments over tests for what each test does
 * 
 * Before each test is run, you can assume that the '_graph' variable is reset to
 * a new instance of the a Graph<String> that you can simply use 'as is'
 * via the methods under the 'DO NOT MODIFY ANYTHING BELOW THIS LINE' line.
 * Of course, please do not modify anything below that, or the above 
 * assumptions may be broken.
 */
@RunWith(Parameterized.class)
public class GraphTest {
    

    // Undirected Graph instance variable
    private Graph<String> _graph;
    // Directed Graph instance variable
    private Graph<String> _dirGraph;
    private String _graphClassName;
	
    /**
     * A simple test for insertVertex, that adds 3 vertices and then checks to
     * make sure they were added by accessing them through the vertices
     * iterator.
     */
    @Test(timeout = 10000)
    public void testInsertVertex() {
        // insert vertices
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // use the vertex iterator to get a list of the vertices in the actual
        // graph
        List<CS16Vertex<String>> actualVertices = new ArrayList<CS16Vertex<String>>();
        Iterator<CS16Vertex<String>> it = _graph.vertices();
        while (it.hasNext()) {
            actualVertices.add(it.next());
        }

        // assert that the graph state is consistent with what you expect
        assertThat(actualVertices.size(), is(3));
        assertThat(actualVertices.contains(A), is(true));
        assertThat(actualVertices.contains(B), is(true));
        assertThat(actualVertices.contains(C), is(true));
    }

    /**
     * A simple test for removeVertex, that adds 3 vertices and then checks to
     * make sure they were added by accessing them through the vertices
     * iterator. Then remove the vertex's and checks to make sure they were removed
     * by accessing them through the vertices iterator
     */
    @Test(timeout = 10000)
    public void testRemoveVertex() {
        // insert vertices
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // use the vertex iterator to get a list of the vertices in the actual graph
        List<CS16Vertex<String>> actualVertices = new ArrayList<CS16Vertex<String>>();
        Iterator<CS16Vertex<String>> it = _graph.vertices();
        while (it.hasNext()) {
            actualVertices.add(it.next());
        }

        // assert that the graph state is consistent with what you expect
        assertThat(actualVertices.size(), is(3));
        assertThat(actualVertices.contains(A), is(true));
        assertThat(actualVertices.contains(B), is(true));
        assertThat(actualVertices.contains(C), is(true));

        _graph.removeVertex(A);
        _graph.removeVertex(B);
        _graph.removeVertex(C);

        // use the vertex iterator to get a list of the vertices in the actual graph
        List<CS16Vertex<String>> actualVertices2 = new ArrayList<CS16Vertex<String>>();
        Iterator<CS16Vertex<String>> it2 = _graph.vertices();
        while (it2.hasNext()) {
            actualVertices.add(it2.next());
        }

        // assert that the graph state is consistent with what you expect
        assertThat(actualVertices2.size(), is(0));
        assertThat(actualVertices2.contains(A), is(false));
        assertThat(actualVertices2.contains(B), is(false));
        assertThat(actualVertices2.contains(C), is(false));
    }

    // Same test as above, but with a directed graph
    @Test(timeout = 10000)
    public void testInsertVertexDirected() {
        // insert vertices
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        // use the vertex iterator to get a list of the vertices in the actual
        // graph
        List<CS16Vertex<String>> actualVertices = new ArrayList<CS16Vertex<String>>();
        Iterator<CS16Vertex<String>> it = _dirGraph.vertices();
        while (it.hasNext()) {
            actualVertices.add(it.next());
        }

        // assert that the graph state is consistent with what you expect
        assertThat(actualVertices.size(), is(3));
        assertThat(actualVertices.contains(A), is(true));
        assertThat(actualVertices.contains(B), is(true));
        assertThat(actualVertices.contains(C), is(true));
    }

    // Same test as above, but with a directed graph
    @Test(timeout = 10000)
    public void testRemoveVertexDirected() {
        // insert vertices
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        // use the vertex iterator to get a list of the vertices in the actual graph
        List<CS16Vertex<String>> actualVertices = new ArrayList<CS16Vertex<String>>();
        Iterator<CS16Vertex<String>> it = _dirGraph.vertices();
        while (it.hasNext()) {
            actualVertices.add(it.next());
        }

        // assert that the graph state is consistent with what you expect
        assertThat(actualVertices.size(), is(3));
        assertThat(actualVertices.contains(A), is(true));
        assertThat(actualVertices.contains(B), is(true));
        assertThat(actualVertices.contains(C), is(true));

        _dirGraph.removeVertex(A);
        _dirGraph.removeVertex(B);
        _dirGraph.removeVertex(C);

        // use the vertex iterator to get a list of the vertices in the actual graph
        List<CS16Vertex<String>> actualVertices2 = new ArrayList<CS16Vertex<String>>();
        Iterator<CS16Vertex<String>> it2 = _dirGraph.vertices();
        while (it2.hasNext()) {
            actualVertices.add(it2.next());
        }

        // assert that the graph state is consistent with what you expect
        assertThat(actualVertices2.size(), is(0));
        assertThat(actualVertices2.contains(A), is(false));
        assertThat(actualVertices2.contains(B), is(false));
        assertThat(actualVertices2.contains(C), is(false));
    }


    /**
     * A simple test for insertEdges that adds 3 vertices, adds two edges to the
     * graph and then asserts that both edges were in fact added using the edge
     * iterator as well as checks to make sure their from and to vertices were
     * set correctly.
     */
    @Test(timeout = 10000)
    public void testInsertEdges() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // add edges.
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);

        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it = _graph.edges();
        while (it.hasNext()) {
            actualEdges.add(it.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges.size(), is(2));
        assertThat(actualEdges.contains(ab), is(true));
        assertThat(actualEdges.contains(bc), is(true));
    }

    /**
     * A simple test for removeEdges that adds 3 vertices, adds two edges to the graph and then asserts that both edges
     * were in fact added using the edge iterator as well as checks to make sure their from and to vertices were
     * set correctly. Then removes edges and asserts that all edges wer in fact removed using the edge iterator.
     */
    @Test(timeout = 10000)
    public void testRemoveEdges() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // add edges.
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);

        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it = _graph.edges();
        while (it.hasNext()) {
            actualEdges.add(it.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges.size(), is(2));
        assertThat(actualEdges.contains(ab), is(true));
        assertThat(actualEdges.contains(bc), is(true));

        _graph.removeEdge(ab);
        _graph.removeEdge(bc);

        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges2 = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it2 = _graph.edges();
        while (it2.hasNext()) {
            actualEdges.add(it2.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges2.size(), is(0));
        assertThat(actualEdges2.contains(ab), is(false));
        assertThat(actualEdges2.contains(bc), is(false));
    }


    // Same test as above, but with a directed graph
    @Test(timeout = 10000)
    public void testInsertEdgesDirected() {
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        // use the edge iterator to get a list of the edges in the actual graph.
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);

        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it = _dirGraph.edges();
        while (it.hasNext()) {
            actualEdges.add(it.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges.size(), is(2));
        assertThat(actualEdges.contains(ab), is(true));
        assertThat(actualEdges.contains(bc), is(true));
    }

    // Same test as above, but with a directed graph
    @Test(timeout = 10000)
    public void testRemoveEdgesDirected() {
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        // use the edge iterator to get a list of the edges in the actual graph.
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);

        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it = _dirGraph.edges();
        while (it.hasNext()) {
            actualEdges.add(it.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges.size(), is(2));
        assertThat(actualEdges.contains(ab), is(true));
        assertThat(actualEdges.contains(bc), is(true));

        _dirGraph.removeEdge(ab);
        _dirGraph.removeEdge(bc);

        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges2 = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it2 = _dirGraph.edges();
        while (it2.hasNext()) {
            actualEdges.add(it2.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges2.size(), is(0));
        assertThat(actualEdges2.contains(ab), is(false));
        assertThat(actualEdges2.contains(bc), is(false));
    }

    ////////////////////////////////////////////////////////////////////////////
    /////////////////// Insert & Remove Exceptions /////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /**
     * null input vertex to insertEdge
     */
    @Test(expected = InvalidVertexException.class)
    public void testInsertEdgesException() {
        CS16Vertex<String> B = _graph.insertVertex("B");

        CS16Edge<String> ab = _graph.insertEdge(null, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, null, 2);
    }

    /**
     * null input edge to removeEdge
     */
    @Test (expected = InvalidEdgeException.class)
    public void testRemoveEdgesException() {
        _graph.removeEdge(null);
    }

    /**
     * null input vertex to removeVertex
     */
    @Test (expected = InvalidVertexException.class)
    public void testRemoveVertexException() {
        _graph.removeVertex(null);
    }

    ////////////////////////////////////////////////////////////////////////////
    /////////////////// Test Additional Methods  ///////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /**
     * A simple test for incomingEdges that adds 3 vertices, adds two edges to the
     * graph and then asserts that incoming edges to A contain one but not the other
     */
    @Test(timeout = 10000)
    public void testIncomingEdges() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // add edges.
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);

        // use the edge iterator to get a list of the incoming edges
        List<CS16Edge<String>> actualEdges2 = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it2 = _graph.incomingEdges(A);
        while (it2.hasNext()) {
            actualEdges2.add(it2.next());
        }

        assertThat(actualEdges2.contains(ab), is(true));
        assertThat(actualEdges2.contains(bc), is(false));
    }

    // Same test as above, but with a directed graph
    @Test(timeout = 10000)
    public void testIncomingEdgesDirected() {
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        // use the edge iterator to get a list of the edges in the actual graph.
        CS16Edge<String> ba = _dirGraph.insertEdge(B, A, 1);
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 2);

        // use the edge iterator to get a list of the incoming edges
        List<CS16Edge<String>> actualEdges2 = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it2 = _dirGraph.incomingEdges(A);
        while (it2.hasNext()) {
            actualEdges2.add(it2.next());
        }

        assertThat(actualEdges2.contains(ba), is(true));
        assertThat(actualEdges2.contains(ab), is(false));
    }

    /**
     * null input vertex to incomingEdges
     */
    @Test (expected = InvalidVertexException.class)
    public void testIncomingEdgesException() {
        _graph.incomingEdges(null);
    }

    /**
     * A simple test for outgoingEdges that adds 3 vertices, adds two edges to the
     * graph and then asserts that outgoing edges to A contain one but not the other
     */
    @Test(timeout = 10000)
    public void testOutgoingEdges() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // add edges.
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);

        // use the edge iterator to get a list of the incoming edges
        List<CS16Edge<String>> actualEdges2 = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it2 = _graph.outgoingEdges(A);
        while (it2.hasNext()) {
            actualEdges2.add(it2.next());
        }

        assertThat(actualEdges2.contains(ab), is(true));
        assertThat(actualEdges2.contains(bc), is(false));
    }

    // Same test as above, but with a directed graph
    @Test(timeout = 10000)
    public void testOutgoingEdgesDirected() {
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        // use the edge iterator to get a list of the edges in the actual graph.
        CS16Edge<String> ba = _dirGraph.insertEdge(B, A, 1);
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 2);

        // use the edge iterator to get a list of the incoming edges
        List<CS16Edge<String>> actualEdges2 = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it2 = _dirGraph.outgoingEdges(A);
        while (it2.hasNext()) {
            actualEdges2.add(it2.next());
        }

        assertThat(actualEdges2.contains(ba), is(false));
        assertThat(actualEdges2.contains(ab), is(true));
    }

    /**
     * null input vertex to outgoingEdges
     */
    @Test (expected = InvalidVertexException.class)
    public void testOutgoingEdgesException() {
        _graph.outgoingEdges(null);
    }

    /**
     * A simple test for numVertices, counting the number of vertices in the graph
     */
    @Test(timeout = 10000)
    public void testNumVertices() {
        // insert vertices
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // assert that the graph state is consistent with what you expect
        assertThat(_graph.getNumVertices(), is(3));
    }

    /**
     * A simple test for vertices()
     */
    @Test(timeout = 10000)
    public void testVertices() {
        // insert vertices
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // use the vertex iterator to get a list of the vertices in the actual graph
        List<CS16Vertex<String>> actualVertices = new ArrayList<CS16Vertex<String>>();
        Iterator<CS16Vertex<String>> it = _graph.vertices();
        while (it.hasNext()) {
            actualVertices.add(it.next());
        }

        // assert that the graph state is consistent with what you expect
        assertThat(actualVertices.size(), is(3));
    }

    // Same test as above, but with a directed graph
    @Test(timeout = 10000)
    public void testVerticesDirected() {
        // insert vertices
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        // use the vertex iterator to get a list of the vertices in the actual graph
        List<CS16Vertex<String>> actualVertices = new ArrayList<CS16Vertex<String>>();
        Iterator<CS16Vertex<String>> it = _dirGraph.vertices();
        while (it.hasNext()) {
            actualVertices.add(it.next());
        }

        // assert that the graph state is consistent with what you expect
        assertThat(actualVertices.size(), is(3));
        assertThat(actualVertices.contains(A), is(true));
        assertThat(actualVertices.contains(B), is(true));
        assertThat(actualVertices.contains(C), is(true));
    }

    /**
     * A simple test for edges()
     */
    @Test(timeout = 10000)
    public void testEdges() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // add edges.
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);

        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it = _graph.edges();
        while (it.hasNext()) {
            actualEdges.add(it.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges.size(), is(2));
        assertThat(actualEdges.contains(ab), is(true));
        assertThat(actualEdges.contains(bc), is(true));
    }

    // Same test as above, but with a directed graph
    @Test(timeout = 10000)
    public void testEdgesDirected() {
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        // use the edge iterator to get a list of the edges in the actual graph.
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);

        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it = _dirGraph.edges();
        while (it.hasNext()) {
            actualEdges.add(it.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges.size(), is(2));
        assertThat(actualEdges.contains(ab), is(true));
        assertThat(actualEdges.contains(bc), is(true));
    }

    /**
     *  A simple test for endVertices that adds 3 vertices, adds two edges to the
     *  graph and then asserts that the vertices at the end are a and b
     */
    @Test(timeout = 10000)
    public void testEndVertices() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // add edges.
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);

        // use the endVertices to get vertex's at end of ab
        List<CS16Vertex<String>> actualEdges = _graph.endVertices(ab);

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges.size(), is(2));
        assertThat(actualEdges.contains(A), is(true));
        assertThat(actualEdges.contains(B), is(true));
        assertThat(actualEdges.contains(C), is(false));
    }

    /**
     * null input vertex to endVertices
     */
    @Test (expected = InvalidEdgeException.class)
    public void testEndVerticesException() {
        _graph.endVertices(null);
    }

    /**
     * A simple test for connectingEdge that adds 3 vertices, adds two edges to the
     * graph and then asserts that both edges are in fact between the specified vertices
     */
    @Test(timeout = 10000)
    public void testConnectingEdge() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // add edges.
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);

        // assert that the graph state is consistent with what you expect.
        assertThat(_graph.connectingEdge(A, B), is(ab));
        assertThat(_graph.connectingEdge(B, A), is(ab));
        assertThat(_graph.connectingEdge(B, C), is(bc));
        assertThat(_graph.connectingEdge(C, B), is(bc));
    }

    // Same test as above, but with a directed graph
    @Test(timeout = 10000)
    public void testConnectingEdgeDirected() {
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        // use the edge iterator to get a list of the edges in the actual graph.
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);

        // assert that the graph state is consistent with what you expect.
        assertThat(_dirGraph.connectingEdge(A, B), is(ab));
        assertThat(_dirGraph.connectingEdge(B, C), is(bc));
    }

    /**
     * null input vertex to connectingEdge
     */
    @Test (expected = InvalidVertexException.class)
    public void testConnectingEdgeNullException() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // use the edge iterator to get a list of the edges in the actual graph.
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);


        _graph.connectingEdge(null, null);
        _graph.connectingEdge(A, null);
        _graph.connectingEdge(null, A);
    }

    /**
     * no edge between the two vertices because no edge exists and also when not in the right direction
     */
    @Test (expected = NoSuchEdgeException.class)
    public void testConnectingEdgeNoSuchEdgeException() {
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        // use the edge iterator to get a list of the edges in the actual graph.
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);
        _dirGraph.connectingEdge(B, A); // no edge in correct direction
        _dirGraph.connectingEdge(A, C);
    }

    /**
     * A simple test for areAdjacent that adds 3 vertices, adds two edges to the
     * graph and then asserts that both vertices are adjacent to one another
     */
    @Test(timeout = 10000)
    public void testAreAdjacent() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // add edges.
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);

        // assert that the graph state is consistent with what you expect.
        assertThat(_graph.areAdjacent(A, B), is(true));
        assertThat(_graph.areAdjacent(C, B), is(true));
        assertThat(_graph.areAdjacent(A, C), is(false));
    }

    // Same test as above, but with a directed graph
    @Test(timeout = 10000)
    public void testAreAdjacentDirected() {
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        // use the edge iterator to get a list of the edges in the actual graph.
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);

        // assert that the graph state is consistent with what you expect.
        assertThat(_dirGraph.areAdjacent(A, B), is(true));
        assertThat(_dirGraph.areAdjacent(B, A), is(false));
        assertThat(_dirGraph.areAdjacent(C, B), is(false));
        assertThat(_dirGraph.areAdjacent(B, C), is(true));
        assertThat(_dirGraph.areAdjacent(A, C), is(false));
    }

    /**
     * null input vertex to areAdjacent
     */
    @Test (expected = InvalidVertexException.class)
    public void testAreAdjacentNullException() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // use the edge iterator to get a list of the edges in the actual graph.
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);


        _graph.areAdjacent(null, null);
        _graph.areAdjacent(A, null);
        _graph.areAdjacent(null, A);
    }

    /**
     * A simple test for insertEdges that adds 3 vertices, adds two edges to the
     * graph and then asserts that correct vertex opposite of vertex and edge is returned
     */
    @Test(timeout = 10000)
    public void testOpposite() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // add edges.
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);

        // assert that the graph state is consistent with what you expect.
        assertThat(_graph.opposite(A, ab), is(B));
        assertThat(_graph.opposite(B, ab), is(A));
    }

    // Same test as above, but with a directed graph
    @Test(timeout = 10000)
    public void testOppositeDirected() {
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        // use the edge iterator to get a list of the edges in the actual graph.
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);

        // assert that the graph state is consistent with what you expect.
        assertThat(_dirGraph.opposite(A, ab), is(B));
        assertThat(_dirGraph.opposite(B, ab), is(A));
    }

    /**
     * null input vertex to opposite()
     */
    @Test (expected = InvalidVertexException.class)
    public void testOppositeNullVertexException() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // use the edge iterator to get a list of the edges in the actual graph.
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);

        _graph.opposite(null, null);
        _graph.opposite(null, ab);
    }

    /**
     * null input edge to opposite()
     */
    @Test (expected = InvalidEdgeException.class)
    public void testOppositeNullEdgeException() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        _graph.opposite(A, null);
    }

    /**
     * no such vertex opposite of specified edge and vertex
     */
    @Test (expected = NoSuchVertexException.class)
    public void testOppositeNoSuchVertexException() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // use the edge iterator to get a list of the edges in the actual graph.
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);

        _graph.opposite(C, ab);
    }

    /**
     * A simple test for insertEdges that adds 3 vertices, adds two edges to the
     * graph and then asserts that the correct number of outgoing edges is returned
     */
    @Test(timeout = 10000)
    public void testNumOutgoingEdgesDirected() {
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        // use the edge iterator to get a list of the edges in the actual graph.
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);

        // assert that the graph state is consistent with what you expect.
        assertThat(_dirGraph.numOutgoingEdges(A), is(1));
        assertThat(_dirGraph.numOutgoingEdges(B), is(1));
        assertThat(_dirGraph.numOutgoingEdges(C), is(0));
    }

    /**
     * null input vertex to numOutgoingEdges
     */
    @Test (expected = InvalidVertexException.class)
    public void testNumOutgoingNullException() {
        _dirGraph.numOutgoingEdges(null);
    }

    /**
     * test numOutgoing on an undirected graph
     */
    @Test (expected = DirectionException.class)
    public void testNumOutgoingOnUndirected() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        _graph.numOutgoingEdges(A);
    }

    /**
     * A simple test for clear() that adds 3 vertices, adds two edges to the
     * graph and then asserts that clearing it will indeed clear everything
     */
    @Test(timeout = 10000)
    public void testClear() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // add edges.
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);

        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it = _graph.edges();
        while (it.hasNext()) {
            actualEdges.add(it.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges.size(), is(2));
        assertThat(actualEdges.contains(ab), is(true));
        assertThat(actualEdges.contains(bc), is(true));

        _graph.clear();

        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges2 = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it2 = _graph.edges();
        while (it2.hasNext()) {
            actualEdges.add(it2.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges2.size(), is(0));
        assertThat(actualEdges2.contains(ab), is(false));
        assertThat(actualEdges2.contains(bc), is(false));
    }






















        /*
     * List of graphs for testing!
     */
    @Parameters(name = "with graph: {0}")
    public static Collection<String> graphs() {
        List<String> names = new ArrayList<>();
        names.add("graph.AdjacencyMatrixGraph");
        return names;
    }




















































































































    /*
     * ####################################################
     * 
     * DO NOT MODIFY ANYTHING BELOW THIS LINE
     * 
     * ####################################################
     */
	
	
    @SuppressWarnings("unchecked")
    @Before
	public void makeGraph() {
        Class<?> graphClass = null;
        try {
            graphClass = Class.forName(_graphClassName);
            Constructor<?> constructor = graphClass.getConstructors()[0];
            _graph = (Graph<String>) constructor.newInstance(false);
	    _dirGraph = (Graph<String>) constructor.newInstance(true);
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | IllegalArgumentException e) {
            System.err.println("Exception while instantiating Graph class in GraphTest.");
            e.printStackTrace();
        }
	}
	
    public GraphTest(String graphClassName) {
	    this._graphClassName = graphClassName;
	}
}
