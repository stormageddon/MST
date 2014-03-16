// Sorter.java
//  Sorts a connected graph.
//  @author Michael Caputo <mpc5944>

import java.util.*;

public class Sorter {

    private boolean shouldPrint = true;

    public Sorter(boolean shouldPrint) {
	this.shouldPrint = shouldPrint;
    }

    public Edge[] quickSortMatrix(int[][] matrix, int numEdges) {
	System.out.println("\nSORTED EDGES WITH MATRIX USING QUICK SORT");
	long startTime = System.currentTimeMillis();
	Edge[] edges = getEdgesFromMatrix(matrix, numEdges);

	quickSort(edges);
       
	long endTime = System.currentTimeMillis();
	System.out.printf("Runtime: %d milliseconds\n", endTime - startTime);
	return edges;
    }

    public Edge[] quickSortList(Map<Integer, Vertex> adjacencyList, int numEdges) {
	System.out.println("\nSORTED EDGES WITH LIST USING QUICK SORT");
	long startTime = System.currentTimeMillis();
	Edge[] edges = getEdgesFromList(adjacencyList, numEdges);

	quickSort(edges);

	long endTime = System.currentTimeMillis();
	System.out.printf("Runtime: %d milliseconds\n", endTime - startTime);
	return edges;
    }

    private void quickSort(Edge[] edges) {
	qSort(edges, 0, edges.length - 1);
	
	int totalWeight = 0;
	for( int j = 0; j < edges.length; j++ ) {
	    if( shouldPrint ) {
		System.out.printf("%d %d weight =  %d \n",edges[j].v1, edges[j].v2, edges[j].weight);
	    }
	    totalWeight += edges[j].weight;
	}
	System.out.printf("Total weight = %d\n", totalWeight);
    }

    private void qSort(Edge[] edges, int lo, int hi) {
	if (hi <= lo) {
	    return;
	}
	int j = partition(edges, lo, hi);
	qSort(edges, lo, j-1);
	qSort(edges, j+1, hi);
    }

    private int partition(Edge[] edges, int lo, int hi) {
	int i = lo, j = hi+1;
	while( true ) {
	    while (edges[i++].weight < edges[lo].weight) {
		if (i == hi) {
		    break;
		}
	    }
	    while (edges[lo].weight < edges[--j].weight) {
		if (j == lo) {
		    break;
		}
	    }

	    if (i >= j) {
		break;
	    }
	    exchange(edges, i, j);
	}
	
	exchange(edges, lo, j);
	return j;
    }

    private void exchange(Edge[] edges, int indexI, int indexJ) {
	Edge tempEdge = edges[indexI];
	edges[indexI] = edges[indexJ];
	edges[indexJ] = tempEdge;
    }

    public Edge[] countSortMatrix(int[][] matrix, int numEdges) {
	System.out.println("\nSORTED EDGES WITH MATRIX USING COUNT SORT");
	long startTime = System.currentTimeMillis();
	Edge[] edges = getEdgesFromMatrix(matrix, numEdges);

	countSort(edges);

	long endTime = System.currentTimeMillis();
	System.out.printf("Runtime: %d milliseconds\n", endTime - startTime);
	return edges;
    }

    public Edge[] countSortList(Map<Integer, Vertex> adjacencyList, int numEdges) {
	System.out.println("\nSORTED EDGES WITH MATRIX USING COUNT SORT");
	long startTime = System.currentTimeMillis();
	Edge[] edges = getEdgesFromList(adjacencyList, numEdges);
	
	countSort(edges);
       
	long endTime = System.currentTimeMillis();
	System.out.printf("Runtime: %d milliseconds\n", endTime - startTime);
	return edges;
    }

    private void countSort(Edge[] edges) {
	int[] count = new int[(getMaxWeight(edges)) + 1];
	
	for( int i = 0; i < edges.length; i++ ) {
	    Edge currEdge = edges[i];
	    count[currEdge.weight] = count[currEdge.weight] + 1;
	}

	for( int k = 1; k < count.length; k++ ) {
	    count[k] = count[k] + count[k - 1];
	}

	Edge[] aux = new Edge[edges.length];
	for (int j = edges.length - 1; j >= 0; j--) {
	    Edge currEdge = edges[j];
	    int index = count[currEdge.weight] - 1;
	    aux[index] = currEdge;
	    count[currEdge.weight] = count[currEdge.weight] - 1;
	}

	int totalWeight = 0;
	for (int x = 0; x < aux.length; x++) {
	    if( aux[x] != null ) {
		if( shouldPrint ) {
		    System.out.printf("%d %d weight = %d\n", aux[x].v1, aux[x].v2, aux[x].weight);
		}
		totalWeight += aux[x].weight;
	    }
	}
	
	System.out.printf("Total weight = %d\n", totalWeight);
    }

    private int getMaxWeight(Edge[] edges) {
	int largest = edges[0].weight;
	for( int i = 1; i < edges.length; i++ ) {
	    if( edges[i].weight > largest ) {
		largest = edges[i].weight;
	    }
	}

	return largest;
    }

    private int getMinWeight(Edge[] edges) {
	int smallest = edges[0].weight;
	for( int i = 1; i < edges.length - 1; i++ ) {
	    if( edges[i].weight < smallest ) {
		smallest = edges[i].weight;
	    }
	}
	return smallest;
    }

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
	    if( shouldPrint ) {
		System.out.println(edges[index]);
	    }
	    totalWeight += edges[index].weight;
	}

	System.out.println("Total weight: " + totalWeight);

    }

    private Edge[] getEdgesFromMatrix(int[][] matrix, int numEdges) {
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
	return edges;
    }

    private Edge[] getEdgesFromList(Map<Integer, Vertex> adjacencyList, int numEdges) {
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
	return edges;
    }

    public Edge[] insertionSortMatrix(int[][] matrix, int numEdges) {
	System.out.println("\nSORTED EDGES WITH MATRIX USING INSERTION SORT");
	long startTime = System.currentTimeMillis();
	Edge[] edges = getEdgesFromMatrix(matrix, numEdges);

	insertionSort(edges);

	long endTime = System.currentTimeMillis();
	System.out.printf("Runtime: %d milliseconds\n", endTime - startTime);
	return edges;
    }

    public Edge[] insertionSortList(Map<Integer, Vertex> adjacencyList, int numEdges) {
	System.out.println("\nSORTED EDGES WITH LIST USING INSERTION SORT");
	long startTime = System.currentTimeMillis();
	Edge[] edges = getEdgesFromList(adjacencyList, numEdges);

	insertionSort(edges);

	long endTime = System.currentTimeMillis();
	System.out.printf("Runtime: %d milliseconds\n", endTime - startTime);
	return edges;
    }
}
