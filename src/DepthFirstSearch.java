/*************************************************************************
 *  
 * DepthFirstSearch
 * 
 * The DepthFirstSearch class checks whether the graph is connected
 *
 *************************************************************************/

/**
 * The class checks whether the graph is connected
 *
 * @author balajiiyer
 */

public class DepthFirstSearch {

	 /** Holds seen nodes */
	private boolean[] seen;
	 /** Counts the number of edges */
	private int count;
	/** Holds the status of graph*/
	private boolean connected;

	/**
	 * Constructor to initialize the search with graph and node
	 * 
	 * @param graph
	 * @param source
	 */
	public DepthFirstSearch(Graph graph, int source) {
		seen = new boolean[graph.getVerticesCount()];
		depthFirstSearch(graph, source);
	}

	/**
	 * Depth first search from node
	 * 
	 * @param graph
	 * @param source
	 */
	private void depthFirstSearch(Graph graph, int source) {
		count++;
		seen[source] = true;
		for (Edge edge : graph.adj(source)) {
			int w = edge.getEdge(source);
			if (!seen[w]) {
				depthFirstSearch(graph, w);
			}

		}
		if (getCount() != graph.getVerticesCount())
			connected = false;
		else
			connected = true;

	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Check whether the node has been seen or not
	 * 
	 * @param v
	 * @return true or false
	 */
	public boolean hasSeen(int v) {
		return seen[v];
	}

	/**
	 * 
	 * @return the status of the graph:connected or NotConnected
	 */
	public boolean isConnected() {
		return connected;
	}
}
