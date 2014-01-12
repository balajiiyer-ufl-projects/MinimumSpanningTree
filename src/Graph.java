/*************************************************************************
 *  Compilation:  javac Graph.java
 
 *  Execution:    java Graph
 *  
 *  Dependencies: Edge.java InputStreamReader.java       
 *                
 *  Creates an undirected graph
 *
 *************************************************************************/

/**	
 * The Graph class generates an undirected graph for given vertices and edges.
 *  
 * @author balajiiyer
 */

import java.util.LinkedList;
import java.util.Random;

public class Graph {

	/** Holds the seed for assigning weight */
	private final int COST = 1000;
	/** Holds the vertex count */
	private final int vertices;
	/** Holds the edge count */
	private int edges;
	/** Holds the visited nodes */
	private boolean[][] nodesPresent;
	/** Holds the adjacency list of vertex and edges */
	private LinkedList<Edge>[] adjList;

	/**
	 * Create an empty graph with "n"(n=1000,3000,5000) vertices
	 */
	@SuppressWarnings("unchecked")
	public Graph(int vertices) {
		if (vertices < 0)
			throw new IllegalArgumentException(
					"Number of vertices in a Graph must be nonnegative");
		this.vertices = vertices;
		this.edges = 0;
		nodesPresent = new boolean[vertices][vertices];
		adjList = (LinkedList<Edge>[]) new LinkedList[vertices];
		for (int vertexCount = 0; vertexCount < vertices; vertexCount++) {
			adjList[vertexCount] = new LinkedList<Edge>();
		}
	}

	/**
	 * Create a random weighted graph with V vertices and E edges. The expected
	 * running time is proportional to V + E.
	 */
	public Graph(int vertices, int edges) {
		this(vertices);
		if (edges < 0)
			throw new IllegalArgumentException(
					"Number of edges in a Graph cannot be negative");
		for (int edgeCount = 0; edgeCount < edges;) {
			Random random = new Random();
			int nodei = random.nextInt(vertices);
			int nodej = random.nextInt(vertices);
			// Avoiding parallel edges
			if (nodei != nodej && !nodesPresent[nodei][nodej]) {
				int weight = random.nextInt(COST) + 1;
				nodesPresent[nodei][nodej] = true;
				nodesPresent[nodej][nodei] = true;
				Edge e = new Edge(nodei, nodej, weight);
				edgeCount++;
				addEdge(e);
			}
		}

	}

	/**
	 * Create a weighted graph from input stream.
	 */
	public Graph(InputStreamReader inputFile) {
		this(inputFile.readInteger());
		int edgeCount = inputFile.readInteger();
		for (int i = 0; i < edgeCount; i++) {
			int nodei = inputFile.readInteger();
			int nodej = inputFile.readInteger();
			int weight = inputFile.readInteger();
			Edge e = new Edge(nodei, nodej, weight);
			addEdge(e);
		}
	}

	/**
	 * Return the number of vertices in this graph.
	 */
	public int getVerticesCount() {
		return vertices;
	}

	/**
	 * Return the number of edges in this graph.
	 */
	public int getEdgesCount() {
		return edges;
	}

	/**
	 * Add the undirected edge e to this graph.
	 */
	public void addEdge(Edge e) {
		int nodei = e.getVertex();
		int nodej = e.getEdge(nodei);
		adjList[nodei].add(e);
		adjList[nodej].add(e);
		edges++;
	}

	/**
	 * Return the edges incident to vertex v as an Iterable
	 */
	public Iterable<Edge> adj(int v) {
		return adjList[v];
	}

	/**
	 * Return all edges in this graph as an Iterable
	 */
	public Iterable<Edge> edges() {
		LinkedList<Edge> list = new LinkedList<Edge>();
		for (int v = 0; v < vertices; v++) {
			for (Edge e : adj(v)) {
				// Ignoring Self loops for MST
				if (e.getEdge(v) > v) {
					list.add(e);
				}
			}
		}
		return list;
	}

	/**
	 * Return a string representation of this graph
	 */
	public String toString() {
		String NEWLINE = System.getProperty("line.separator");
		StringBuilder s = new StringBuilder();
		s.append(vertices + " " + edges + NEWLINE);
		for (int v = 0; v < vertices; v++) {
			s.append(v + ": ");
			for (Edge e : adjList[v]) {
				s.append(e + "  ");
			}
			s.append(NEWLINE);
		}

		return s.toString();
	}

	/**
	 * Test client.
	 */
	// public static void main(String[] args) {
	// InputStreamReader in = new InputStreamReader(args[0]);
	// Graph graph = new Graph(in);
	// System.out.println(graph);
	// }

}
