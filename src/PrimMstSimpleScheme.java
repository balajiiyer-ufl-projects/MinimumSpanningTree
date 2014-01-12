/*************************************************************************
 *  Compilation:  javac PrimMstSimpleScheme.java
 *  
 *  Execution:    java PrimMstSimpleScheme filename.txt
 *  
 *  Dependencies: Edge.java Graph.java DepthFirstSearch.java
 *                InputStreamReader.java  
 *                
 *  Computes a minimum spanning tree using Prim's algorithm.
 *  The algorithm uses a simple scheme.
 *
 *************************************************************************/

/**
 * The class calculates the minimum spanning tree using simple scheme.
 * 
 * @author balajiiyer
 */

import java.util.Iterator;
import java.util.LinkedList;

public class PrimMstSimpleScheme {

	/** Holds the seen endpoints */
	private boolean[] seen;
	/** Holds the edges of minimum spanning tree */
	private LinkedList<Edge> mst;
	/** Holds the crossing edges of graph */
	private LinkedList<Edge> edgeList;

	/**
	 * Calculates the minimum spanning tree given an undirected graph and
	 * arbitrary source
	 */
	public PrimMstSimpleScheme(Graph graph, int source) {
		edgeList = new LinkedList<Edge>();
		seen = new boolean[graph.getVerticesCount()];
		mst = new LinkedList<Edge>();
		visit(graph, source);
		while (!edgeList.isEmpty()) {
			// Get the lowest weight edge
			Edge e = getMin(edgeList);
			int v = e.getVertex(), w = e.getEdge(v);
			if (seen[v] && seen[w])
				continue; // Skip if ineligible.
			mst.add(e);
			if (!seen[v])
				visit(graph, v);
			if (!seen[w])
				visit(graph, w);
		}
	}

	/**
	 * Get the lowest weight edge and remove it from the given list
	 * 
	 * @param edgeList
	 * @return
	 */
	private Edge getMin(LinkedList<Edge> edgeList) {
		Edge minEdge = null;
		int minWeight = Integer.MAX_VALUE;
		Iterator<Edge> edgeIterator = edgeList.iterator();
		while (edgeIterator.hasNext()) {
			Edge edge = (Edge) edgeIterator.next();
			if (edge.weight() < minWeight) {
				minWeight = edge.weight();
				minEdge = edge;
			}
		}
		edgeList.remove(minEdge);
		return minEdge;
	}

	/**
	 * Scan the edges and add to a list.
	 * Mark it as seen
	 * 
	 * @param graph
	 * @param vertex
	 */
	private void visit(Graph graph, int vertex) {
		seen[vertex] = true;
		for (Edge e : graph.adj(vertex))
			if (!seen[e.getEdge(vertex)])
				edgeList.add(e);
	}

	/**
	 * Return the edges as an Iterable
	 * 
	 * @return
	 */
	public Iterable<Edge> edges() {
		return mst;
	}

	/**
	 * Return the total weight of minimum spanning tree
	 * 
	 * @return
	 */
	public int weight() {
		int weight = 0;
		for (Edge e : edges())
			weight += e.weight();
		return weight;

	}

	/**
	 * Test client.
	 */
	// public static void main(String[] args) {
	//
	// InputStreamReader in = new
	// InputStreamReader("/Volumes/Einstein/UFL/Fall 2013/ADS/project/mst.txt");
	// Graph graph = new Graph(in);
	// PrimMstSimpleScheme mst = new PrimMstSimpleScheme(graph, 0);
	// for (Edge e : mst.edges()) {
	// System.out.println(e);
	// }
	// System.out.println(mst.weight());
	// }

}
