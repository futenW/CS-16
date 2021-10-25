package graph;

import static support.graph.Constants.MAX_VERTICES;

import java.util.*;

import net.datastructures.NodeQueue;
import support.graph.CS16Edge;
import support.graph.CS16Vertex;
import support.graph.Graph;
import support.graph.GraphEdge;
import support.graph.GraphVertex;
import support.graph.DirectionException;
import support.graph.InvalidEdgeException;
import support.graph.InvalidVertexException;
import support.graph.NoSuchEdgeException;
import support.graph.NoSuchVertexException;
import support.graph.NodeSequence;
    
/**
 * This class defines a Graph that tracks its edges through the use of an
 * adjacency matrix. Please review the lecture slides and the book before
 * attempting to write this program. An adjacency matrix consists of a 2D array
 * of Vertices, with each vertex of the graph appearing in both dimensions.
 *
 * Since we are using an adjacency matrix, each vertex must have a 'number', so
 * that it can represent an index in one of the dimensional arrays. This
 * assignment is not as trivial as it may appear. Remember that your arrays have
 * a maximum index. Thus, you cannot just up the number for each vertex. Why
 * not? Think about what happens when you constantly add and delete new
 * vertices. You will soon exceed the size of your adjacency matrix array. Note
 * further that this number must be unique.
 * 
 * Make sure your AdjacencyMatrixGraph can be both directed and undirected!
 *
 * Good luck, and as always, start early, start today, start yesterday!
 */
public class AdjacencyMatrixGraph<V> implements Graph<V> {

    private CS16Edge<V>[][] _adjMatrix; // The underlying data structure of your graph: the adjacency matrix

    // Sets to store the vertices and edges of your graph
    private Set<CS16Vertex<V>> _vertices;
    private Set<CS16Edge<V>> _edges;

    private boolean _directed; // boolean that keeps track of directed-ness of graph
    private int _numVertices; // number of vertices

    private NodeQueue<Integer> _queue;

    /**
     * Constructor for your Graph, where among other things, you will most
     * likely want to instantiate your matrix array and your Sets.
     *
     * Takes in a boolean that represents whether the graph will be directed.
     * This must run in O(1) time.
     */
    public AdjacencyMatrixGraph(boolean directed) {
        _adjMatrix = this.makeEmptyEdgeArray();

        _directed = directed; // initialize instance vars
        _numVertices = 0;

        _vertices = new HashSet<CS16Vertex<V>>();
        _edges = new HashSet<CS16Edge<V>>();

        _queue = new NodeQueue<Integer>(); // push all into queue
        for (int i = 0; i < MAX_VERTICES; i++) { // Ed Post # 1730: assume initialization is constant
            _queue.enqueue(i);
        }
    }

    /**
     * Inserts a new Vertex into your Graph. You will want to first generate a
     * unique number for your vertex that falls within the range of your
     * adjacency array. You will then have to add the Vertex to your set of
     * vertices.
     *
     * You will not have to worry about the case where *more* than MAX_VERTICES
     * vertices are in your graph. Your code should, however, be able to hold
     * MAX_VERTICES vertices at any time.
     * This must run in O(1) time.
     *
     * @param vertElement the element to be added to the graph as a vertex
     */
    @Override
    public CS16Vertex<V> insertVertex(V vertElement) {
        CS16Vertex newVertex = new GraphVertex(vertElement);

        int index = _queue.dequeue(); // get unique number
        newVertex.setVertexNumber(index);

        _vertices.add(newVertex);

        _numVertices++; // increment
        return newVertex;
    }

    /**
     * Inserts a new Edge into your Graph. You need to update your adjacency
     * matrix to reflect this new added Edge. In addition, the Edge needs to be
     * added to the edge set.
     *
     * If the graph is directed, you will only want an edge
     * starting from the first vertex ending at the second vertex. If the graph is
     * undirected, you will want an edge both ways.
     * This must run in O(1) time.
     *
     * @param v1 The first vertex of the edge connection.
     * @param v2 The second vertex of the edge connection.
     * @param edgeElement The element of the newly inserted edge.
     * @return Returns the newly inserted Edge.
     * @throws InvalidVertexException Thrown when either Vertex is null.
     */
    @Override
    public CS16Edge<V> insertEdge(CS16Vertex<V> v1, CS16Vertex<V> v2, Integer edgeElement) throws InvalidVertexException {
        if (v1 == null || v2 == null) {
            throw new InvalidVertexException("vertex is null :(");
        }

        CS16Edge newEdge = new GraphEdge(edgeElement); // create new edge element

        if (_directed == false) {
            int one = v1.getVertexNumber();
            int two = v2.getVertexNumber();
            _adjMatrix[one][two] = newEdge;
            _adjMatrix[two][one] = newEdge;
            newEdge.setVertexOne(v1);
            newEdge.setVertexTwo(v2);
        } else { // _directed == True
            int from = v1.getVertexNumber();
            int to = v2.getVertexNumber(); // _directed == True
            _adjMatrix[from][to] = newEdge;
            newEdge.setVertexOne(v1);
            newEdge.setVertexTwo(v2);
        }

        _edges.add(newEdge);

        return newEdge;
    }

