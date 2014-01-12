/*************************************************************************
 *  Compilation:  javac PrimMstFibonacciHeapScheme.java
 *  
 *  Execution:    java PrimMstFibonacciHeapScheme filename.txt
 *  
 *  Dependencies: Edge.java Graph.java DepthFirstSearch.java
 *                InputStreamReader.java  
 *                
 *  Computes a minimum spanning tree using Prim's algorithm.
 *  The algorithm uses a Fibonacci Heap scheme.
 *
 *************************************************************************/

/**
 * The class calculates the minimum spanning tree using Fibonacci Heap scheme.
 * 
 * @author balajiiyer
 */

import java.util.LinkedList;

public class PrimMstFibonacciHeapScheme {
	/** Holds the weight of an edge */
	private Edge[] edgeTo; // edgeTo[v] = shortest edge from tree vertex to
	/** Holds the weight of an edge */
	private int[] distTo; 
	/** Holds the seen endpoints */
	private boolean[] seen; // marked[v] = true if v on tree, false otherwise

	/**Holds fibonacci heap object*/
	private FibonacciHeap pq;

	/**Holds heap nodes*/
	private FibonacciHeap.Entry[] nodes;

	/**
	 * Calculates the minimum spanning tree given an undirected graph
	 */
	public PrimMstFibonacciHeapScheme(Graph graph) {
		edgeTo = new Edge[graph.getVerticesCount()];
		distTo = new int[graph.getVerticesCount()];
		seen = new boolean[graph.getVerticesCount()];
		nodes = new FibonacciHeap.Entry[graph.getVerticesCount()];
		pq = new FibonacciHeap();
		for (int vertexCount = 0; vertexCount < graph.getVerticesCount(); vertexCount++) {
			distTo[vertexCount] = Integer.MAX_VALUE;
			nodes[vertexCount] = pq.insert(vertexCount, distTo[vertexCount]);
		}
		for (int vertexCount = 0; vertexCount < graph.getVerticesCount(); vertexCount++)
			if (!seen[vertexCount])
				primFibHeap(graph, vertexCount);
	}

	/**
	 * Run Prim's algorithm in Graph graph, starting from source vertex
	 * 
	 * @param graph
	 * @param source
	 */
	private void primFibHeap(Graph graph, int source) {
		distTo[source] = 0;
		while (!pq.isEmpty()) {
			// Get the lowest weight edge
			int v = (Integer) pq.removeMin();
			visit(graph, v);
		}
	}

	/**
	 * Scan the edges and add to a list.
	 * Mark it as seen
	 * 
	 * @param graph
	 * @param v
	 */
	private void visit(Graph graph, int v) {
		seen[v] = true;
		for (Edge e : graph.adj(v)) {
			int w = e.getEdge(v);
			if (seen[w])
				continue; //Skip if ineligible
			if (e.weight() < distTo[w]) {
				distTo[w] = e.weight();
				edgeTo[w] = e;
				pq.decreaseKey(nodes[w], distTo[w]);
			}
		}
	}

	/**
	 * Return the edges as an Iterable
	 * 
	 * @return mst
	 */
	public Iterable<Edge> edges() {
		LinkedList<Edge> mst = new LinkedList<Edge>();
		for (int v = 0; v < edgeTo.length; v++) {
			Edge e = edgeTo[v];
			if (e != null) {
				mst.add(e);
			}
		}
		return mst;
	}

	/**
	 * Return the total weight of minimum spanning tree
	 * 
	 * @return weight
	 */
	public int weight() {
		int weight = 0;
		for (Edge e : edges())
			weight += e.weight();
		return weight;
	}

	/**
	 * Test Client
	 */
	// public static void main(String[] args) {
	// InputStreamReader in = new InputStreamReader(args[0]);
	// Graph graph = new Graph(in);
	// PrimMstFibonacciHeapScheme mst = new PrimMstFibonacciHeapScheme(graph);
	// for (Edge e : mst.edges()) {
	// System.out.println(e);
	// }
	// System.out.println(mst.weight());
	// }

}
