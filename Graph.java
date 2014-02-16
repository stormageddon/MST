/**
 * Graph.java
 * This class represents an undirected, connected, weighted graph.
 *
 */
import java.util.*;

public class Graph {
    private Map<Integer, Vertex> adjacencyList;
    //    private Random weightGenerator = new Random(200000);
    //    private Random connectionGenerator = new Random(100000);
    private Random weightGenerator;
    private Random connectionGenerator;
    private int n, seed;
    private double p;

    public Graph(int n, int seed, double p) {
	adjacencyList = new HashMap<Integer, Vertex>();
	this.n = n;
	this.seed = seed;
	this.p = p;
	//	generateGraph();
	this.connectionGenerator = new Random(seed);
	this.weightGenerator = new Random(2*seed);
    }

    /*
     * n := number of vertices in graph
     */
    public Graph generateGraph() {

	for (int i = 0; i < n; i++ ) {
	    addVertex(i);
	}
	for (int i = 0; i < adjacencyList.size(); i++) {
	    addNeighbors(i);
	}
	return this;
    }

    public void addVertex(int vertexId) {
	adjacencyList.put(vertexId, new Vertex(vertexId));
    }

    public void addNeighbors(int vertexId) {
	for( int element = vertexId; element < adjacencyList.size(); element++ ) {
	    if( element != vertexId ) {
		if (connectionGenerator.nextDouble() <= p) {
		    int weight = weightGenerator.nextInt(n) + 1;
		    adjacencyList.get(vertexId).addNeighbor(adjacencyList.get(element),new Integer(weight));
		    adjacencyList.get(element).addNeighbor(adjacencyList.get(vertexId),new Integer(weight));
		}
	    }
	}
    }

    public void printGraphAsAdjacencyList() {
	System.out.printf("\nThe graph as an adjacency list:");
	for(int i = 0; i < adjacencyList.size(); i++) {
	    Vertex currVertex = adjacencyList.get(i);
	    System.out.printf("\n%d->",currVertex.getId());
	    for(int k = 0; k < currVertex.getNeighbors().size(); k++) {
		Tuple currNeighbor = currVertex.getNeighbors().get(k);
		System.out.printf(" %d(%d)",((Vertex)currNeighbor.vertex).getId(), currNeighbor.weight);
	    }
	}
	System.out.println("\n");
    }

    public void printGraphAsMatrix() {
	System.out.println("The graph as an adjacency matrix:\n");
	for(int i = 0; i < adjacencyList.size(); i++) {
	    Vertex currVertex = adjacencyList.get(i);
	    for(int k = 0; k < adjacencyList.size(); k++) {
		int w = 0;
		if( currVertex.contains(k) ) {
		    w = currVertex.getNeighborWeight(adjacencyList.get(k));
		}
		System.out.printf("%d\t",w);
	    }
	    System.out.println("\n");
	}
    }

    public Vertex getVertex(int vertexId) {
	return adjacencyList.get(vertexId);
    }

    public void clear() {
	adjacencyList = new HashMap<Integer, Vertex>();
    }
}