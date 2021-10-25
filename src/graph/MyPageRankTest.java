package graph;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import support.graph.CS16Edge;
import support.graph.CS16Vertex;
import support.graph.Graph;
import java.util.Map;
import java.util.HashMap;

/**
 * This class tests the functionality of your PageRank algorithm on a
 * directed AdjacencyMatrixGraph. The general framework of a test case is:
 * - Name the test method descriptively,
 * - Mention what is being tested (it is ok to have slightly verbose method names here)
 *
 * Some tips to keep in mind when writing test cases:
 * - All pages' ranks should total to 1.
 * - It will be easier to start out by writing test cases on smaller graphs.
 *
 */
public class MyPageRankTest {

	// This is your margin of error for testing
	double _epsilon = 0.03;

	/**
     * A simple test with four pages. Each page only has one
	 * outgoing link to a different page, resulting in a square
	 * shape or cycle when visualized. The pages' total ranks is 1.
     */
	@Test
	public void testFourEqualRanks() {
		Graph<String> adjMatrix = new AdjacencyMatrixGraph<String>(true);
		CS16Vertex<String> a = adjMatrix.insertVertex("A");
		CS16Vertex<String> b = adjMatrix.insertVertex("B");
		CS16Vertex<String> c = adjMatrix.insertVertex("C");
		CS16Vertex<String> d = adjMatrix.insertVertex("D");

		/**
		  * Inserting an edge with a null element since a weighted edge is not
		  * meaningful for the PageRank algorithm.
		  */

		CS16Edge<String> e0 = adjMatrix.insertEdge(a,b,null);
		CS16Edge<String> e1 = adjMatrix.insertEdge(b,c,null);
		CS16Edge<String> e2 = adjMatrix.insertEdge(c,d,null);
		CS16Edge<String> e3 = adjMatrix.insertEdge(d,a,null);

		MyPageRank<String> pr = new MyPageRank<String>();

		Map<CS16Vertex<String>, Double> output = pr.calcPageRank(adjMatrix);

		// Check that the number of vertices returned by PageRank is 4
		assertEquals(output.size(), 4);
		double total = 0;
		for (double rank: output.values()) {
			total += rank;
		}

		// The weights of each vertex should be 0.25
		double expectedRankA = 0.25;
		double expectedRankB = 0.25;
		double expectedRankC = 0.25;
		double expectedRankD = 0.25;

		// The sum of weights should always be 1
		assertEquals(total, 1, _epsilon);

		// The Rank for each vertex should be 0.25 +/- epsilon
		assertEquals(expectedRankA, output.get(a), _epsilon);
		assertEquals(expectedRankB, output.get(b), _epsilon);
		assertEquals(expectedRankC, output.get(c), _epsilon);
		assertEquals(expectedRankD, output.get(d), _epsilon);

	}

	/**
     	 * A simple test with three pages. Note that vertex A's
	 * rank is not 0 even though it has no incoming edges,
	 * demonstrating the effects of the damping factor and handling sinks.
     	 */
	@Test
	public void simpleTestOne() {
		Graph<String> adjMatrix = new AdjacencyMatrixGraph<String>(true);
		CS16Vertex<String> a = adjMatrix.insertVertex("A");
		CS16Vertex<String> b = adjMatrix.insertVertex("B");
		CS16Vertex<String> c = adjMatrix.insertVertex("C");
		CS16Edge<String> e0 = adjMatrix.insertEdge(a,b,null);
		CS16Edge<String> e1 = adjMatrix.insertEdge(b,c,null);

		MyPageRank<String> pr = new MyPageRank<String>();

		Map<CS16Vertex<String>, Double> output = pr.calcPageRank(adjMatrix);

		assertEquals(output.size(), 3);
		double total = 0;
		for (double rank: output.values()) {
			total += rank;
		}

		// These are precomputed values
		double expectedRankA = 0.186;
		double expectedRankB = 0.342;
		double expectedRankC = 0.471;

		assertEquals(total, 1, _epsilon);
		assertEquals(expectedRankA, output.get(a), _epsilon);
		assertEquals(expectedRankB, output.get(b), _epsilon);
		assertEquals(expectedRankC, output.get(c), _epsilon);

	}


