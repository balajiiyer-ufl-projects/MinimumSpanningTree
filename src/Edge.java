/*************************************************************************
 *  Compilation:  javac Edge.java
 *  Execution:    java Edge
 *
 *  Weighted edge
 *
 *************************************************************************/

/**
 * The Edge class represents an undirected graph with a weighted edge.
 * 
 * @author balajiiyer
 */

public class Edge implements Comparable<Edge> {
	
	/** Holds one endpoint of an edge */
	private int nodei;
	/** Holds other endpoint of an edge */
	private int nodej;
	/** Holds weight of an edge */
	private int weight;

	/**
	 * Create an edge with given weight.
	 */
	public Edge(int nodei, int nodej, int weight) {
		this.nodei = nodei;
		this.nodej = nodej;
		this.weight = weight;
	}

	/**
	 * Return the weight of this edge.
	 */
	public int weight() {
		return weight;
	}

	/**
	 * Return vertex of this edge.
	 */
	public int getVertex() {
		return nodei;
	}

	/**
	 * Return the endpoint of this edge that is different from the given vertex
	 */
	public int getEdge(int vertex) {
		if (vertex == nodei)
			return nodej;
		else if (vertex == nodej)
			return nodei;
		else
			throw new IllegalArgumentException("Illegal node specified");
	}

	/**
	 * Compare edges by weight.
	 */
	public int compareTo(Edge other) {
		if (this.weight() < other.weight())
			return -1;
		else if (this.weight() > other.weight())
			return +1;
		else
			return 0;
	}

	/**
	 * Return a string representation of this edge.
	 */
	public String toString() {
		return String.format("%d %d", nodei, nodej);
	}

	/**
	 * Test client.
	 */
	// public static void main(String[] args) {
	// Edge e = new Edge(12, 23, 3);
	// System.out.println(e);
	// }
}
