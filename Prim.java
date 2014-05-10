// Implementation of Prim's algorithm

import java.util.ArrayList;
import java.util.*;


public class Prim {

    int currPriority = 999999;
    ArrayList<Edge> mst = new ArrayList<Edge>();
    ArrayList<Vertex>tPriority = new ArrayList<Vertex>();

    public void performPrimOnMatrix(int[][] matrix, int numEdges, Graph g, int numVertices) {
	ArrayList<Edge> edgesInGraph = new ArrayList<Edge>(Arrays.asList(getEdgesFromMatrix(matrix, numEdges)));
	ArrayList<Edge> edgesMST = new ArrayList<Edge>(); // The final arrayList
	ArrayList<Vertex> vertices = new ArrayList<Vertex>();

	Heap heap = new Heap();
	Vertex startVertex = g.getVertex(0);
	ArrayList<Tuple<Vertex, Integer>> neighborTuples = startVertex.getNeighbors();
	
	for( Tuple<Vertex, Integer> tuple : neighborTuples ) {
	    heap.insert(tuple.vertex);
	}
	
	vertices.add(startVertex);
	

	while( vertices.size() < numVertices ) {
	    Vertex root = heap.delMax();
	    if( !vertices.contains(root) ) {
		ArrayList<Tuple<Vertex, Integer>> newTuples = root.getNeighbors();

		for( Tuple<Vertex, Integer> tuple : newTuples ) {
		    heap.insert(tuple.vertex);
		}

		vertices.add(root);
		ArrayList<Edge> emptyEdges = new ArrayList<Edge>();

		int smallest = root.getNeighbors().get(0).weight;
		Tuple<Vertex, Integer> smallestTuple = root.getNeighbors().get(0);
		for( int i = 0; i < root.getNeighbors().size(); i++ ) {
		    if( root.getNeighbors().get(i).weight < smallest ) {
			smallestTuple = root.getNeighbors().get(i);
		    }
		}

		if( smallestTuple != null ) {
		    // Make sure edge not already added
		    Edge newEdge = new Edge(root.getId(), smallestTuple.vertex.getId(), smallestTuple.weight);
		    if( !edgesMST.contains(newEdge) ) {
			edgesMST.add(newEdge);
		    }
		}
	    }
	}

	int totalWeight = 0;
	System.out.println("MST:");
	for( Edge edge : edgesMST ) {
	    //	    System.out.println("\t" + edge.v1 + " " + edge.v2 + " -> " + edge.weight);
	    totalWeight += edge.weight;
	}
	System.out.println("Total weight: " + totalWeight);
	

    }

    //    ArrayList<Vertex> mst = new ArrayList<Vertex>();

    private Vertex deleteMin(Heap queue) {
	//	Vertex root = queue.element();
	//	queue.remove(root);
	//	tPriority.add(root);
	Vertex root = queue.delMax();
	
	return root;
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
}
