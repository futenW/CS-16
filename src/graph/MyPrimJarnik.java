package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import net.datastructures.Entry;
import support.graph.CS16AdaptableHeapPriorityQueue;
import support.graph.CS16Edge;
import support.graph.CS16GraphVisualizer;
import support.graph.CS16Vertex;
import support.graph.Graph;
import support.graph.MinSpanForest;

/**
 * In this class you will implement a slightly modified version
 * of the Prim-Jarnik algorithm for generating Minimum Spanning trees.
 * The original version of this algorithm will only generate the 
 * minimum spanning tree of the connected vertices in a graph, given
 * a starting vertex. Like Kruskal's, this algorithm can be modified to 
 * produce a minimum spanning forest with very little effort.
 *
 * See the handout for details on Prim-Jarnik's algorithm.
 * Like Kruskal's algorithm this algorithm makes extensive use of 
 * the decorator pattern, so make sure you know it.
 */
public class MyPrimJarnik<V> implements MinSpanForest<V> {

    /** 
     * This method implements Prim-Jarnik's algorithm and extends 
     * it slightly to account for disconnected graphs. You must return 
     * the collection of edges of the Minimum Spanning Forest (MSF) for 
     * the given graph, g.
     * 
     * This algorithm must run in O((|E| + |V|)log(|V|)) time
     * @param g Your graph
     * @param visualizer Only used if you implement the optional animation.
     * @return returns a data structure that contains the edges of your MSF that implements java.util.Collection
     */
    @Override
    public Collection<CS16Edge<V>> genMinSpanForest(Graph<V> g, CS16GraphVisualizer<V> visualizer) {
        ArrayList<CS16Edge<V>> msf = new ArrayList<CS16Edge<V>>();
        MyDecorator lowestCosts = new MyDecorator<CS16Vertex<V>, Integer>(); // keeps track of costs
        MyDecorator prev = new MyDecorator<CS16Vertex<V>, CS16Vertex<V>>();
        MyDecorator entries = new MyDecorator<CS16Vertex<V>, Entry<Integer, CS16Vertex<V>>>(); // keeps track of entries
        MyDecorator inPQ = new MyDecorator<CS16Vertex<V>, Boolean>(); // in PQ (visted yet?)

        // initialize cost and previous for all vertices
        Iterator<CS16Vertex<V>> verticesList = g.vertices();
        while (verticesList.hasNext()) {
            CS16Vertex<V> vert = verticesList.next();
            lowestCosts.setDecoration(vert, Integer.MAX_VALUE); // set to infiniteee
            prev.setDecoration(vert, null);
            inPQ.setDecoration(vert, true);
        }

        // add all to priority queue
        CS16AdaptableHeapPriorityQueue<Integer, CS16Vertex<V>> pq = new CS16AdaptableHeapPriorityQueue<Integer, CS16Vertex<V>>();
        verticesList = g.vertices();
        while (verticesList.hasNext()) {
            CS16Vertex<V> vert = verticesList.next();
            Integer cost = (int) lowestCosts.getDecoration(vert);
            Entry<Integer, CS16Vertex<V>> temp = pq.insert(cost, vert); // insert cost as key into PQ
            entries.setDecoration(vert, temp);
        }

        while (pq.isEmpty() == false) {
            CS16Vertex<V> min = pq.removeMin().getValue(); // remove min from PQ
            inPQ.setDecoration(min, false);

            if (prev.getDecoration(min) != null) {
                msf.add(g.connectingEdge(min, (CS16Vertex<V>) prev.getDecoration(min))); // add to msf
            }
            Iterator<CS16Edge<V>> incidents = g.outgoingEdges(min);
            while (incidents.hasNext()) {
                CS16Edge<V> thisEdge = incidents.next();
                CS16Vertex<V> u = g.opposite(min, thisEdge);

                if ((boolean) inPQ.getDecoration(u) == true) { // if still unvisited
                    if (thisEdge.element() < (int) lowestCosts.getDecoration(u)) {
                        lowestCosts.setDecoration(u, thisEdge.element());
                        prev.setDecoration(u, min);
                        pq.replaceKey((Entry<Integer, CS16Vertex<V>>) entries.getDecoration(u), thisEdge.element());
                    }
                }
            }
        }

        return msf;
    }
}
