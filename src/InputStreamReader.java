/*************************************************************************
 *  Compilation:  javac InputStreamReader.java
 *  
 *  Execution:    java InputStreamReader   
 *
 *  Reads in data from a file
 *
 *************************************************************************/

/**
 *  Reads the graph data(vertices,edges,weight) from a file 
 *
 *  @author balajiiyer
 */

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public final class InputStreamReader {

	/** Object to read inputs */
	private Scanner scanner;

	/**
	 * Create an input stream from standard input
	 */
	public InputStreamReader() {
		scanner = new Scanner(new BufferedInputStream(System.in));
	}

	/**
	 * Create an input stream from a file
	 */
	public InputStreamReader(String s) {
		try {
			// Read file from local file system
			File file = new File(s);
			if (file.exists()) {
				scanner = new Scanner(file);
				return;
			}

		} catch (IOException ioe) {
			System.err.println("Could not open file " + s);
		}
	}

	/**
	 * Check if input is empty
	 */
	public boolean isEmpty() {
		return !scanner.hasNext();
	}
	
	/**
	 * Check if input has next line
	 */
	public boolean hasNextLine() {
		return scanner.hasNextLine();
	}

	/**
	 * Read and return the next line.
	 */
	public String readLine() {
		String line;
		try {
			line = scanner.nextLine();
		} catch (Exception e) {
			line = null;
		}
		return line;
	}

	/**
	 * Read and return the next integer
	 */
	public int readInteger() {
		return scanner.nextInt();
	}

	/**
	 * Close the input stream.
	 */
	public void close() {
		scanner.close();
	}

	/**
	 * Test client.
	 */
	// public static void main(String[] args) {
	// InputStreamReader in;
	// System.out.println("---------------------------------------------------------------------------");
	// try {
	// in = new
	// InputStreamReader("/Volumes/Einstein/UFL/Fall 2013/ADS/project/mst.txt");
	// while (!in.isEmpty()) {
	// String s = in.readLine();
	// System.out.println(s);
	// }
	// System.out.println();
	// }
	// catch (Exception e) { System.out.println(e); }
	// System.out.println();
	// }
}
