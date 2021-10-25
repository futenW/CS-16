package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import support.graph.CS16Edge;
import support.graph.CS16Vertex;
import support.graph.Graph;
import support.graph.PageRank;

/**
 * In this class you will implement one of many different versions of the PageRank algorithm. This algorithm
 * will only work on directed graphs. Keep in mind that there are many different ways to handle sinks.
 * Make sure you review the help slides and handout for details on the PageRank algorithm.
 */
public class MyPageRank<V> implements PageRank<V> {
	private Graph<V> _g;
	private Map<CS16Vertex<V>, Double> _vertsToRanks; // final mapping

	private static final double _dampingFactor = 0.85; // numerical final consts
	private static final int _maxIterations = 100;
	private static final double _error = 0.01;

	/**
	 * TODO: Feel free to add in anything else necessary to store the information
	 * needed to calculate the rank. Maybe make something to keep track of sinks, your ranks, and your outgoing edges?
	 */
	private List<CS16Vertex<V>> _vertices; // all indexes the same for each array
	private List<Integer> _numOutgoing;
	private List<Double> _prevRank; // of last iteration
	private List<Double> _currRank;

	/**
	 * The main method that does the calculations! You'll want to call the methods
	 * that initialize your variables here. You'll also want to decide on a
	 * type of loop - for loop, do while, or while loop - for your calculations.
	 *
	 * @return A Map of every Vertex to its corresponding rank
	 */
	@Override
	public Map<CS16Vertex<V>, Double> calcPageRank(Graph<V> g) {
		_g = g;
		this.handleSinks(); // modify graph to account for sinks

		// initialize private instance variables
		_vertices = new ArrayList<CS16Vertex<V>>();
		_numOutgoing = new ArrayList<Integer>();
		_prevRank = new ArrayList<Double>();
		_currRank = new ArrayList<Double>();

		// initialize every vertex's pagerank to be 1/N, where N is total number of vertices
		double initialPR = 1.0 / _g.getNumVertices();
		Iterator<CS16Vertex<V>> vertList = _g.vertices();
		while (vertList.hasNext()) {
			CS16Vertex<V> vert = vertList.next();
			_vertices.add(vert);
			_numOutgoing.add(_g.numOutgoingEdges(vert));
			_prevRank.add(0.0);
			_currRank.add(initialPR);
		}

		this.updateRank();

		// create and return vertex to rank mapping
		_vertsToRanks = new HashMap<CS16Vertex<V>, Double>();
		for (int i = 0; i < _vertices.size(); i++) {
			_vertsToRanks.put(_vertices.get(i), _currRank.get(i));
		}
		return _vertsToRanks;
	}

	/**
	 * Method used to account for sink pages (those with no outgoing edges). There are
	 * multiple ways you can implement this, check the lecture and help slides!
	 *
	 * adds edge to every sink connecting it to every node
	 */
	private void handleSinks() { // add edge to every sink to every node
		Iterator<CS16Vertex<V>> vertList = _g.vertices();
		while (vertList.hasNext()) {
			CS16Vertex<V> vert = vertList.next();
			if (_g.numOutgoingEdges(vert) < 1) { // sink
				Iterator<CS16Vertex<V>> vList = _g.vertices(); // iterate through all nodes
				while (vList.hasNext()) {
					CS16Vertex<V> v = vList.next();
					_g.insertEdge(vert, v, 1); // insert edge
				}
			} // sink
		}
	}

	// iterates continuously until max iterations or convergence
	private void updateRank() {
		int count = 0;
		do {
			for (int i = 0; i < _currRank.size(); i++) { // pass off to prev and zero out curr
				_prevRank.set(i, _currRank.get(i));
				_currRank.set(i, 0.0);
			}
			for (int i = 0; i < _vertices.size(); i++) { // for each vertex, calculate & update rank w/ neighbors and rain
				double d = _dampingFactor * _prevRank.get(i);
				double oneMinusD = (1.0 - _dampingFactor) * _prevRank.get(i);
				double shareWithNeighbors = d / _g.numOutgoingEdges(_vertices.get(i));
				double rainOnAll = oneMinusD / _g.getNumVertices();

				for (int j = 0; j < _g.getNumVertices(); j++) { // rain on all vertices
					_currRank.set(j, _currRank.get(j) + rainOnAll);
				}
				Iterator<CS16Edge<V>> neighborList = _g.outgoingEdges(_vertices.get(i)); // push PageRank to neighbors
				while (neighborList.hasNext()) {
					CS16Edge<V> nEdge = neighborList.next();
					CS16Vertex<V> n = _g.opposite(_vertices.get(i), nEdge);
					int neighborIndex = 0;
					for (int k = 0; k < _vertices.size(); k++) { // find index of neighbor n
						if (_vertices.get(k) == n) {
							neighborIndex = k;
						}
					}
					_currRank.set(neighborIndex, _currRank.get(neighborIndex) + shareWithNeighbors);
				}

			}
			count++;
		} while (count < _maxIterations && this.endNow() == false); // while not to max iterations or not converged
	}

	// checks whether convergence has occurred yet, called at end of every iteration of update rank
	private boolean endNow() {
		boolean youShouldTotallyEndNow = true;
		for (int i = 0; i < _vertices.size(); i++) {
			double curr = _currRank.get(i);
			double prev = _prevRank.get(i);
			double absError = Math.abs(curr - prev); // absolute value of error
			if (absError > _error) {
				youShouldTotallyEndNow = false;
			}
		}
		return youShouldTotallyEndNow; // you should totally end now... not
	}
}