	/**
	  * TODO: Add your own tests here. Instead of checking for specific rank values,
	  * make test cases comparing the relative ranks of pages (e.g. using an assertThat statement)!
	  */

	/**
	 * A simple test with zero pages. Should result in an empty set and a total of zero
	 */
	@Test
	public void noNodes() {
		Graph<String> adjMatrix = new AdjacencyMatrixGraph<String>(true);
		MyPageRank<String> pr = new MyPageRank<String>();

		Map<CS16Vertex<String>, Double> output = pr.calcPageRank(adjMatrix);

		assertEquals(output.size(), 0);
		double total = 0;
		for (double rank: output.values()) {
			total += rank;
		}

		assertEquals(total, 0, _epsilon);
	}

	/**
	 * A simple test with a single page. The page should hoard all 1 PageRank. The pages' total ranks is 1.
	 */
	@Test
	public void testOneNode() {
		Graph<String> adjMatrix = new AdjacencyMatrixGraph<String>(true);
		CS16Vertex<String> a = adjMatrix.insertVertex("A");

		MyPageRank<String> pr = new MyPageRank<String>();

		Map<CS16Vertex<String>, Double> output = pr.calcPageRank(adjMatrix);

		// Check that the number of vertices returned by PageRank is 4
		assertEquals(output.size(), 1);
		double total = 0;
		for (double rank: output.values()) {
			total += rank;
		}

		// The weights of each vertex should be 1.0
		double expectedRankA = 1.0;

		// The sum of weights should always be 1
		assertEquals(total, 1, _epsilon);

		// The Rank for each vertex should be 0.25 +/- epsilon
		assertEquals(expectedRankA, output.get(a), _epsilon);
	}

	/**
	 * A simple test with two unconnected pages. Each page only has zero outgoing link to a different page.
	 * The pages' total ranks is 1.
	 */
	@Test
	public void testTwoUnconnectedPages() {
		Graph<String> adjMatrix = new AdjacencyMatrixGraph<String>(true);
		CS16Vertex<String> a = adjMatrix.insertVertex("A");
		CS16Vertex<String> b = adjMatrix.insertVertex("B");

		MyPageRank<String> pr = new MyPageRank<String>();

		Map<CS16Vertex<String>, Double> output = pr.calcPageRank(adjMatrix);

		// Check that the number of vertices returned by PageRank is 4
		assertEquals(output.size(), 2);
		double total = 0;
		for (double rank: output.values()) {
			total += rank;
		}

		// The weights of each vertex should be 0.25
		double expectedRankA = 0.5;
		double expectedRankB = 0.5;

		// The sum of weights should always be 1
		assertEquals(total, 1, _epsilon);

		// The Rank for each vertex should be 0.25 +/- epsilon
		assertEquals(expectedRankA, output.get(a), _epsilon);
		assertEquals(expectedRankB, output.get(b), _epsilon);

		assertEquals(output.get(a), output.get(b), _epsilon); // compare the to one another
	}

	/**
	 * A simple test with two singly connected pages. One page only has one
	 * outgoing link to a different page, and the other page has no outgoing links. The pages' total ranks is 1.
	 */
	@Test
	public void testTwoSinglyConnectedPages() {
		Graph<String> adjMatrix = new AdjacencyMatrixGraph<String>(true);
		CS16Vertex<String> a = adjMatrix.insertVertex("A");
		CS16Vertex<String> b = adjMatrix.insertVertex("B");

		CS16Edge<String> e0 = adjMatrix.insertEdge(a,b,null);

		MyPageRank<String> pr = new MyPageRank<String>();

		Map<CS16Vertex<String>, Double> output = pr.calcPageRank(adjMatrix);

		// Check that the number of vertices returned by PageRank is 4
		assertEquals(output.size(), 2);
		double total = 0;
		for (double rank: output.values()) {
			total += rank;
		}

		// The weights of each vertex should be:
		double expectedRankA = 0.3488;
		double expectedRankB = 0.65119;

		// The sum of weights should always be 1
		assertEquals(total, 1, _epsilon);

		// The Rank for each vertex should be 0.25 +/- epsilon
		assertEquals(expectedRankA, output.get(a), _epsilon);
		assertEquals(expectedRankB, output.get(b), _epsilon);

		assertTrue(output.get(a) < output.get(b)); // compare them to one another
	}

