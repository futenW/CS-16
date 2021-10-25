package graph;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import support.graph.CS16Edge;
import support.graph.CS16Vertex;
import support.graph.Graph;
import support.graph.MinSpanForest;

/**
 * This class tests the functionality of your MSF algorithms on an AdjacencyMatrixGraph
 * with a 'String' type parameter for the vertices. Edge elements are Integers.
 * The general framework of a test case is: - Name the test method descriptively,
 * mentioning what is  being tested (it is ok to have slightly verbose method names here)
 * Set-up the program state (ex: instantiate a heap and insert K,V pairs into it) - Use
 * assertions to validate that the program is in the state you expect it to be
 * See header comments over tests for what each test does
 * 
 * Before each test is run, you can assume that the '_graph' variable is reset to
 * a new instance of the a Graph<String> that you can simply use 'as is', as
 * well as the '_msf' variable.
 *
 * Of course, please do not modify anything below the 'DO NOT MODIFY ANYTHING BELOW THIS LINE'
 * line, or the above assumptions may be broken.
 */
@RunWith(Parameterized.class)
public class MsfTest {

    private String _msfClassName;
    private MinSpanForest<String> _msf;
    private Graph<String> _graph; // die graphe

    /**
     * test on a simple three node graph to ensure everything works as expected
     */
    @Test
    public void simpleTest() {
        CS16Vertex<String> A = _graph.insertVertex("A"); // w/ three vertices
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 1);
        CS16Edge<String> ca = _graph.insertEdge(A, C, 10); // one between each
        Collection<CS16Edge<String>> MSF = _msf.genMinSpanForest(_graph, null);

        assertThat(MSF.size(), is(2));
        assertThat(MSF.contains(ab), is(true));
        assertThat(MSF.contains(bc), is(true));
        assertThat(MSF.contains(ca), is(false));
    }

    /**
     * test on an empty graph
     */
    @Test
    public void testEmptyGraph() {
        Collection<CS16Edge<String>> MSF = _msf.genMinSpanForest(_graph, null);
        assertThat(MSF.size(), is(0));
    }

    /**
     * test on a graph with only a single node
     */
    @Test
    public void testSingleNodeGraph() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        Collection<CS16Edge<String>> MSF = _msf.genMinSpanForest(_graph, null);
        assertThat(MSF.size(), is(0));
    }

    /**
     * test on a graph with only two nodes and one edge
     */
    @Test
    public void testTwoNodeGraphWithEdge() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Edge<String> ab = _graph.insertEdge(A, B, 10); // only edge

        Collection<CS16Edge<String>> MSF = _msf.genMinSpanForest(_graph, null);

        assertThat(MSF.size(), is(1));
        assertThat(MSF.contains(ab), is(true));
    }

    /**
     * test on a graph with two nodes and no edge
     */
    @Test
    public void testTwoNodeGraphWithoutEdge() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        Collection<CS16Edge<String>> MSF = _msf.genMinSpanForest(_graph, null);

        assertThat(MSF.size(), is(0));
    }

    /**
     * test with four nodes, two edges, one between two nodes and one between the other two nodes
     */
    @Test
    public void testFourNodesForest() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");
        CS16Vertex<String> D = _graph.insertVertex("D");

        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> cd = _graph.insertEdge(C, D, 1);
        Collection<CS16Edge<String>> MSF = _msf.genMinSpanForest(_graph, null);

        assertThat(MSF.size(), is(2));
        assertThat(MSF.contains(ab), is(true)); // contains both
        assertThat(MSF.contains(cd), is(true));
    }

    /**
     * test on an entire forest with three trees, each with a MST
     */
    @Test
    public void testSimpleForest() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 1);
        CS16Edge<String> ca = _graph.insertEdge(A, C, 10); // tree one

        CS16Vertex<String> D = _graph.insertVertex("D");
        CS16Vertex<String> E = _graph.insertVertex("E");
        CS16Vertex<String> F = _graph.insertVertex("F");
        CS16Edge<String> de = _graph.insertEdge(D, E, 1);
        CS16Edge<String> ef = _graph.insertEdge(E, F, 1);
        CS16Edge<String> fd = _graph.insertEdge(F, D, 10); // tree two

        CS16Vertex<String> G = _graph.insertVertex("G");
        CS16Vertex<String> H = _graph.insertVertex("H");
        CS16Vertex<String> I = _graph.insertVertex("I");
        CS16Edge<String> gh = _graph.insertEdge(G, H, 1);
        CS16Edge<String> hi = _graph.insertEdge(H, I, 1);
        CS16Edge<String> ig = _graph.insertEdge(I, G, 10); // tree three

        Collection<CS16Edge<String>> MSF = _msf.genMinSpanForest(_graph, null);

        assertThat(MSF.size(), is(6));
        assertThat(MSF.contains(ab), is(true));
        assertThat(MSF.contains(bc), is(true));
        assertThat(MSF.contains(ca), is(false)); // test tree one

        assertThat(MSF.contains(de), is(true));
        assertThat(MSF.contains(ef), is(true));
        assertThat(MSF.contains(fd), is(false)); // test tree two

        assertThat(MSF.contains(hi), is(true));
        assertThat(MSF.contains(gh), is(true));
        assertThat(MSF.contains(ig), is(false)); // test three three
    }
































    
    /*
     * This is the method that, using junit magic, provides the list of MSF algorithms
     * that should be created and be tested via the methods above.
     * By default, all of the above tests will be run on MyPrimJarnik algorithm implementations.
     * If you're interested in testing the methods on just one of the
     * algorithms, comment out the one you don't want in the method below!
     */
    @Parameters(name = "with msf algo: {0}")
    public static Collection<String> msts() {
        List<String> algoNames = new ArrayList<>();
        algoNames.add("graph.MyPrimJarnik");
        return algoNames;
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
    public void setup() {
        Class<?> msfClass = null;
        try {
            msfClass = Class.forName(_msfClassName);
            Constructor<?> constructor = msfClass.getConstructors()[0];
            _msf = (MinSpanForest<String>) constructor.newInstance();
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException
                | IllegalArgumentException e) {
            System.err.println("Exception while instantiating msf class " + _msfClassName + " from test.");
            e.printStackTrace();
        }
        _graph = new AdjacencyMatrixGraph<>(false);
    }

    public MsfTest(String msfClassName) {
        _msfClassName = msfClassName;
    }
}