    /**
     * Removes an Edge from your Graph. You will want to remove all references
     * to it from your adjacency matrix. Don't forget to remove it from the edge
     * set. Make sure to remove only the correct edge if the graph is directed.
     * This must run in O(1) time.
     *
     * @param edge The Edge to remove.
     * @return The element of the removed Edge.
     * @throws InvalidEdgeException Thrown when the Edge is null.
     */
    @Override
    public Integer removeEdge(CS16Edge<V> edge) throws InvalidEdgeException {
        if (edge == null) {
            throw new InvalidEdgeException("edge is null :(");
        }
        if (_directed == false) {
            int one = edge.getVertexOne().getVertexNumber();
            int two = edge.getVertexTwo().getVertexNumber();
            _adjMatrix[one][two] = null; // for both
            _adjMatrix[two][one] = null;
        } else { // _directed == true
            int from = edge.getVertexOne().getVertexNumber();
            int to = edge.getVertexTwo().getVertexNumber(); // _directed == true
            _adjMatrix[from][to] = null;
        }
        _edges.remove(edge);
        return edge.element();
    }

    /**
     * Removes a Vertex from your graph. You will first have to remove all edges
     * that are connected to this Vertex. (Perhaps you can use other methods you
     * will eventually write to make this easier?) Finally, remove the Vertex
     * from the vertex set.
     * This must run in O(|V|) time.
     *
     * @param vert The Vertex to remove.
     * @return The element of the removed Vertex.
     * @throws InvalidVertexException Thrown when the Vertex is null.
     */
    @Override
    public V removeVertex(CS16Vertex<V> vert) throws InvalidVertexException {
        if (vert == null) {
            throw new InvalidVertexException("vertex is null :(");
        }

        if (_directed == false) {
            Iterator<CS16Edge<V>> edgesList = this.outgoingEdges(vert); // if undirected
            while (edgesList.hasNext()) {
                CS16Edge<V> edge = edgesList.next();
                int one = edge.getVertexOne().getVertexNumber();
                int two = edge.getVertexTwo().getVertexNumber();
                _adjMatrix[one][two] = null;
                _adjMatrix[two][one] = null;
                _edges.remove(edge);
            }
        } else { // _directed == true
            Iterator<CS16Edge<V>> outgoingEdges = this.outgoingEdges(vert); // _directed == True
            while (outgoingEdges.hasNext()) {
                CS16Edge<V> edge = outgoingEdges.next();
                int from = edge.getVertexOne().getVertexNumber();
                int to = edge.getVertexTwo().getVertexNumber();
                _adjMatrix[from][to] = null;
                _edges.remove(edge);
            } // iterate while has next

            Iterator<CS16Edge<V>> incomingEdges = this.incomingEdges(vert); // iterate over everything
            while (incomingEdges.hasNext()) {
                CS16Edge<V> edge = incomingEdges.next();
                int from = edge.getVertexOne().getVertexNumber();
                int to = edge.getVertexTwo().getVertexNumber();
                _adjMatrix[from][to] = null;
                _edges.remove(edge);
            }
        }

        _vertices.remove(vert);
        _queue.enqueue(vert.getVertexNumber());
        _numVertices--;
        return vert.element();
    }

    ///////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////      Helper Methods        //////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////