	/**
	 * A simple test with two pages pointing at each other. Each page only has one
	 * outgoing link to a different page. The pages' total ranks is 1.
	 */
	@Test
	public void testTwoDoublyConnectedPages() {
		Graph<String> adjMatrix = new AdjacencyMatrixGraph<String>(true);
		CS16Vertex<String> a = adjMatrix.insertVertex("A");
		CS16Vertex<String> b = adjMatrix.insertVertex("B");

		CS16Edge<String> e0 = adjMatrix.insertEdge(a,b,null);
		CS16Edge<String> e1 = adjMatrix.insertEdge(b,a,null);

		MyPageRank<String> pr = new MyPageRank<String>();

		Map<CS16Vertex<String>, Double> output = pr.calcPageRank(adjMatrix);

		// Check that the number of vertices returned by PageRank is 4
		assertEquals(output.size(), 2);
		double total = 0;
		for (double rank: output.values()) {
			total += rank;
		}

		// The weights of each vertex should be:
		double expectedRankA = 0.5;
		double expectedRankB = 0.5;

		// The sum of weights should always be 1
		assertEquals(total, 1, _epsilon);

		// The Rank for each vertex should be 0.25 +/- epsilon
		assertEquals(expectedRankA, output.get(a), _epsilon);
		assertEquals(expectedRankB, output.get(b), _epsilon);

		assertEquals(output.get(a), output.get(b), _epsilon); // compare them to one another
	}

	/**
	 * A simple test with four pages. Each page only has one outgoing link to a different page, resulting in a square
	 * shape or cycle when visualized. The pages' total ranks is 1.
	 */
	@Test
	public void fourEqualRanks() {
		Graph<String> adjMatrix = new AdjacencyMatrixGraph<String>(true);
		CS16Vertex<String> a = adjMatrix.insertVertex("A");
		CS16Vertex<String> b = adjMatrix.insertVertex("B");
		CS16Vertex<String> c = adjMatrix.insertVertex("C");
		CS16Vertex<String> d = adjMatrix.insertVertex("D");

		/**
		 * Inserting an edge with a null element since a weighted edge is not
		 * meaningful for the PageRank algorithm.
		 */

		CS16Edge<String> e0 = adjMatrix.insertEdge(a,b,null);
		CS16Edge<String> e1 = adjMatrix.insertEdge(b,c,null);
		CS16Edge<String> e2 = adjMatrix.insertEdge(c,d,null);
		CS16Edge<String> e3 = adjMatrix.insertEdge(d,a,null);

		MyPageRank<String> pr = new MyPageRank<String>();

		Map<CS16Vertex<String>, Double> output = pr.calcPageRank(adjMatrix);

		// Check that the number of vertices returned by PageRank is 4
		assertEquals(output.size(), 4);
		double total = 0;
		for (double rank: output.values()) {
			total += rank;
		}

		// The weights of each vertex should be 0.25
		double expectedRankA = 0.25;
		double expectedRankB = 0.25;
		double expectedRankC = 0.25;
		double expectedRankD = 0.25;

		// The sum of weights should always be 1
		assertEquals(total, 1, _epsilon);

		// The Rank for each vertex should be 0.25 +/- epsilon
		assertEquals(expectedRankA, output.get(a), _epsilon);
		assertEquals(expectedRankB, output.get(b), _epsilon);
		assertEquals(expectedRankC, output.get(c), _epsilon);
		assertEquals(expectedRankD, output.get(d), _epsilon);

		assertEquals(output.get(a), output.get(b), _epsilon); // compare them to one another
		assertEquals(output.get(a), output.get(c), _epsilon);
		assertEquals(output.get(a), output.get(d), _epsilon);
		assertEquals(output.get(b), output.get(c), _epsilon);
		assertEquals(output.get(b), output.get(d), _epsilon);
		assertEquals(output.get(c), output.get(d), _epsilon);
	}

