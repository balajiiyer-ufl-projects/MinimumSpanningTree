MinimumSpanningTree
===================

Implemented Prim’s Minimum Spanning Tree algorithm using Arrays and Fibonacci Heaps.Generated undirected graphs randomly with different edge densities for different number of vertices(1000,3000 &5000) as input.Depth first search was performed on the generated graphs to make sure it is connected.The runtimes were measured and compared for both the implementations.

The program supports the following two data input modes:

1.Random mode – Accepts vertex count and density as inputs. Compute the MST using both the schemes in this mode. 

The command line for this mode is:

$ mst –r n d // run in a random connected graph with n vertices and d% of density.

2.User Input mode – Accepts a file "file-name" containing undirected graph data with vertices, edges and weight. The output is displayed on standard output stream

The command line for this mode is:

$mst -s file-name // read the input from a file ‘file-name’ for simple scheme 

$mst -f file-name // read the input from a file ‘file-name’ for f-heap scheme

In the user input mode, the program gets the input in the following format:

￼￼￼n m      // The number of vertices and edges respectively in the first line // the edge (v1, v2) with cost c1

v1 v2 c1 // the edge (v1, v2) with cost c1

v2 v3 c2 // the edge (v2, v3) with cost c2

...      // a total of m edges

Assume that vertices are labeled from 0 to n-1. An example input is shown below:

3 2

0 1 5

1 2 8

The graph consists of three vertices {0, 1, 2} and two edges (0,1) and (1,2) with costs 5 and 8 respectively.
In the user input mode, the program displays the cost of this tree in the first line and the edges in the constructed spanning tree in the following n-1 lines. The output is printed to the standard output stream.
The output for the example shown above is as follows:

13 

0 1 

1 2
