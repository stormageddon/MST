// Sorter.java
//  Sorts a connected graph.
//  @author Michael Caputo <mpc5944>

import java.util.*;

public class Sorter {
  
    public Sorter() { }

    private void insertionSort(Edge[] edges) {

	int totalWeight = 0;
	int n = edges.length;
	for (int j = 1; j < n; j++) {
	    Edge tempEdge = edges[j];
	    int i = j-1;
	    while ( (i > -1) && (edges[i].compare(edges[i], tempEdge)  < 0 ) ) {
		edges[i + 1] = edges[i];
		i--;
	    }
	    edges[i + 1] = tempEdge;
	}

	for (int index = 0; index < edges.length; index++) {
	    System.out.println(edges[index]);
	    totalWeight += edges[index].weight;
	}

	System.out.println("Total weight: " + totalWeight);

    }

    public Edge[] insertionSortMatrix(int[][] matrix, int numEdges) {
	System.out.println("\nSORTED EDGES WITH MATRIX USING INSERTION SORT");
	long startTime = System.currentTimeMillis();
	Edge[] edges = new Edge[numEdges];
	int edgeIndex = 0;

	for( int i = 0; i < matrix.length; i++ ) {
	    for (int j = 0; j < matrix.length; j++ ) {
		if ( matrix[i][j] != 0 ) {
		    // Edge exists

		    // Make sure edge hasn't been added already
		    Edge edgeToAdd = new Edge(i, j, matrix[i][j]);
		    if (!Arrays.asList(edges).contains(edgeToAdd)) {
			edges[edgeIndex] = new Edge(i, j, matrix[i][j]);
			edgeIndex++;
		    }
		}
	    }
	}
	insertionSort(edges);
	long endTime = System.currentTimeMillis();
	System.out.printf("Runtime: %d milliseconds\n", endTime - startTime);
	return edges;
    }

    public Edge[] insertionSortList(Map<Integer, Vertex> adjacencyList, int numEdges) {
	System.out.println("\nSORTED EDGES WITH LIST USING INSERTION SORT");
	long startTime = System.currentTimeMillis();
	Edge[] edges = new Edge[numEdges];
	int edgeIndex = 0;

	for( int i = 0; i < adjacencyList.size(); i++ ) {
	    Vertex v = adjacencyList.get(i);
	    for( int j = 0; j < v.getNeighbors().size(); j++ ) {
		Edge edgeToAdd = new Edge(v.getId(), v.getNeighbors().get(j).vertex.getId(), v.getNeighbors().get(j).weight);
		if (!Arrays.asList(edges).contains(edgeToAdd)) {
		    edges[edgeIndex] = edgeToAdd;
		    edgeIndex++;
		}
	    }
	}
	insertionSort(edges);
	long endTime = System.currentTimeMillis();
	System.out.printf("Runtime: %d milliseconds\n", endTime - startTime);
	return edges;
    }
}
