/*********************************************************************************
 *  Compilation:  javac -classpath . *.java
 *     
 *  Execution:    1. Random mode  			    	 : java mst -r -n -d 
 *				  2. MST using Simple Scheme		 : java mst -s filename.txt
 *				  3. MST using Fibonacci Heap Scheme : java mst -f filename.txt		
 *  
 *  Dependencies: Edge.java Graph.java DepthFirstSearch.java
 *                InputStreamReader.java FibonacciHeap.java 
 *                PrimMstSimpleScheme.java PrimMstFibonacciHeapScheme.java
 *                
 *  Test Client for graph creation and to display minimum spanning tree using 
 *  1. Simple scheme
 *  2. Fibonacci Heap scheme
 *
 *********************************************************************************/

/**
 * The mst class represents a test client for graph creation and to display the output of
 * Prim's minimum spanning tree.Supports random mode graph generation and file input mode 
 * containing undirected graph data.
 * Calculates the minimum spanning tree using simple scheme and fibonacci heap scheme.
 * 
 * @author balajiiyer
 */

public class mst {

	/** Holds the arbitrary start index for minimum spanning tree to expand */
	private static final int SOURCE = 0;

	/**
	 * Main function for graph creation and display minimum spanning tree on
	 * standard output
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/** Holds the start time */
		long startTime;
		/** Holds the end time */
		long endTime;
		/** Holds the elapsed time */
		long time;
		/** Object to hold the undirected graph data */
		Graph graph = null;
		/** Holds the status of graph */
		boolean connected = false;

		/**
		 * Calculates minimum spanning tree in random mode.
		 * Accepts vertex count and density
		 * 
		 */
		if (args[0].equals("-r")) {
			try {

				/**
				 * Read in vertices and density from command line
				 */
				int vertices = Integer.parseInt(args[1]);
				int density = Integer.parseInt(args[2]);
				int edges = (density * ((vertices) * (vertices - 1) / 2)) / 100;

				/**
				 * Generate graph until it is connected
				 */
				while (!connected) {
					graph = new Graph(vertices, edges);
					DepthFirstSearch dfs = new DepthFirstSearch(graph, SOURCE);
					connected = dfs.isConnected();
					/**
					 * Test client to display created graph
					 */
					// if (connected) {
					// System.out.println(graph);
					// for (int vertexCount = 0; vertexCount < graph
					// .getVerticesCount(); vertexCount++) {
					// if (dfs.hasSeen(vertexCount)) {
					// System.out.print(vertexCount + " ");
					// }
					// }
					// System.out.println("Connected");
					// }
				}

				/**
				 * Find the minimum spanning tree using Simple scheme
				 */
				startTime = System.currentTimeMillis();
				mstSimpleScheme(graph, false);
				endTime = System.currentTimeMillis();
				time = endTime - startTime;
				System.out.println("Time for Simple Scheme: " + time
						+ " milliseconds");

				/**
				 * Find the minimum spanning tree using Fibonacci Heap scheme
				 */
				startTime = System.currentTimeMillis();
				mstFibonacciHeapScheme(graph, false);
				endTime = System.currentTimeMillis();
				time = endTime - startTime;
				System.out.println("Time for Fibonacci Heap Scheme: " + time
						+ " milliseconds");
			} catch (Exception exc) {
				System.err
						.println("Something went wrong!!! Please check the input "
								+ exc.getMessage());

			}
		}

		/**
		 * Calculates minimum spanning tree using simple scheme
		 * Accepts filename containing undirected graph data
		 * 
		 */
		else if (args[0].equals("-s")) {
			try {
				/**
				 * Read input from a file
				 */
				InputStreamReader inputFile = new InputStreamReader(args[1]);
				/**
				 * Generate graph until it is connected
				 */
				while (!connected) {
					graph = new Graph(inputFile);
					DepthFirstSearch dfs = new DepthFirstSearch(graph, SOURCE);
					connected = dfs.isConnected();
					/**
					 * Test client to display created graph
					 */
					// if (connected) {
					// System.out.println(graph);
					// for (int vertexCount = 0; vertexCount < graph
					// .getVerticesCount(); vertexCount++) {
					// if (dfs.hasSeen(vertexCount)) {
					// System.out.print(vertexCount + " ");
					// }
					// }
					// System.out.println("Connected");
					// }
				}

				/**
				 * Find the minimum spanning tree using Simple scheme
				 */
				mstSimpleScheme(graph, true);
			} catch (Exception exc) {
				System.err
						.println("Something went wrong!!! Please check the input "
								+ exc.getMessage());

			}

		}
		/**
		 * Calculates minimum spanning tree using fibonacci heap scheme
		 * Accepts filename containing undirected graph data
		 * 
		 */
		else if (args[0].equals("-f")) {
			try {
				/**
				 * Read input from a file
				 */
				InputStreamReader inputFile = new InputStreamReader(args[1]);

				/**
				 * Generate graph until it is connected
				 */
				while (!connected) {
					graph = new Graph(inputFile);
					DepthFirstSearch dfs = new DepthFirstSearch(graph, SOURCE);
					connected = dfs.isConnected();
					/**
					 * Test client to display created graph
					 */
					// if (connected) {
					// System.out.println(graph);
					// for (int vertexCount = 0; vertexCount < graph
					// .getVerticesCount(); vertexCount++) {
					// if (dfs.hasSeen(vertexCount)) {
					// System.out.print(vertexCount + " ");
					// }
					// }
					// System.out.println("Connected");
					// }
				}

				/**
				 * Find the minimum spanning tree using Fibonacci Heap scheme
				 */
				mstFibonacciHeapScheme(graph, true);
			} catch (Exception exc) {
				System.err
						.println("Something went wrong!!! Please check the input "
								+ exc.getMessage());

			}

		}
		/**
		 * For invalid input,display the accepted input formats.
		 */
		else {
			System.out.println("Invalid input : Please use the following input formats : ");
			System.out.println("Random mode : mst -r n d");
			System.out.println("Using Simple Scheme from file : mst -s file-name");
			System.out.println("Using Fibonacci Heap Scheme from file : mst -f file-name");
		}

	}

	/**
	 * Display the minimum spanning tree for Fibonacci Heap scheme
	 */
	private static void mstFibonacciHeapScheme(Graph graph, boolean display) {
		PrimMstFibonacciHeapScheme mst = new PrimMstFibonacciHeapScheme(graph);
		if (display) {
			System.out.println(mst.weight());
			for (Edge e : mst.edges()) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Display the minimum spanning tree for Simple scheme
	 */
	private static void mstSimpleScheme(Graph graph, boolean display) {
		PrimMstSimpleScheme mst = new PrimMstSimpleScheme(graph, SOURCE);
		if (display) {
			System.out.println(mst.weight());
			for (Edge e : mst.edges()) {
				System.out.println(e);
			}
		}
	}

}