    /**
     * Returns an Iterator over all the Edges that are incoming to this Vertex.
     * This must run in O(|V|) time;
     *
     * @param vert The vertex to find the incoming edges on.
     * @return Returns an Iterator holding the incoming edges on v.
     * @throws InvalidVertexException Thrown when the Vertex is null.
     */
    @Override
    public Iterator<CS16Edge<V>> incomingEdges(CS16Vertex<V> vert) throws InvalidVertexException {
        if (vert == null) {
            throw new InvalidVertexException("vertex is null :("); // handle exception
        }

        Set<CS16Edge<V>> incomingEdges = new HashSet<CS16Edge<V>>();
        for (int i = 0; i < _adjMatrix.length; i++) {
            if (_adjMatrix[i][vert.getVertexNumber()] != null) {
                incomingEdges.add(_adjMatrix[i][vert.getVertexNumber()]); // add to list
            }
        }
        return incomingEdges.iterator();
    }

    /**
     * Returns an Iterator of all the Edges that are outgoing from this vertex.
     * This must run in O(|V|) time;

     * @param vert The vertex to find the outgoing edges on.
     * @return Returns an Iterator holding the outgoing edges on v.
     * @throws InvalidVertexException Thrown when the Vertex is null.
     */
    @Override
    public Iterator<CS16Edge<V>> outgoingEdges(CS16Vertex vert) throws InvalidVertexException {
        if (vert == null) {
            throw new InvalidVertexException("vertex is null :("); // handle null input
        }
        Set<CS16Edge<V>> outgoingEdges = new HashSet<CS16Edge<V>>();
        for (int i = 0; i < _adjMatrix[0].length; i++) {
            if (_adjMatrix[vert.getVertexNumber()][i] != null) {
                outgoingEdges.add(_adjMatrix[vert.getVertexNumber()][i]); // add to list
            }
        }
        return outgoingEdges.iterator();
    }

    /**
     * Returns the number of vertices in the graph.
     */
    @Override
	public int getNumVertices() {
		return _numVertices;
	}

    /**
     * Returns an iterator holding all the Vertices of the graph. This must run in O(1) time.
     * Note that the visualizer uses this method to display the graph's vertices, so you should implement it first.
     * @return an Iterator containing the vertices of the Graph.
     */
    @Override
    public Iterator<CS16Vertex<V>> vertices() {
        return _vertices.iterator();
    }

    /**
     * Returns an iterator holding all the edges of the graph. This must run in O(|1|) time.
     * Note that the visualizer uses this method to display the graph's edges, so you should implement it first.
     * @return an Iterator containing the edges of the Graph.
     */
    @Override
    public Iterator<CS16Edge<V>> edges() {
        return _edges.iterator();
    }

    /**
     * Returns the two Vertices that the Edge e is connected to.
     * Checking the adjacency matrix may be too costly for this method.
     * Note that the visualizer uses this method to display the graph's edges.
     * This must run in O(1) time.
     *
     * @param e The edge to find the connecting Vertex's on.
     * @return a list of Vertex's holding the two connecting vertices.
     * @throws InvalidEdgeException Thrown when the Edge e is null.
     */
    @Override
    public List<CS16Vertex<V>> endVertices(CS16Edge<V> e) throws InvalidEdgeException {
        if (e == null) {
            throw new InvalidEdgeException("edge is null :("); // handle null input edge
        }
        List<CS16Vertex<V>> list = new ArrayList<CS16Vertex<V>>(); // create new arrayListf
        list.add(e.getVertexOne());
        list.add(e.getVertexTwo());
        return list;
    }

    /**
     * Returns the edge that connects the two vertices. You will want to consult your adjacency matrix to see if they are connected.
     * If so, return that edge, otherwise throw a NoSuchEdgeException.
     *
     * If the graph is directed, then two nodes are connected if there is an edge from the first vertex to the second.
     * If the graph is undirected, then two nodes are connected if there is an edge from the first vertex to the second and vice versa.
     * This must run in O(1) time.
     *
     * @param v1 The first vertex that may be connected.
     * @param v2 The second vertex that may be connected.
     * @return The edge that connects the first and second vertices.
     * @throws InvalidVertexException Thrown when either vertex is null.
     * @throws NoSuchEdgeException Thrown when no edge connects the vertices.
     */
    @Override
    public CS16Edge<V> connectingEdge(CS16Vertex<V> v1, CS16Vertex<V> v2) throws InvalidVertexException, NoSuchEdgeException {
        if (v1 == null || v2 == null) {
            throw new InvalidVertexException("vertex is null :("); // exception handler
        }
        if (_adjMatrix[v1.getVertexNumber()][v2.getVertexNumber()] == null) {
            throw new NoSuchEdgeException("no such edge between these vertices :("); // exception handler
        }
        return _adjMatrix[v1.getVertexNumber()][v2.getVertexNumber()];
    }