	/**
	 * A simple test with three pages. Note that vertex A's rank is not 0 even though it has no incoming edges,
	 * demonstrating the effects of the damping factor and handling sinks.
	 */
	@Test
	public void baiscTest() {
		Graph<String> adjMatrix = new AdjacencyMatrixGraph<String>(true);
		CS16Vertex<String> a = adjMatrix.insertVertex("A");
		CS16Vertex<String> b = adjMatrix.insertVertex("B");
		CS16Vertex<String> c = adjMatrix.insertVertex("C");
		CS16Edge<String> e0 = adjMatrix.insertEdge(a,b,null);
		CS16Edge<String> e1 = adjMatrix.insertEdge(b,c,null);

		MyPageRank<String> pr = new MyPageRank<String>();

		Map<CS16Vertex<String>, Double> output = pr.calcPageRank(adjMatrix);

		assertEquals(output.size(), 3);
		double total = 0;
		for (double rank: output.values()) {
			total += rank;
		}

		// These are precomputed values
		double expectedRankA = 0.186;
		double expectedRankB = 0.342;
		double expectedRankC = 0.471;

		assertEquals(total, 1, _epsilon);
		assertEquals(expectedRankA, output.get(a), _epsilon);
		assertEquals(expectedRankB, output.get(b), _epsilon);
		assertEquals(expectedRankC, output.get(c), _epsilon);

		assertTrue(output.get(a) < output.get(b)); // compare them to one another
		assertTrue(output.get(a) < output.get(c));
		assertTrue(output.get(b) < output.get(c));
	}

	/**
	 * A simple test with all sinks except for one node
	 */
	@Test
	public void almostAllSinks() {
		Graph<String> adjMatrix = new AdjacencyMatrixGraph<String>(true);
		CS16Vertex<String> a = adjMatrix.insertVertex("A");
		CS16Vertex<String> b = adjMatrix.insertVertex("B");
		CS16Vertex<String> c = adjMatrix.insertVertex("C");
		CS16Vertex<String> d = adjMatrix.insertVertex("D");

		CS16Edge<String> e0 = adjMatrix.insertEdge(a,b,null);
		CS16Edge<String> e1 = adjMatrix.insertEdge(a,c,null);
		CS16Edge<String> e2 = adjMatrix.insertEdge(a,d,null);

		MyPageRank<String> pr = new MyPageRank<String>();

		Map<CS16Vertex<String>, Double> output = pr.calcPageRank(adjMatrix);

		// Check that the number of vertices returned by PageRank is 4
		assertEquals(output.size(), 4);
		double total = 0;
		for (double rank: output.values()) {
			total += rank;
		}

		// The weights of each vertex should be 0.25
		double expectedRankA = 0.2058;
		double expectedRankB = 0.25;
		double expectedRankC = 0.25;
		double expectedRankD = 0.25;

		// The sum of weights should always be 1
		assertEquals(total, 1, _epsilon);

		// The Rank for each vertex should be 0.25 +/- epsilon
		assertEquals(expectedRankA, output.get(a), _epsilon);
		assertEquals(expectedRankB, output.get(b), _epsilon);
		assertEquals(expectedRankC, output.get(c), _epsilon);
		assertEquals(expectedRankD, output.get(d), _epsilon);

		assertTrue(output.get(a) < output.get(b)); // compare them to one another
		assertTrue(output.get(a) < output.get(c));
		assertTrue(output.get(a) < output.get(d));
		assertEquals(output.get(b), output.get(c), _epsilon);
		assertEquals(output.get(b), output.get(d), _epsilon);
		assertEquals(output.get(c), output.get(d), _epsilon);

	}

}