    /**
     * Returns true if there exists an Edge that starts from Vertex v1 and ends
     * at Vertex v2 for both a directed and undirected graph. For a directed graph
     * two vertices are adjacent if there is an edge from the first vertex to the
     * second vertex.
     * This must run in O(1) time.
     *
     * @param v1 The first Vertex to test adjacency.
     * @param v2 The second Vertex to test adjacency.
     * @return Returns true if the vertices are adjacent.
     * @throws InvalidVertexException Thrown if either vertex is null.
     */
    @Override
    public boolean areAdjacent(CS16Vertex<V> v1, CS16Vertex<V> v2) throws InvalidVertexException {
        if (v1 == null || v2 == null) {
            throw new InvalidVertexException("vertex is null :("); // null v1 or null v2
        }
        return _adjMatrix[v1.getVertexNumber()][v2.getVertexNumber()] != null;
    }

    /**
     * Returns the Vertex that is on the other side of Edge e opposite of Vertex v. Consulting the
     * adjacency matrix may result in a running time that is too high.
     *
     * If the edge is not incident on v, then throw a NoSuchVertexException.
     * This must run in O(1) time.
     *
     * @param vert The first vertex on Edge e.
     * @param edge The edge connecting Vertex v and the unknown opposite Vertex.
     * @return The opposite Vertex of v across Edge e.
     * @throws InvalidVertexException Thrown when the Vertex is not valid.
     * @throws InvalidEdgeException Thrown when the Edge is not valid.
     * @throws NoSuchVertexException Thrown when Edge e is not incident on v.
     */
    @Override
    public CS16Vertex<V> opposite(CS16Vertex<V> vert, CS16Edge<V> edge) throws InvalidVertexException, InvalidEdgeException, NoSuchVertexException {
        if (vert == null) {
            throw new InvalidVertexException("vertex is null :("); // null vertice
        }
        if (edge == null) {
            throw new InvalidEdgeException("edge is null :("); // null edge
        }
        if (!vert.equals(edge.getVertexOne()) && !vert.equals(edge.getVertexTwo())) {
            throw new NoSuchVertexException("edge is not incident on vertex :(");
        }

        if (vert.equals(edge.getVertexTwo())) { // return one
            return edge.getVertexOne();
        } else {
            return edge.getVertexTwo(); // or the other
        }
    }

    /**
     * Returns an int of the number Edges that are leaving from this Vertex. This should only
     * work if called on a directed graph. This method will be used in MyPageRank.
     *
     * @param vert The vertex to to find the outgoing edges on.
     * @return an int
     * @throws InvalidVertexException Thrown when the Vertex is not valid.
     * @throws DirectionException Thrown when this method is called on an undirected graph.
     */
    @Override
    public int numOutgoingEdges(CS16Vertex<V> vert) throws InvalidVertexException, DirectionException {
        if (vert == null) {
            throw new InvalidVertexException("vertex is null :("); // null vertex
        }
        if (_directed == false) {
            throw new DirectionException("graph is undirected :("); // undirected graph
        }

        int count = 0;
        for (int i = 0; i < _adjMatrix[0].length; i++) {
            if (_adjMatrix[vert.getVertexNumber()][i] != null) { // there is an edge here
                count++;
            }
        }
        return count;
    }

    /**
     * Clears all the vertices and edges from the graph. You will want to also clear the adjacency matrix. Remember
     * the power of Java's garbage collection mechanism. If you re-instantiate something, the instance of what that
     * Object used to be disappears.
     * This must run in O(1) time.
     */
    @Override
    public void clear() {
        _adjMatrix = this.makeEmptyEdgeArray();
        _numVertices = 0;

        _vertices = new HashSet<CS16Vertex<V>>(); // new hashSet of vertices
        _edges = new HashSet<CS16Edge<V>>();

        _queue = new NodeQueue<Integer>();
        for (int i = 0; i < MAX_VERTICES; i++) { // Ed Post # 1730: assume initialization is constant
            _queue.enqueue(i);
        }
    }

    /**
     * Toggles the directedness of the graph.
     */
    @Override
    public void toggleDirected() {
        _directed = !_directed;
    }


































    // Do not change this method!
    @SuppressWarnings("unchecked")
    private CS16Edge<V>[][] makeEmptyEdgeArray() {
        return new CS16Edge[MAX_VERTICES][MAX_VERTICES];
    }
}
